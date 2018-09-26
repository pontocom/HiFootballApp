package com.example.brunobarros.hifootball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class InitialPage extends AppCompatActivity {

    String method, resj;
    JSONObject jsonres;
    JSONArray jsonArray;
    ArrayList<String> nomejogos = new ArrayList<String>();
    ArrayList<String> jogo = new ArrayList<String>();
    json jsonclass;
    SharedPreferences pref;
    static String APPTAG = "HIFootballApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina__inicial);

        //startActivity(new Intent(this, ChangeIP.class));

        pref = getApplicationContext().getSharedPreferences("login", 0);

        method = "List_Jogos";
        jsonclass = new json();
        PHPConnection phpc = new PHPConnection(this, jsonclass);
        try {
            resj = phpc.execute(method).get();
            Log.d(APPTAG, "0");
        } catch (InterruptedException e) {
            Toast.makeText(this, "Erro1", Toast.LENGTH_LONG).show();
            Log.d(APPTAG, "1");
        } catch (ExecutionException e) {
            Toast.makeText(this, "Erro2", Toast.LENGTH_LONG).show();
            Log.d(APPTAG, "2");
        }

        try {
            //jsonObject = new JSONObject(res);
            //jsonArray = jsonObject.getJSONArray("");
            if (resj == null)
            {
                Log.d(APPTAG, "Entrou na StartActivity -> InitialPage");
                Toast.makeText(this, "There was an error connecting to the backend!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ChangeIP.class));
                finish();
            }
            else {
                jsonArray = new JSONArray(resj);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonres = jsonArray.getJSONObject(i);
                    nomejogos.add(jsonres.getString("Nome_do_Jogo"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView ljogos = (ListView) findViewById(R.id.lvjogos);

        ljogos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomejogos));

        ljogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    Toast.makeText(InitialPage.this, "Id do Jogo: " + jsonArray.getJSONObject(position).getString("Id").toString() + "\nNome do Jogo: " + jsonArray.getJSONObject(position).getString("Nome_do_Jogo").toString(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(InitialPage.this, History.class);
                    jogo.add(jsonArray.getJSONObject(position).getString("Id").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("IdEC").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("IdEV").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("IdU").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("IdC").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("Jornada").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("Data").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("Nome_do_Jogo").toString());
                    jogo.add(jsonArray.getJSONObject(position).getString("Tempo").toString());
                    i.putExtra("jogo", jogo);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().getSharedPreferences("login", 0).edit().clear().commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.criar_evento) {
            if (!(pref.getString("login", null) == null)) {
                startActivity(new Intent(this, CreateEvent.class));
            }
            else
                startActivity(new Intent(this, Login.class));
        }

        if (id == R.id.pagina_inicial) {

            startActivity(new Intent(this, InitialPage.class));
        }

        if (id == R.id.historico) {

            startActivity(new Intent(this, History.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
