package com.example.brunobarros.hifootball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by brunobarros on 13/10/17.
 */
public class Alterar_IP extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterarip);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int largura = dm.widthPixels;
        int altura = dm.heightPixels;

        getWindow().setLayout((int)(largura*.6),(int)(altura*.6));

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefs", 0);
        final SharedPreferences.Editor editor = pref.edit();

        Button bt = (Button) findViewById(R.id.btinserir);
        final EditText ip = (EditText) findViewById(R.id.ip);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("ip", ip.getText().toString());
                editor.apply();
                Log.i("HiFootball", "IP = " + ip.getText().toString());
                //editor.commit();
                //finish();
                startActivity(new Intent(getApplicationContext(), Pagina_Inicial.class));
            }
        });
    }
}
