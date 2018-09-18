package com.example.brunobarros.hifootball;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Historico extends AppCompatActivity {

    String method, resej, reseE;
    JSONObject jsonres, jsonres2;
    JSONArray jsonArray, jsonArray2, jsonArray3;
    ArrayList<String> jogo = new ArrayList<String>();
    ImageView ivequipa;
    ImageView ivjogador;
    ArrayList<String> nomeequipas = new ArrayList<String>();
    ArrayList<String> nomejogadrores;
    TextView golo, canto, desarme, remate, falta, posse, jgolo, jremate, jpenalty, jlivres, jcantos, jpasses, jcruzamento, jlancamento, jrecupe, jcortes, jperda;
    Spinner sjogadores;
    json jsonclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        ivequipa = (ImageView) findViewById(R.id.IVEquipa);
        ivjogador = (ImageView) findViewById(R.id.IVJogador);
        Spinner sequipas = (Spinner) findViewById(R.id.sequipa);
        sjogadores = (Spinner) findViewById(R.id.sjogador);
        golo = (TextView) findViewById(R.id.TVGolo);
        canto = (TextView) findViewById(R.id.TVCanto);
        desarme = (TextView) findViewById(R.id.TVDesarme);
        remate = (TextView) findViewById(R.id.TVRemae);
        falta = (TextView) findViewById(R.id.TVFalta);
        posse = (TextView) findViewById(R.id.TVPosse);
        jgolo = (TextView) findViewById(R.id.TVJGolo);
        jremate = (TextView) findViewById(R.id.TVJRemate);
        jpenalty = (TextView) findViewById(R.id.TVJPenalty);
        jlivres = (TextView) findViewById(R.id.TVJLivres);
        jcantos = (TextView) findViewById(R.id.TVJCantos);
        jpasses = (TextView) findViewById(R.id.TVJPasses);
        jcruzamento = (TextView) findViewById(R.id.TVJCruzamento);
        jlancamento = (TextView) findViewById(R.id.TVJLancamento);
        jrecupe = (TextView) findViewById(R.id.TVJRecuperacao);
        jcortes = (TextView) findViewById(R.id.TVJCortes);
        jperda = (TextView) findViewById(R.id.TVJPerda);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            Toast.makeText(this, "Mostrar as ferramentas para escolher o jogo.", Toast.LENGTH_LONG).show();
        } else {
            jogo = extras.getStringArrayList("jogo");

            /*for (int i = 0; i < jogo.size(); i++)
            {
                Toast.makeText(this, "Na posicao " + i + " do array temos: " + jogo.get(i).toString(), Toast.LENGTH_LONG).show();
            }*/

            String substr = jogo.get(7).substring(jogo.get(7).indexOf("Vs") + 3).toString();
            String substr2 = jogo.get(7).substring(0,jogo.get(7).indexOf("Vs")).toString();
            Toast.makeText(this, "Nome da equipa que joga em casa:  " + substr2 + " Vs " +  substr, Toast.LENGTH_LONG).show();

            String ip = "192.168.1.5";
            new LoadImage().execute("http://"+ip+":80/HiFootball/Fotos/Arsenal.jpg", "http://"+ip+":80/HiFootball/Fotos/Giroud.jpg");

            nomeequipas.add(substr2);
            nomeequipas.add(substr);
            sequipas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomeequipas));

            method = "List_EventoEquipa";
            jsonclass = new json();
            PHPConnection phpc = new PHPConnection(this,jsonclass);

            try {
                reseE =phpc.execute(method, jogo.get(0).toString()).get();
                Toast.makeText(this, "Resposta do PHP : " + reseE, Toast.LENGTH_LONG).show();
                Log.e("JSON Parser", "Resposta do PHP : " + reseE);

                jsonArray = new JSONArray(reseE);
                for(int i = 0; i < jsonArray.length(); i++) {
                    jsonres = jsonArray.getJSONObject(i);

                    /*golo.setText(jsonres.getString("Golo_C"));
                    canto.setText(jsonres.getString("Canto_C"));
                    desarme.setText(jsonres.getString("Desarme_C"));
                    remate.setText(jsonres.getString("Remate_C"));
                    falta.setText(jsonres.getString("Falta_C"));
                    posse.setText(jsonres.getString("Posse_de_Bola_C"));*/

                }
            } catch (InterruptedException e) {
                Toast.makeText(this, "Erro1", Toast.LENGTH_LONG).show();
            } catch (ExecutionException e) {
                Toast.makeText(this, "Erro2", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        sequipas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String ide = "0";

                if (position == 0)
                {
                    Toast.makeText(Historico.this, "Equipa da Casa!" + "\n Id da equipa: " + jogo.get(1).toString(), Toast.LENGTH_LONG).show();
                    try {
                        ide = jogo.get(1).toString();

                        if (!reseE.equals("0")) {
                            golo.setText(jsonres.getString("Golo_C"));
                            canto.setText(jsonres.getString("Canto_C"));
                            desarme.setText(jsonres.getString("Desarme_C"));
                            remate.setText(jsonres.getString("Remate_C"));
                            falta.setText(jsonres.getString("Falta_C"));
                            posse.setText(jsonres.getString("Posse_de_Bola_C"));
                        }
                        else {
                            golo.setText("nd");
                            canto.setText("nd");
                            desarme.setText("nd");
                            remate.setText("nd");
                            falta.setText("nd");
                            posse.setText("nd");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(Historico.this, "Equipa da Visitante!" + "\n Id da equipa: " + jogo.get(2).toString(), Toast.LENGTH_LONG).show();
                    try {
                        ide = jogo.get(2).toString();

                        if (!reseE.equals("0")) {
                            golo.setText(jsonres.getString("Golo_V"));
                            canto.setText(jsonres.getString("Canto_V"));
                            desarme.setText(jsonres.getString("Desarme_V"));
                            remate.setText(jsonres.getString("Remate_V"));
                            falta.setText(jsonres.getString("Falta_V"));
                            posse.setText(jsonres.getString("Posse_de_Bola_V"));
                        }
                        else
                        {
                            golo.setText("nd");
                            canto.setText("nd");
                            desarme.setText("nd");
                            remate.setText("nd");
                            falta.setText("nd");
                            posse.setText("nd");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                PHPConnection phpc = new PHPConnection(Historico.this, jsonclass);
                method = "List_Jogadores";

                try {
                    resej =phpc.execute(method, ide).get();

                    nomejogadrores = new ArrayList<String>();
                    jsonArray2 = new JSONArray(resej);
                    for(int i = 0; i < jsonArray2.length(); i++) {
                        jsonres2 = jsonArray2.getJSONObject(i);

                        nomejogadrores.add(jsonres2.getString("Nome"));
                    }

                    sjogadores.setAdapter(new ArrayAdapter<String>(Historico.this, android.R.layout.simple_spinner_dropdown_item, nomejogadrores));
                } catch (InterruptedException e) {
                    Toast.makeText(Historico.this, "Erro1", Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    Toast.makeText(Historico.this, "Erro2", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sjogadores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PHPConnection phpc = new PHPConnection(Historico.this, jsonclass);
                method = "List_EventoJogador";

                try {
                    jsonres2 = jsonArray2.getJSONObject(position);

                    resej =phpc.execute(method, jogo.get(0).toString(), jsonres2.getString("Id")).get();

                    jsonArray3 = new JSONArray(resej);
                    for(int i = 0; i < jsonArray3.length(); i++) {
                        jsonres2 = jsonArray3.getJSONObject(i);

                        jgolo.setText(jsonres2.getString("Golo"));
                        jremate.setText(jsonres2.getString("Remate"));
                        jpenalty.setText(jsonres2.getString("Penalty"));
                        jlivres.setText(jsonres2.getString("Livre"));
                        jcantos.setText(jsonres2.getString("Canto"));
                        jpasses.setText(jsonres2.getString("Passe"));
                        jcruzamento.setText(jsonres2.getString("Cruzamento"));
                        jlancamento.setText(jsonres2.getString("Lancamento"));
                        jrecupe.setText(jsonres2.getString("Recuperacao"));
                        jcortes.setText(jsonres2.getString("Corte"));
                        jperda.setText(jsonres2.getString("Perda_de_Bola"));
                    }
                } catch (JSONException e) {
                    jgolo.setText("nd");
                    jremate.setText("nd");
                    jpenalty.setText("nd");
                    jlivres.setText("nd");
                    jcantos.setText("nd");
                    jpasses.setText("nd");
                    jcruzamento.setText("nd");
                    jlancamento.setText("nd");
                    jrecupe.setText("nd");
                    jcortes.setText("nd");
                    jperda.setText("nd");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        if (id == R.id.criar_evento) {

            startActivity(new Intent(this, Criar_Evento.class));
        }

        if (id == R.id.pagina_inicial) {

            startActivity(new Intent(this, Pagina_Inicial.class));
        }

        if (id == R.id.historico) {

            startActivity(new Intent(this, Historico.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadImage extends AsyncTask<String,Void,Bitmap>{

        Bitmap bmp = null;
        Bitmap bmp2 = null;
        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                bmp = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                bmp2 = BitmapFactory.decodeStream((InputStream) new URL(params[1]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap image) {

            ivequipa.setImageBitmap(image);
            ivjogador.setImageBitmap(bmp2);

        }
    }
}
