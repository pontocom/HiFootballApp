import usb.core
import usb.util
import datetime
import requests

def send_event(userID, time, data):
    event = {   "userID": userID, 
                "time": str(time),
                "event": ','.join(str(e) for e in data) }
    resp = requests.post('http://localhost:5000/sendEvent', json=event)
    if resp.status_code != 201:
        print('sending of event failed')
        #raise ApiError('POST /tasks/ {}'.format(resp.status_code))
    print('Sent ' + str(data))

def main():
    # find device ids with lsusb in terminal
    device = usb.core.find(idVendor=0x0079, idProduct=0x0011) # Dilong Gamepad
    if device is None:
        print('did not find an input device')
        return

    # make sure device is not busy and use default configuration
    device.reset()
    if device.is_kernel_driver_active(0) == True:
        device.detach_kernel_driver(0)
    device.set_configuration()

    # first endpoint
    endpoint = device[0][(0,0)][0]

    # try to connect to server and get userID
    resp = requests.get('http://localhost:5000/userID')
    if resp.status_code != 200:
    # This means something went wrong.
        print("could not connect to server")
        #raise ApiError('GET /tasks/ {}'.format(resp.status_code))
    userID = resp.json()["userID"]
    print("userID is " + str(userID))

    # create file
    # filename = "GamepadData_" # +str(datetime.datetime.now())
    # file = open(filename, "a+")

    # read data
    data = device.read(endpoint.bEndpointAddress, endpoint.wMaxPacketSize)
    oldData = []
    initialData = data # let's hope this is no buttons pressed
    while data[6] != 32:
        try:
            data = device.read(endpoint.bEndpointAddress,
                               endpoint.wMaxPacketSize)
            # send events when buttons are pressed and when they are releasesed 
            if oldData != data and initialData != data:
                time = datetime.datetime.now()
                send_event(userID, time, data)
                # file.write(str(time) + " ")
                # file.write(str(data) + "\n")
            oldData = data

        except usb.core.USBError as e:
            data = None
            if e.args == ('Operation timed out',):
                continue
    # file.close()



if __name__ == '__main__':
  main()
