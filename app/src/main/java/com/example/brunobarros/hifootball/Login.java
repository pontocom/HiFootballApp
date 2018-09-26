package com.example.brunobarros.hifootball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by brunobarros on 31/08/17.
 */
public class Login extends Activity {

    json jsonclass;
    String method, resj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        method = "List_Jogos";
        jsonclass = new json();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int largura = dm.widthPixels;
        int altura = dm.heightPixels;

        getWindow().setLayout((int)(largura*.6),(int)(altura*.6));

        SharedPreferences pref = getApplicationContext().getSharedPreferences("login", 0);
        final SharedPreferences.Editor editor = pref.edit();

        Button bt = (Button) findViewById(R.id.Login);
        final EditText user = (EditText) findViewById(R.id.Email);
        final EditText password = (EditText) findViewById(R.id.Password);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Username: "+ user.getText() + " Password: " + password.getText(), Toast.LENGTH_LONG).show();
                PHPConnection phpc = new PHPConnection(getApplicationContext(), jsonclass);
                method = "login";
                try {
                    resj = phpc.execute(method, user.getText().toString(), password.getText().toString()).get();
                    if (resj.contains("Erro:"))
                        Toast.makeText(getApplicationContext(), resj, Toast.LENGTH_LONG).show();
                    else
                    {
                        Toast.makeText(getApplicationContext(), resj, Toast.LENGTH_LONG).show();
                        editor.putString("login", resj);
                        editor.commit();
                        startActivity(new Intent(getApplicationContext(), CreateEvent.class));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().getSharedPreferences("login", 0).edit().clear().commit();
    }
}
