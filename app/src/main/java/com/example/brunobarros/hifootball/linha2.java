package com.example.brunobarros.hifootball;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class linha2 extends AppCompatActivity {

    private TextView text2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linha2_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);

        //criar o ficheiro
        //File file = new File(context.getFilesDir(), "teste.txt");
        File file = getBaseContext().getFileStreamPath("teste2.txt");
        text2 = (TextView) findViewById(R.id.texto);

        //Declarar o layout e rodar para ter o campo na posicao correcta
        RelativeLayout rlm = (RelativeLayout) findViewById(R.id.rlmain);
        rlm.setRotationY(180);

        RelativeLayout.LayoutParams lparms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparms.setMarginEnd(1);

        if (file.exists())
        {
            Toast.makeText(this, "O ficheiro existe", Toast.LENGTH_LONG).show();

            //Se deseja escrever no ficheiro na mesma, so colocar o codigo em baixo
        }

        else {
            //escrever no ficheiro
            try {

                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("teste2.txt", 0));
                out.write("1 2 3 4");
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //ler ficheiro
        try {

            final InputStream in = openFileInput("teste2.txt");

            if (in != null) {
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader breader = new BufferedReader(reader);
                StringBuilder sbuilder = new StringBuilder();
                String str;
                RelativeLayout.LayoutParams params;
                Button btn2 = null;
                int i = 0;

                while ((str = breader.readLine()) != null) {
                    sbuilder.append(str + "\n");
                    Toast.makeText(this, "A palavra selecionada foi: " + str, Toast.LENGTH_SHORT).show();

                    String[] splited = str.split("\\s+");
                    for (i = 0; i<splited.length; i++)
                    {
                        Toast.makeText(this, "A palavra separada selecionada foi: " + splited[i], Toast.LENGTH_SHORT).show();
                        Button btn = new Button(this);

                        if (i == 0)
                        {
                            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            params.addRule(RelativeLayout.RIGHT_OF, text2.getId());
                        }
                        else
                        {
                            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            params.addRule(RelativeLayout.RIGHT_OF, btn2.getId());
                        }
                        btn.setLayoutParams(params);
                        btn.setId(i + 1);
                        btn.setText(splited[i]);

                        //adicionar evento ao botao
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(linha2.this, "Button(" + v.getId() + ")", Toast.LENGTH_SHORT).show();

                                //Instante de tempo
                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                                String strDate = sdf.format(c.getTime());
                                Toast.makeText(linha2.this, "Tempo: (" + strDate + ")", Toast.LENGTH_SHORT).show();
                            }
                        });
                        btn2 = btn;
                        btn.setRotationY(-180);
                        btn.setBackgroundResource(R.drawable.bttstyle);
                        btn.setTextColor(getBaseContext().getResources().getColor(R.color.colorPrimary));
                        //btn.setLayoutParams(lparms);
                        rlm.addView(btn);
                    }
                }
                in.close();
                text2.setText(sbuilder.toString());
                //Criar o botao para chamar a outra pagina
                if (btn2 != null) {
                    params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.RIGHT_OF, btn2.getId());
                    Button btn = new Button(this);
                    btn.setLayoutParams(params);
                    btn.setId(i + 1);
                    btn.setText("Mudar de tactica");

                    final Intent intent = new Intent(linha2.this, MainActivity.class);

                    //adicionar evento ao botao!
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(linha2.this, "Mudar de equipa", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });

                    btn.setRotationY(-180);
                    btn.setBackgroundResource(R.drawable.bttstyle);
                    btn.setTextColor(getBaseContext().getResources().getColor(R.color.colorPrimary));
                    rlm.addView(btn);
                }
                else
                    Toast.makeText(linha2.this, "O ficheiro em causa se encontra vazio!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    public void criarevento (View view)
    {
        startActivity(new Intent(this, Criar_Evento.class));
    }
}
