package com.example.brunobarros.hifootball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by brunobarros on 17/03/16.
 */
public class PHPConnection extends AsyncTask<String,Void,String> {

    Context ctx;
    json dadosjogadores; // PlayerData
    String res = "";
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    PHPConnection (Context ctx, json dadosjogadores)
    {
        this.ctx = ctx;
        this.dadosjogadores = dadosjogadores;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        //Urls para os scripts PHP
        //192.168.35.55 - Isla  192.168.3.2 - Casa  172.20.10.3 - MVL
        //Toast.makeText(ctx, "Do it on backgroud", Toast.LENGTH_SHORT).show();
        sharedpreferences = ctx.getSharedPreferences(MyPREFERENCES, ctx.MODE_PRIVATE);
        String ip = "";
        try {
            ip = sharedpreferences.getString("ip", null);
            Log.d("PHP", ip);
        }
        catch (Exception e)
        {

        }

        String url = "http://"+ip+":80/HiFootball/";

        String method = params[0];

        if (method.equals("Listar_FuncaoUT"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_FuncaoUT.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //this.res = res;
                //json jsonres = new json();
                //jsonres.setJson(res);
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("login"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"login.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8")+ "&" +
                              URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("List_Campeonatos"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_Campeonatos.php");
                HttpURLConnection httpurlc2 = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc2.setRequestMethod("POST");
                httpurlc2.setDoInput(true);
                InputStream ips = httpurlc2.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc2.disconnect();

                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (method.equals("List_Equipa"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_Equipa.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("List_Jogadores"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_Jogador.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("List_JogadorparaJogo"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_JogadorparaJogo.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("Inserir_Evento"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"inserir_envento.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("idec","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                              URLEncoder.encode("idev","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                              URLEncoder.encode("idu","UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                              URLEncoder.encode("idc","UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                              URLEncoder.encode("jornada","UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                              URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8") + "&" +
                              URLEncoder.encode("nomej","UTF-8") + "=" + URLEncoder.encode(params[7], "UTF-8") + "&" +
                              URLEncoder.encode("tempo","UTF-8") + "=" + URLEncoder.encode(params[8], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();

                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("Inserir_EventoJogador"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            //Create JSON string start
            String line = "";/* res = "", json_string = "{\"upload_fishes\":[";
            String tmp = sharedpreferences.getString("EquipaCasaIDJ", "");
            StringTokenizer st2 = new StringTokenizer(tmp,",");
            tmp = sharedpreferences.getString("EquipaForaIDJ", "");
            StringTokenizer st1 = new StringTokenizer(tmp,",");
            int cgolo = 0, cremate = 0, cdesarme = 0, cfalta = 0, ccanto = 0;
            int fgolo = 0, fremate = 0, fdesarme = 0, ffalta = 0, fcanto = 0;*/

            try {
                URL url_LFUT = new URL(url+"inserir_enventoJogador.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                /*buscar os dados e colocalos na val
                int[] tmpEventos = new int[11];
                for (int i = 0; i < 22; i++) {
                    if (i == 0) {
                        tmpEventos = dadosjogadores.getGREventos();
                    }
                    else  if (i == 1) {
                        tmpEventos = dadosjogadores.getDDEventos();
                    }
                    else  if (i == 2) {
                        tmpEventos = dadosjogadores.getDEEventos();
                    }
                    else  if (i == 3) {
                        tmpEventos = dadosjogadores.getDC1Eventos();
                    }
                    else  if (i == 4) {
                        tmpEventos = dadosjogadores.getDC2Eventos();
                    }
                    else  if (i == 5) {
                        tmpEventos = dadosjogadores.getMDCEventos();
                    }
                    else  if (i == 6) {
                        tmpEventos = dadosjogadores.getMC1Eventos();
                    }
                    else  if (i == 7) {
                        tmpEventos = dadosjogadores.getMC2Eventos();
                    }
                    else  if (i == 8) {
                        tmpEventos = dadosjogadores.getMODEventos();
                    }
                    else  if (i == 9) {
                        tmpEventos = dadosjogadores.getMOEEventos();
                    }
                    else if (i == 10) {
                        tmpEventos = dadosjogadores.getPLEventos();
                    }
                    else if (i == 11) {
                        tmpEventos = dadosjogadores.getGRVEventos();
                    }
                    else  if (i == 12) {
                        tmpEventos = dadosjogadores.getDDVEventos();
                    }
                    else  if (i == 13) {
                        tmpEventos = dadosjogadores.getDEVEventos();
                    }
                    else  if (i == 14) {
                        tmpEventos = dadosjogadores.getDC1VEventos();
                    }
                    else  if (i == 15) {
                        tmpEventos = dadosjogadores.getDC2VEventos();
                    }
                    else  if (i == 16) {
                        tmpEventos = dadosjogadores.getMDCVEventos();
                    }
                    else  if (i == 17) {
                        tmpEventos = dadosjogadores.getMC1VEventos();
                    }
                    else  if (i == 18) {
                        tmpEventos = dadosjogadores.getMC2VEventos();
                    }
                    else  if (i == 19) {
                        tmpEventos = dadosjogadores.getMODVEventos();
                    }
                    else  if (i == 20) {
                        tmpEventos = dadosjogadores.getMOEVEventos();
                    }
                    else if (i == 21) {
                        tmpEventos = dadosjogadores.getPLVEventos();
                    }

                    //Repeat and loop this until all objects are added (and add try+catch)
                    JSONObject obj_new = new JSONObject();
                    obj_new.put("IdEv", sharedpreferences.getString("IDE", ""));
                    obj_new.put("Remate", tmpEventos[0]);
                    obj_new.put("Passe", tmpEventos[1]);
                    obj_new.put("Perda_de_Bola", tmpEventos[2]);
                    obj_new.put("Corte", tmpEventos[3]);
                    obj_new.put("Recuperacao", tmpEventos[4]);
                    obj_new.put("Cruzamento", tmpEventos[5]);
                    obj_new.put("Lancamento", tmpEventos[6]);
                    obj_new.put("Canto", tmpEventos[7]);
                    obj_new.put("Livre", tmpEventos[8]);
                    obj_new.put("Penalty", tmpEventos[9]);
                    obj_new.put("Golo", tmpEventos[10]);
                    if (i < 11) {
                        obj_new.put("IdJ", st2.nextToken());
                        cgolo += tmpEventos[10];
                        cremate += tmpEventos[0];
                        cdesarme += tmpEventos[4];
                        //cfalta += 0;
                        ccanto += tmpEventos[7];
                    }
                    else {
                        obj_new.put("IdJ", st1.nextToken());
                        fgolo += tmpEventos[10];
                        fremate += tmpEventos[0];
                        fdesarme += tmpEventos[4];
                        //cfalta += 0;
                        fcanto += tmpEventos[7];
                    }

                    json_string = json_string + obj_new.toString() + ",";
                }

                //Close JSON string
                json_string = json_string.substring(0, json_string.length() - 1);
                json_string += "]}";
                Log.i("JSON OBJECT: ", "DADOS: " + json_string);

                Log.i("ARRAY GR: ", "REMATE: " + tmpEventos[0] + "Passe:" + tmpEventos[1] + "Perda_de_Bola:" + tmpEventos[2] + "Corte:" + tmpEventos[3] + "Recuperacao:" + tmpEventos[4] + "Cruzamento:" + tmpEventos[5] + "Lancamento:" + tmpEventos[6] + "Canto:" + tmpEventos[7] + "Livre:" + tmpEventos[8] + "Penalty:" + tmpEventos[9] + "Golo" + tmpEventos[10]);*/
                String data = URLEncoder.encode("IdJ","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("IdEv","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("ts","UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("IdU","UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
                //String data = URLEncoder.encode("IdJ","UTF-8") + "=" + URLEncoder.encode(json_string, "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();

                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                /*Inserir dados na parte da equipa....
                url_LFUT = new URL(url+"inserir_dadosEquipas.php");
                httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                os = httpurlc.getOutputStream();
                bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //buscar os dados e colocalos na SP


                Log.i("DADOS DAS EQUIPAS: ", "cgolo: " + cgolo + "cremate:" + cremate + "cdesarme:" + cdesarme + "cfalta:" + cfalta + "ccanto:" + ccanto + "fgolo:" + fgolo + "fremate:" + fremate + "fdesarme:" + fdesarme + "ffalta:" + ffalta + "fcanto:" + fcanto + "String.valueOf(sharedpreferences.getInt(tempo, 0)" + String.valueOf(sharedpreferences.getInt("tempo", 0)));
                data = URLEncoder.encode("IdJ","UTF-8") + "=" + URLEncoder.encode(sharedpreferences.getString("IDEC", ""), "UTF-8") + "&" +
                        URLEncoder.encode("IdEv","UTF-8") + "=" + URLEncoder.encode(sharedpreferences.getString("IDE", ""), "UTF-8") + "&" +
                        URLEncoder.encode("cgolo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(cgolo), "UTF-8") + "&" +
                        URLEncoder.encode("cremate","UTF-8") + "=" + URLEncoder.encode(String.valueOf(cremate), "UTF-8") + "&" +
                        URLEncoder.encode("cdesarme","UTF-8") + "=" + URLEncoder.encode(String.valueOf(cdesarme), "UTF-8") + "&" +
                        URLEncoder.encode("cfalta","UTF-8") + "=" + URLEncoder.encode(String.valueOf(cfalta), "UTF-8") + "&" +
                        URLEncoder.encode("ccanto","UTF-8") + "=" + URLEncoder.encode(String.valueOf(ccanto), "UTF-8") + "&" +
                        URLEncoder.encode("fgolo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(fgolo), "UTF-8") + "&" +
                        URLEncoder.encode("fremate","UTF-8") + "=" + URLEncoder.encode(String.valueOf(fremate), "UTF-8") + "&" +
                        URLEncoder.encode("fdesarme","UTF-8") + "=" + URLEncoder.encode(String.valueOf(fdesarme), "UTF-8") + "&" +
                        URLEncoder.encode("ffalta","UTF-8") + "=" + URLEncoder.encode(String.valueOf(ffalta), "UTF-8") + "&" +
                        URLEncoder.encode("fcanto","UTF-8") + "=" + URLEncoder.encode(String.valueOf(fcanto), "UTF-8") + "&" +
                        URLEncoder.encode("cposse","UTF-8") + "=" + URLEncoder.encode(String.valueOf((sharedpreferences.getInt("tempo", 0)*100)/5400)+"%", "UTF-8") + "&" +
                        URLEncoder.encode("fposse","UTF-8") + "=" + URLEncoder.encode(String.valueOf((sharedpreferences.getInt("tempov", 0)*100)/5400)+"%", "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();

                ips = httpurlc.getInputStream();
                br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();*/

                Log.i("DADOS DO PHP: ", "RESULTADO: " + res);

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }/* catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
        else if (method.equals("inserir_enventos"))
        {
            String line = "";

            try {
                URL url_LFUT = new URL(url + "inserir_enventos.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                /*String data = URLEncoder.encode("IdEv","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("ts","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("Remate","UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("Passe","UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                        URLEncoder.encode("Perda_de_Bola","UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                        URLEncoder.encode("Corte","UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8") + "&" +
                        URLEncoder.encode("Recuperacao","UTF-8") + "=" + URLEncoder.encode( params[7], "UTF-8") + "&" +
                        URLEncoder.encode("Cruzamento","UTF-8") + "=" + URLEncoder.encode(params[8], "UTF-8") + "&" +
                        URLEncoder.encode("Lancamento","UTF-8") + "=" + URLEncoder.encode(params[9], "UTF-8") + "&" +
                        URLEncoder.encode("Canto","UTF-8") + "=" + URLEncoder.encode(params[10], "UTF-8") + "&" +
                        URLEncoder.encode("Livre","UTF-8") + "=" + URLEncoder.encode(params[11], "UTF-8") + "&" +
                        URLEncoder.encode("Penalty","UTF-8") + "=" + URLEncoder.encode(params[12], "UTF-8") + "&" +
                        URLEncoder.encode("IdJ","UTF-8") + "=" + URLEncoder.encode(params[14], "UTF-8") + "&" +
                        URLEncoder.encode("btpg","UTF-8") + "=" + URLEncoder.encode(params[15], "UTF-8") + "&" +
                        URLEncoder.encode("btlg","UTF-8") + "=" + URLEncoder.encode(params[16], "UTF-8") + "&" +
                        URLEncoder.encode("btlancamentog","UTF-8") + "=" + URLEncoder.encode(params[17], "UTF-8") + "&" +
                        URLEncoder.encode("btcantog","UTF-8") + "=" + URLEncoder.encode(params[18], "UTF-8") + "&" +
                        URLEncoder.encode("faltao","UTF-8") + "=" + URLEncoder.encode(params[19], "UTF-8") + "&" +
                        URLEncoder.encode("faltad","UTF-8") + "=" + URLEncoder.encode(params[20], "UTF-8") + "&" +
                        URLEncoder.encode("rematef","UTF-8") + "=" + URLEncoder.encode(params[21], "UTF-8") + "&" +
                        URLEncoder.encode("remateb","UTF-8") + "=" + URLEncoder.encode(params[22], "UTF-8") + "&" +
                        URLEncoder.encode("agolo","UTF-8") + "=" + URLEncoder.encode(params[23], "UTF-8") + "&" +
                        URLEncoder.encode("bolafora","UTF-8") + "=" + URLEncoder.encode(params[24], "UTF-8") + "&" +
                        URLEncoder.encode("pontapebaliza","UTF-8") + "=" + URLEncoder.encode(params[25], "UTF-8") + "&" +
                        URLEncoder.encode("defesa","UTF-8") + "=" + URLEncoder.encode(params[26], "UTF-8") + "&" +
                        URLEncoder.encode("camarelo","UTF-8") + "=" + URLEncoder.encode(params[27], "UTF-8") + "&" +
                        URLEncoder.encode("cvermelho","UTF-8") + "=" + URLEncoder.encode(params[28], "UTF-8") + "&" +
                        URLEncoder.encode("Golo","UTF-8") + "=" + URLEncoder.encode(params[13], "UTF-8");*/
                String data = URLEncoder.encode("IdEv","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("ts","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("IdJ","UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("IdAcao","UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                        URLEncoder.encode("IdU","UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();

                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                Log.i("DADOS DO PHP: ", "RESULTADO: " + res);

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("List_Jogos")) // List games.
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_Evento.php"); // List Match
                HttpURLConnection httpurlc2 = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc2.setRequestMethod("POST");
                httpurlc2.setDoInput(true);
                InputStream ips = httpurlc2.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc2.disconnect();

                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (method.equals("List_EventoEquipa"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_EventoEquipa.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("List_EventoJogador"))
        {
            //Toast.makeText(ctx, "Listar_FuncaoUT", Toast.LENGTH_SHORT).show();
            String line = "", res = "";

            try {
                URL url_LFUT = new URL(url+"List_EventoJogador.php");
                HttpURLConnection httpurlc = (HttpURLConnection) url_LFUT.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoInput(true);
                httpurlc.setDoOutput(true);
                OutputStream os = httpurlc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                              URLEncoder.encode("idj","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                bw.write(data);
                bw.flush();
                bw.close();



                InputStream ips = httpurlc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));

                while ((line = br.readLine())!=null)
                {
                    res += line;
                }

                br.close();
                ips.close();
                httpurlc.disconnect();

                //por os dados na ddl
                //Spinner gender =(Spinner) findViewById(R.id.ddev);
                //this.res = res;
                return res;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*@Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/

    @Override
    protected void onPostExecute(String s) {

        //Toast.makeText(ctx, res, Toast.LENGTH_LONG).show();

    }
}
