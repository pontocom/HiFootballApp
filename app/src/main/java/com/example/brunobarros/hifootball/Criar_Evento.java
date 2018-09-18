package com.example.brunobarros.hifootball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Criar_Evento extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String method;
    JSONObject jsonObject, jsonres;
    JSONArray jsonArray, jsonArrayequipas;
    String resc, rese, Id = "";
    int idc, idec=-1, idev=-1, ides;
    ArrayList<String> nomecompiticao = new ArrayList<String>();
    ArrayList<String> nomeequipas = new ArrayList<String>();
    Context context = this;
    java.text.SimpleDateFormat fdata = new java.text.SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat fhora = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat data_hora = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    SharedPreferences pref;

    Spinner ddec;
    Spinner ddev;
    Spinner ddes;
    json jsonclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar__evento);


        pref = getApplicationContext().getSharedPreferences("login", 0);
        /*json jsonres = new json();
        String res = jsonres.getJson();
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();*/
        context = getApplicationContext();

        method = "List_Campeonatos";
        jsonclass = new json();
        PHPConnection phpc = new PHPConnection(this, jsonclass);
        //phpc.execute(method);
        try {
            resc =phpc.execute(method).get();
        } catch (InterruptedException e) {
            Toast.makeText(this, "Erro1", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(this, "Erro2", Toast.LENGTH_LONG).show();
        }

        /*method = "List_Equipa";
        PHPConnection phpc2 = new PHPConnection(this);
        try {
            rese = phpc2.execute(method).get();
        } catch (InterruptedException e) {
            Toast.makeText(this, "Erro1", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(this, "Erro2", Toast.LENGTH_LONG).show();
        }*/

        //Declara os objectos da pagina
        Spinner ddcompiticao = (Spinner) findViewById(R.id.ddcompiticao);
        ddcompiticao.setOnItemSelectedListener(this);
        ddec = (Spinner) findViewById(R.id.ddec);
        ddev = (Spinner) findViewById(R.id.ddev);
        ddes = (Spinner) findViewById(R.id.ddes);
        //final EditText data = (EditText) findViewById(R.id.data);
        final EditText hora = (EditText) findViewById(R.id.horajogo);
        Button criarevento = (Button) findViewById(R.id.btncriar);
        final DatePicker dp = (DatePicker) findViewById(R.id.datePicker);


        try {
            //jsonObject = new JSONObject(res);
            //jsonArray = jsonObject.getJSONArray("");
            jsonArray = new JSONArray(resc);
            for(int i = 0; i < jsonArray.length(); i++) {
                jsonres = jsonArray.getJSONObject(i);
                Id = jsonres.getString("Id");
                nomecompiticao.add(jsonres.getString("Nome"));

            }

            /*jsonArrayequipas = new JSONArray(rese);
            for(int i = 0; i < jsonArrayequipas.length(); i++) {
                jsonres = jsonArrayequipas.getJSONObject(i);
                Id = jsonres.getString("Id");
                nomeequipas.add(jsonres.getString("Nome"));

            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //res = jsonres.getJson();

        //Popular os spinners
        //Toast.makeText(this, "Resposta esperada: ---" + "\n" + resc +"\nResposta esperada2: ---" + "\n" + rese + "\nResposta esperada3: ---" + "\n" + Id, Toast.LENGTH_LONG).show();
        ddcompiticao.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomecompiticao));
        /*ddec.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomeequipas));
        ddev.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomeequipas));*/

        /*//on click do spinner compiticao para obter o Id escolhido
        ddcompiticao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idc = position;
                try {
                    Toast.makeText(context, jsonArray.getJSONObject(position).getString("Nome").toString(), Toast.LENGTH_LONG).show();

                    method = "List_Equipa";
                    PHPConnection phpc2 = new PHPConnection(context);
                    rese = phpc2.execute(method, jsonArray.getJSONObject(position).getString("Id").toString()).get();

                    jsonArrayequipas = new JSONArray(rese);
                    nomeequipas = new ArrayList<String>();
                    for(int i = 0; i < jsonArrayequipas.length(); i++) {
                        jsonres = jsonArrayequipas.getJSONObject(i);
                        Id = jsonres.getString("Id");
                        nomeequipas.add(jsonres.getString("Nome"));

                    }

                    ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, nomeequipas);
                    arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    arrayadapter.notifyDataSetChanged();

                    ddec.setAdapter(arrayadapter);
                    ddev.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, nomeequipas));

                } catch (JSONException e) {
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
        });*/

        ddec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idec = position;
                try {
                    Toast.makeText(context, jsonArrayequipas.getJSONObject(position).getString("Nome").toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                escolherequipa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ddev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idev = position;
                try {
                    Toast.makeText(context, jsonArrayequipas.getJSONObject(position).getString("Nome").toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                escolherequipa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ddes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ides = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        criarevento.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Carregou no batao", Toast.LENGTH_SHORT).show();

                if (idec == idev)
                {
                    Toast.makeText(context, "O jogo tem de ser entre diferentes equipas!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    /*if(hora.getText().toString().equals(""))
                    {
                        Toast.makeText(context, "O campo de data e hora de jogo, sao de preenchimento obrigatorio!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {*/
                        try {

                            //verificar os formatos
                            //Date testDate = fdata.parse(data.getText().toString());
                           // Date horas = fhora.parse(hora.getText().toString());
                            Date horas = fhora.parse("23:59:59");
                            int mes = dp.getMonth()+1;
                            Date caldate = fdata.parse(dp.getDayOfMonth() + "-" + mes + "-" + dp.getYear());
                            //Date hjdate = fdata.parse(String.valueOf(new Date()));
                            Date datahora = data_hora.parse(dp.getDayOfMonth() + "-" + mes + "-" + dp.getYear() + " " + "23:59:59");

                            //Compara a data a data actual.
                            if (new Date().before(datahora) ) {
                                //Toast.makeText(context, "Data maior que a actual." + "Data de hoje:" + new Date() + "\nData de hoje Cal:" + caldate, Toast.LENGTH_LONG).show();

                                //Toast.makeText(context, "Data:" + testDate + "Hora:" + horas + "\nDatePiker:" + caldate, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(context, jsonArray.getJSONObject(idc).getString("Id").toString() + "MES:" + mes, Toast.LENGTH_LONG).show();
                                //Toast.makeText(context, jsonArrayequipas.getJSONObject(idec).getString("Id").toString(), Toast.LENGTH_LONG).show();
                                //Toast.makeText(context, jsonArrayequipas.getJSONObject(idev).getString("Id").toString(), Toast.LENGTH_LONG).show();

                                PHPConnection phpc3 = new PHPConnection(context, jsonclass);
                                method = "Inserir_Evento";
                                try {
                                    resc = phpc3.execute(method, jsonArrayequipas.getJSONObject(idec).getString("Id").toString(), jsonArrayequipas.getJSONObject(idev).getString("Id").toString(), pref.getString("login", null),jsonArray.getJSONObject(idc).getString("Id").toString(), "10", dp.getDayOfMonth() + "-" + mes + "-" + dp.getYear(), jsonArrayequipas.getJSONObject(idec).getString("Nome").toString() + " Vs " + jsonArrayequipas.getJSONObject(idev).getString("Nome").toString(), hora.getText().toString()).get();
                                    Toast.makeText(context, "Id da linha:" + resc, Toast.LENGTH_SHORT).show();
                                    //chamar a pagina para montar as tatcicas
                                    if(resc.contains("Error:"))
                                    {
                                        Toast.makeText(context, resc, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Intent i = new Intent(Criar_Evento.this, Anotar_Eventos.class);
                                        i.putExtra("IDE",resc);
                                        if (ides == 0)
                                            i.putExtra("IDES",jsonArrayequipas.getJSONObject(idec).getString("Id").toString());
                                        else
                                            i.putExtra("IDES",jsonArrayequipas.getJSONObject(idev).getString("Id").toString());
                                        startActivity(i);
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }


                            }
                            else
                            {
                                Toast.makeText(context, "Data A data escolhida e um data que ja passou.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            Toast.makeText(context, "Erro no parse dos inputs.", Toast.LENGTH_SHORT).show();
                        }
                    //}
                }

            }
        });

    }

    public void escolherequipa ()
    {
         //Toast.makeText(context, idec + " - " + idev, Toast.LENGTH_LONG).show();
         if (idec != -1 && idev !=-1 && idec != idev)
         {
             ArrayList<String> equipaanotar = new ArrayList<String>();
             equipaanotar.add(nomeequipas.get(idec));
             equipaanotar.add(nomeequipas.get(idev));
             ddes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, equipaanotar));
         }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.criar_evento) {

            startActivity(new Intent(this, Criar_Evento.class));
            //return true;
        }

        if (id == R.id.pagina_inicial) {

            startActivity(new Intent(this, Pagina_Inicial.class));
        }

        if (id == R.id.historico) {

            startActivity(new Intent(this, Historico.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        idc = position;
        try {
            //Toast.makeText(context, jsonArray.getJSONObject(position).getString("Nome").toString(), Toast.LENGTH_LONG).show();

            method = "List_Equipa";
            PHPConnection phpc2 = new PHPConnection(context, jsonclass);
            rese = phpc2.execute(method, jsonArray.getJSONObject(position).getString("Id").toString()).get();

            jsonArrayequipas = new JSONArray(rese);
            nomeequipas = new ArrayList<String>();
            for(int i = 0; i < jsonArrayequipas.length(); i++) {
                jsonres = jsonArrayequipas.getJSONObject(i);
                Id = jsonres.getString("Id");
                nomeequipas.add(jsonres.getString("Nome"));

            }

            ddec.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomeequipas));
            ddev.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomeequipas));

        } catch (JSONException e) {
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

    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().getSharedPreferences("login", 0).edit().clear().commit();
    }
}
