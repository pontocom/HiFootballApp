package com.example.brunobarros.hifootball;

import android.app.Application;
import android.content.Context;
import android.view.View;

/**
 * Created by brunobarros on 26/08/17.
 */
public class State extends Application {

    public Context myState;

    public Context getState(){
        return myState;
    }
    public void setState(Context s){
        myState = s;
    }
    public void destroy ()
    {

    }
}
