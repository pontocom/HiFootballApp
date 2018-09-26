package com.example.brunobarros.hifootball;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class HomeTeam extends AppCompatActivity {

    Button golo, remate, passe, perda, cruzamento, lancamento, canto, livre, penalty, troca, GR, DD, DE, DC1, DC2, MDC, MC1, MC2, MOE, MOD, PL, iniciar, subtituicao;
    String evento = "", escolha1 = "", escollha2 = "", method, resej, tmp = "";
    int jogador1 = -1, jogador2 = -1, tmpint, selectedPosition;
    JSONArray jsonArray;
    JSONObject jsonres;
    ArrayList<String> nomeequipas = new ArrayList<String>();
    String[] EquipaCasaNome = new String[11];
    ArrayList<String> SuplentesCasa = new ArrayList<String>();
    String[] EquipaCasaIDJ = new String[11];
    ArrayList<String> SuplentesCasaIDJ = new ArrayList<String>();
    String[] EquipaForaNome = new String[11];
    ArrayList<String> SuplentesFora = new ArrayList<String>();
    String[] EquipaForaIDJ = new String[11];
    ArrayList<String> SuplentesForaIDJ = new ArrayList<String>();
    String[] eventoperda = new String[2];
    String[] opcao = new String[5];
    String[] eventoremate = new String[3];
    String[] eventocruzamento = new String[4];
    String[] eventocanto = new String[5];
    int GRRemate = 0, GRPasse = 0, GRPerda = 0, GRCorte = 0, GRRecuperacao = 0, GRCruzamento = 0, GRLancamento = 0, GRCanto = 0, GRLivre = 0, GRPenalty = 0, GRGolo = 0;
    int DDRemate = 0, DDPasse = 0, DDPerda = 0, DDCorte = 0, DDRecuperacao = 0, DDCruzamento = 0, DDLancamento = 0, DDCanto = 0, DDLivre = 0, DDPenalty = 0, DDGolo = 0;
    int DERemate = 0, DEPasse = 0, DEPerda = 0, DECorte = 0, DERecuperacao = 0, DECruzamento = 0, DELancamento = 0, DECanto = 0, DELivre = 0, DEPenalty = 0, DEGolo = 0;
    int DC1Remate = 0, DC1Passe = 0, DC1Perda = 0, DC1Corte = 0, DC1Recuperacao = 0, DC1Cruzamento = 0, DC1Lancamento = 0, DC1Canto = 0, DC1Livre = 0, DC1Penalty = 0, DC1Golo = 0;
    int DC2Remate = 0, DC2Passe = 0, DC2Perda = 0, DC2Corte = 0, DC2Recuperacao = 0, DC2Cruzamento = 0, DC2Lancamento = 0, DC2Canto = 0, DC2Livre = 0, DC2Penalty = 0, DC2Golo = 0;
    int MDCRemate = 0, MDCPasse = 0, MDCPerda = 0, MDCCorte = 0, MDCRecuperacao = 0, MDCCruzamento = 0, MDCLancamento = 0, MDCCanto = 0, MDCLivre = 0, MDCPenalty = 0, MDCGolo = 0;
    int MC1Remate = 0, MC1Passe = 0, MC1Perda = 0, MC1Corte = 0, MC1Recuperacao = 0, MC1Cruzamento = 0, MC1Lancamento = 0, MC1Canto = 0, MC1Livre = 0, MC1Penalty = 0, MC1Golo = 0;
    int MC2Remate = 0, MC2Passe = 0, MC2Perda = 0, MC2Corte = 0, MC2Recuperacao = 0, MC2Cruzamento = 0, MC2Lancamento = 0, MC2Canto = 0, MC2Livre = 0, MC2Penalty = 0, MC2Golo = 0;
    int MODRemate = 0, MODPasse = 0, MODPerda = 0, MODCorte = 0, MODRecuperacao = 0, MODCruzamento = 0, MODLancamento = 0, MODCanto = 0, MODLivre = 0, MODPenalty = 0, MODGolo = 0;
    int MOERemate = 0, MOEPasse = 0, MOEPerda = 0, MOECorte = 0, MOERecuperacao = 0, MOECruzamento = 0, MOELancamento = 0, MOECanto = 0, MOELivre = 0, MOEPenalty = 0, MOEGolo = 0;
    int PLRemate = 0, PLPasse = 0, PLPerda = 0, PLCorte = 0, PLRecuperacao = 0, PLCruzamento = 0, PLLancamento = 0, PLCanto = 0, PLLivre = 0, PLPenalty = 0, PLGolo = 0;
    int[] GREventos = new int[11];
    int[] DDEventos = new int[11];
    int[] DEEventos = new int[11];
    int[] DC1Eventos = new int[11];
    int[] DC2Eventos = new int[11];
    int[] MDCEventos = new int[11];
    int[] MC1Eventos = new int[11];
    int[] MC2Eventos = new int[11];
    int[] MODEventos = new int[11];
    int[] MOEEventos = new int[11];
    int[] PLEventos = new int[11];
    int[] tmpEventos = new int[11];
    int GRVcorte = 0, DDVcorte = 0,DEVcorte = 0, DC1Vcorte = 0, DC2Vcorte = 0, MDCVcorte = 0, MC1Vcorte = 0, MC2Vcorte = 0, MODVcorte = 0, MOEVcorte = 0, PLVcorte = 0;
    int GRVRecuperacao = 0, DDVRecuperacao = 0,DEVRecuperacao = 0, DC1VRecuperacao = 0, DC2VRecuperacao = 0, MDCVRecuperacao = 0, MC1VRecuperacao = 0, MC2VRecuperacao = 0, MODVRecuperacao = 0, MOEVRecuperacao = 0, PLVRecuperacao = 0;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Intent i;
    json jsonclass;

    long inicioposse, fimposse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipada_casa);

        golo = (Button) findViewById(R.id.btgolo);
        remate = (Button) findViewById(R.id.btremate);
        passe = (Button) findViewById(R.id.btpasse);
        perda = (Button) findViewById(R.id.btperda);
        cruzamento = (Button) findViewById(R.id.btcruzamento);
        lancamento = (Button) findViewById(R.id.btlancamento);
        canto = (Button) findViewById(R.id.btcanto);
        livre = (Button) findViewById(R.id.btlivre);
        penalty = (Button) findViewById(R.id.btpenalti);
        troca = (Button) findViewById(R.id.bttroca);
        iniciar = (Button) findViewById(R.id.btinicio);
        subtituicao = (Button) findViewById(R.id.btsubstituicao);
        GR = (Button) findViewById(R.id.btGR);
        DD = (Button) findViewById(R.id.btDD);
        DE = (Button) findViewById(R.id.btDE);
        DC1 = (Button) findViewById(R.id.btDC1);
        DC2 = (Button) findViewById(R.id.btDC2);
        MDC = (Button) findViewById(R.id.btMDC);
        MC1 = (Button) findViewById(R.id.btMC1);
        MC2 = (Button) findViewById(R.id.btMC2);
        MOE = (Button) findViewById(R.id.btMOE);
        MOD = (Button) findViewById(R.id.btMOD);
        PL = (Button) findViewById(R.id.btPL);

        eventoperda[0] = "Corte";
        eventoperda[1] = "Recuperacao";

        opcao[0] = "Concretizado";
        opcao[1] = "Falhado - Bola Fora";
        opcao[2] = "Falhado - Defesa ou Corte, mantem a posse de bola";
        opcao[3] = "Falhado - Defesa ou Corte, perda de bola";
        opcao[4] = "Passe";

        eventoremate[0] = "Remate para Fora";
        eventoremate[1] = "Remate Defendido - Mantem a posse de bola";
        eventoremate[2] = "Remate Defendido - Perda da posse de bola";

        eventocruzamento[0] = "Cruzamento bem sucedido";
        eventocruzamento[1] = "Cruzamento para Fora";
        eventocruzamento[2] = "Cruzamento Defendido - Mantem a posse de bola";
        eventocruzamento[3] = "Cruzamento Defendido - Perda da posse de bola";

        eventocanto[0] = "Canto bem sucedido";
        eventocanto[1] = "Canto - Bola Fora";
        eventocanto[2] = "Canto Defendido - Mantem a posse de bola";
        eventocanto[3] = "Canto Defendido - Perda da posse de bola";
        eventocanto[4] = "Passe";

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        i = new Intent(HomeTeam.this, VisitingTeam.class);
        /*if (sharedpreferences.contains("EquipaCasaNome"))
        {
            Toast.makeText(this, "Tem a equipa da casa!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Nao tem a equipa da casa!", Toast.LENGTH_LONG).show();*/

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            Toast.makeText(this, "Mostrar as ferramentas para escolher o jogo.", Toast.LENGTH_LONG).show();
        }
        else {
            String res = extras.getString("iniciar", "");
            jsonclass = (json) getIntent().getSerializableExtra("myclass");
            //Toast.makeText(this, jsonclass.getJson(), Toast.LENGTH_LONG).show();

            if (res.equals("Ini. 1ª Parte")) {

                iniciar.setText(res);
                preencherequipa();
                atulizararrays(jsonclass);
            }
            else if (res.equals("Fim 1ª Parte")) {

                preencherequipa();
                iniciar.setText(res);
                atulizararrays(jsonclass);
                butoes(1);
                // comecar a contar o tempo da posse de bola
                inicioposse = System.currentTimeMillis();
            }
            else if (res.equals("Ini. 2ª Parte")) {

                preencherequipa();
                iniciar.setText(res);
                atulizararrays(jsonclass);
                butoes(1);
            }
            else if (res.equals("Fim 2ª Parte")) {

                preencherequipa();
                iniciar.setText(res);
                atulizararrays(jsonclass);
                butoes(1);
                // comecar a contar o tempo da posse de bola
                inicioposse = System.currentTimeMillis();
            }
            else if (res.equals("Terminou")) {

                /*//Chamar a funcao e enviar os dados...
                method = "Inserir_EventoJogador";
                PHPConnection phpc = new PHPConnection(this, jsonclass);
                try {
                    resej = phpc.execute(method).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }*/
            }
        }

        if (iniciar.getText().equals("Confimar")) {
            //Obter as listas dos jogadores que vao jogar jogo, na proxima versao os dados devem vir da pagina criar evento.
            method = "List_JogadorparaJogo";
            PHPConnection phpc = new PHPConnection(this, jsonclass);

            try {
                escolha1 = extras.getString("IDEC", "");
                escollha2 = extras.getString("IDEV", "");
                tmp = extras.getString("IDE", "");
                if (escolha1.equals("") || escollha2.equals("")){}
                else {
                    editor.putString("IDE", tmp);
                    editor.putString("IDEC", escolha1);
                    resej = phpc.execute(method, escolha1, escollha2).get();

                    jsonArray = new JSONArray(resej);
                    int opcupadodc = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonres = jsonArray.getJSONObject(i);

                        //Toast.makeText(this, "Jogador: " + jsonres.getString("Nome") + " Equipa: " + jsonres.getString("IdE"), Toast.LENGTH_SHORT).show();

                        //fazer um if para comparar o resultado com o id da equipa vindo da pagina de criar evento, eqipa que joga em casa, e else pa equipa que joga fora
                        if (jsonArray.getJSONObject(i).getString("IdE").equals(escolha1)) {
                            //fazer um if para saber se o jogador e titular ==1, else para os substituto
                            if (jsonArray.getJSONObject(i).getString("Selecionados").equals("1")) {
                                if (jsonArray.getJSONObject(i).getString("PCampo").equals("1")) {
                                    EquipaCasaNome[0] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[0] = jsonArray.getJSONObject(i).getString("Id");
                                    GR.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("4")) {
                                    EquipaCasaNome[3] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[3] = jsonArray.getJSONObject(i).getString("Id");
                                    DC1.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("2")) {
                                    EquipaCasaNome[1] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[1] = jsonArray.getJSONObject(i).getString("Id");
                                    DD.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("3")) {
                                    EquipaCasaNome[2] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[2] = jsonArray.getJSONObject(i).getString("Id");
                                    DE.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("6")) {
                                    EquipaCasaNome[5] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[5] = jsonArray.getJSONObject(i).getString("Id");
                                    MDC.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("7")) {
                                    EquipaCasaNome[6] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[6] = jsonArray.getJSONObject(i).getString("Id");
                                    MC1.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("8")) {
                                    EquipaCasaNome[7] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[7] = jsonArray.getJSONObject(i).getString("Id");
                                    MC2.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("10")) {
                                    EquipaCasaNome[9] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[9] = jsonArray.getJSONObject(i).getString("Id");
                                    MOE.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("9")) {
                                    EquipaCasaNome[8] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[8] = jsonArray.getJSONObject(i).getString("Id");
                                    MOD.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("11")) {
                                    EquipaCasaNome[10] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[10] = jsonArray.getJSONObject(i).getString("Id");
                                    PL.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                } else {
                                    EquipaCasaNome[4] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[4] = jsonArray.getJSONObject(i).getString("Id");
                                    DC2.setText(jsonArray.getJSONObject(i).getString("Nome"));
                                }
                            } else {
                                SuplentesCasa.add(jsonArray.getJSONObject(i).getString("Nome"));
                                SuplentesCasaIDJ.add(jsonArray.getJSONObject(i).getString("Id"));
                            }
                        } else {
                            //fazemos o mesmo na equipa visitante
                            if (jsonArray.getJSONObject(i).getString("Selecionados").equals("1")) {
                                if (jsonArray.getJSONObject(i).getString("PCampo").equals("1")) {
                                    EquipaForaNome[0] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[0] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("4")) {
                                    EquipaForaNome[3] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[3] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("2")) {
                                    EquipaForaNome[1] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[1] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("3")) {
                                    EquipaForaNome[2] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[2] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("6")) {
                                    EquipaForaNome[5] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[5] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("7")) {
                                    EquipaForaNome[6] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[6] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("8")) {
                                    EquipaForaNome[7] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[7] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("10")) {
                                    EquipaForaNome[9] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[9] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("9")) {
                                    EquipaForaNome[8] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[8] = jsonArray.getJSONObject(i).getString("Id");
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("11")) {
                                    EquipaForaNome[10] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[10] = jsonArray.getJSONObject(i).getString("Id");
                                } else {
                                    EquipaForaNome[4] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaForaIDJ[4] = jsonArray.getJSONObject(i).getString("Id");
                                }
                            } else {
                                SuplentesFora.add(jsonArray.getJSONObject(i).getString("Nome"));
                                SuplentesForaIDJ.add(jsonArray.getJSONObject(i).getString("Id"));
                            }

                        }
                    }
                }
            } catch (InterruptedException e) {
                Toast.makeText(this, "Erro1", Toast.LENGTH_LONG).show();
            } catch (ExecutionException e) {
                Toast.makeText(this, "Erro2", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iniciar.getText().toString().equals("Confimar"))
                {
                    //Preparar as shered preferences das duas equipas
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < EquipaCasaNome.length; i++) {
                        str.append(EquipaCasaNome[i]).append(",");
                    }
                    editor.putString("EquipaCasaNome", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < SuplentesCasa.size(); i++) {
                        str.append(SuplentesCasa.get(i)).append(",");
                    }
                    editor.putString("SuplentesCasa", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < EquipaCasaIDJ.length; i++) {
                        str.append(EquipaCasaIDJ[i]).append(",");
                    }
                    editor.putString("EquipaCasaIDJ", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < SuplentesCasaIDJ.size(); i++) {
                        str.append(SuplentesCasaIDJ.get(i)).append(",");
                    }
                    editor.putString("SuplentesCasaIDJ", str.toString());

                    //Preparar a equipa visitante
                    str = new StringBuilder();
                    for (int i = 0; i < EquipaForaNome.length; i++) {
                        str.append(EquipaForaNome[i]).append(",");
                    }
                    editor.putString("EquipaForaNome", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < SuplentesFora.size(); i++) {
                        str.append(SuplentesFora.get(i)).append(",");
                    }
                    editor.putString("SuplentesFora", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < EquipaForaIDJ.length; i++) {
                        str.append(EquipaForaIDJ[i]).append(",");
                    }
                    editor.putString("EquipaForaIDJ", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < SuplentesForaIDJ.size(); i++) {
                        str.append(SuplentesForaIDJ.get(i)).append(",");
                    }
                    editor.putString("SuplentesForaIDJ", str.toString());

                    //guardar as 11 estatisticas de cada jogagor
                    str = new StringBuilder();
                    for (int i = 0; i < GREventos.length; i++) {
                        str.append(GREventos[i]).append(",");
                    }
                    editor.putString("GREventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DDEventos.length; i++) {
                        str.append(DDEventos[i]).append(",");
                    }
                    editor.putString("DDEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DEEventos.length; i++) {
                        str.append(DEEventos[i]).append(",");
                    }
                    editor.putString("DEEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DC1Eventos.length; i++) {
                        str.append(DC1Eventos[i]).append(",");
                    }
                    editor.putString("DC1Eventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DC2Eventos.length; i++) {
                        str.append(DC2Eventos[i]).append(",");
                    }
                    editor.putString("DC2Eventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MDCEventos.length; i++) {
                        str.append(MDCEventos[i]).append(",");
                    }
                    editor.putString("MDCEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MC1Eventos.length; i++) {
                        str.append(MC1Eventos[i]).append(",");
                    }
                    editor.putString("MC1Eventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MC2Eventos.length; i++) {
                        str.append(MC2Eventos[i]).append(",");
                    }
                    editor.putString("MC2Eventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MODEventos.length; i++) {
                        str.append(MODEventos[i]).append(",");
                    }
                    editor.putString("MODEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MOEEventos.length; i++) {
                        str.append(MOEEventos[i]).append(",");
                    }
                    editor.putString("MOEEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < PLEventos.length; i++) {
                        str.append(PLEventos[i]).append(",");
                    }
                    editor.putString("PLEventos", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < GREventos.length; i++) {
                        str.append(GREventos[i]).append(",");
                    }
                    editor.putString("GREventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DDEventos.length; i++) {
                        str.append(DDEventos[i]).append(",");
                    }
                    editor.putString("DDEventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DEEventos.length; i++) {
                        str.append(DEEventos[i]).append(",");
                    }
                    editor.putString("DEEventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DC1Eventos.length; i++) {
                        str.append(DC1Eventos[i]).append(",");
                    }
                    editor.putString("DC1EventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < DC2Eventos.length; i++) {
                        str.append(DC2Eventos[i]).append(",");
                    }
                    editor.putString("DC2EventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MDCEventos.length; i++) {
                        str.append(MDCEventos[i]).append(",");
                    }
                    editor.putString("MDCEventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MC1Eventos.length; i++) {
                        str.append(MC1Eventos[i]).append(",");
                    }
                    editor.putString("MC1EventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MC2Eventos.length; i++) {
                        str.append(MC2Eventos[i]).append(",");
                    }
                    editor.putString("MC2EventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MODEventos.length; i++) {
                        str.append(MODEventos[i]).append(",");
                    }
                    editor.putString("MODEventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < MOEEventos.length; i++) {
                        str.append(MOEEventos[i]).append(",");
                    }
                    editor.putString("MOEEventosVsisitante", str.toString());

                    str = new StringBuilder();
                    for (int i = 0; i < PLEventos.length; i++) {
                        str.append(PLEventos[i]).append(",");
                    }
                    editor.putString("PLEventosVsisitante", str.toString());

                    //criar sp para todos os cortes e recuperacao da outra equipa!!!
                    editor.putInt("GRVcorte", GRVcorte);
                    editor.putInt("GRVRecuperacao", GRVRecuperacao);
                    editor.putInt("DDVcorte", DDVcorte);
                    editor.putInt("DDVRecuperacao", DDVRecuperacao);
                    editor.putInt("MDCVcorte", MDCVcorte);
                    editor.putInt("MDCVRecuperacao", MDCVRecuperacao);
                    editor.putInt("DEVcorte", DEVcorte);
                    editor.putInt("DEVRecuperacao",DEVRecuperacao);
                    editor.putInt("MC1Vcorte",MC1Vcorte);
                    editor.putInt("MC1VRecuperacao", MC1VRecuperacao);
                    editor.putInt("MC2Vcorte",MC2Vcorte);
                    editor.putInt("MC2VRecuperacao", MC2VRecuperacao);
                    editor.putInt("DC1Vcorte",DC1Vcorte);
                    editor.putInt("DC1VRecuperacao", DC1VRecuperacao);
                    editor.putInt("DC2Vcorte",DC2Vcorte);
                    editor.putInt("DC2VRecuperacao",DC2VRecuperacao);
                    editor.putInt("MODVcorte",MODVcorte);
                    editor.putInt("MODVRecuperacao", MODVRecuperacao);
                    editor.putInt("MOEVcorte",MOEVcorte);
                    editor.putInt("MOEVRecuperacao", MOEVRecuperacao);
                    editor.putInt("PLVcorte",PLVcorte);
                    editor.putInt("PLVRecuperacao",PLVRecuperacao);

                    editor.putInt("GRCcorte", GRVcorte);
                    editor.putInt("GRCRecuperacao", GRVRecuperacao);
                    editor.putInt("DDCcorte", DDVcorte);
                    editor.putInt("DDCRecuperacao", DDVRecuperacao);
                    editor.putInt("MDCCcorte", MDCVcorte);
                    editor.putInt("MDCCRecuperacao", MDCVRecuperacao);
                    editor.putInt("DECcorte", DEVcorte);
                    editor.putInt("DECRecuperacao",DEVRecuperacao);
                    editor.putInt("MC1Ccorte",MC1Vcorte);
                    editor.putInt("MC1CRecuperacao", MC1VRecuperacao);
                    editor.putInt("MC2Ccorte",MC2Vcorte);
                    editor.putInt("MC2CRecuperacao", MC2VRecuperacao);
                    editor.putInt("DC1Ccorte",DC1Vcorte);
                    editor.putInt("DC1CRecuperacao", DC1VRecuperacao);
                    editor.putInt("DC2Ccorte",DC2Vcorte);
                    editor.putInt("DC2CRecuperacao",DC2VRecuperacao);
                    editor.putInt("MODCcorte",MODVcorte);
                    editor.putInt("MODCRecuperacao", MODVRecuperacao);
                    editor.putInt("MOECcorte",MOEVcorte);
                    editor.putInt("MOECRecuperacao", MOEVRecuperacao);
                    editor.putInt("PLCcorte",PLVcorte);
                    editor.putInt("PLCRecuperacao",PLVRecuperacao);
                    // Don't forget to commit your edits!!!
                    editor.commit();

                    //guarda o ID do Evento

                    //Enviar para a pagina da equipa visitantante
                    i.putExtra("iniciar", "Confimar");

                    //exprimentar
                    jsonclass = new json();
                    jsonclass.setJson("oi");
                    i.putExtra("myclass", jsonclass);
                    startActivity(i);
                }
                else if (iniciar.getText().toString().equals("Ini. 1ª Parte"))
                {
                    iniciar.setText("Fim 1ª Parte");
                    //anunciar o inicio do jogo
                    Toast.makeText(HomeTeam.this, "Inicio do jogo.", Toast.LENGTH_LONG).show();
                    butoes(1);
                    //Chamar a funcao de actualizar os dados.
                    // comecar a contar o tempo da posse de bola
                    inicioposse = System.currentTimeMillis();
                }
                else if (iniciar.getText().toString().equals("Fim 1ª Parte"))
                {
                    iniciar.setText("Ini. 2ª Parte");
                    //anunciar o inicio do jogo
                    Toast.makeText(HomeTeam.this, "Fim da 1ª Parte do jogo.", Toast.LENGTH_LONG).show();
                    butoes(1);
                    //Chamar a funcao de actualizar os dados.
                    actulizarVars(jsonclass);
                    // contabilizar o tempo, o atualizar vars ja faz a chamada, estou a duplicar
                    //tempo();
                }
                else if (iniciar.getText().toString().equals("Ini. 2ª Parte"))
                {
                    iniciar.setText("Fim 2ª Parte");
                    //anunciar o inicio do jogo
                    Toast.makeText(HomeTeam.this, "Inicio 2ª Parte do jogo.", Toast.LENGTH_LONG).show();
                    butoes(1);
                    //Chamar a funcao de actualizar os dados.
                    // comecar a contar o tempo da posse de bola
                    inicioposse = System.currentTimeMillis();
                }
                else if (iniciar.getText().toString().equals("Fim 2ª Parte"))
                {
                    iniciar.setText("Terminou");
                    //anunciar o inicio do jogo
                    Toast.makeText(HomeTeam.this, "Fim do jogo.", Toast.LENGTH_LONG).show();
                    butoes(1);
                    //Chamar a funcao de actualizar os dados.
                    actulizarVars(jsonclass);
                    // contabilizar o tempo, o atualizar vars ja faz a chamada, estou a duplicar
                    //tempo();
                }
                else if (iniciar.getText().toString().equals("Terminou"))
                {
                    //actulizarVars(jsonclass);
                    //Chamar a funcao e enviar os dados...
                    method = "Inserir_EventoJogador";
                    PHPConnection phpc = new PHPConnection(HomeTeam.this, jsonclass);
                    try {
                        resej = phpc.execute(method).get();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(HomeTeam.this, "Dados Inseridos com sucesso. " + resej, Toast.LENGTH_LONG).show();
                        i = new Intent(HomeTeam.this, InitialPage.class);
                        startActivity(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        iniciar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (iniciar.getText().toString().equals("Ini. 1ª Parte"))
                {
                    //O ponta pe de saida, pertence a outra equipa
                    //i = new Intent(HomeTeam.this, VisitingTeam.class);
                    actulizarVars(jsonclass);
                    i.putExtra("iniciar", "Ini. 1ª Parte");
                    i.putExtra("myclass", jsonclass);
                    startActivity(i);
                    return true;
                }
                else if (iniciar.getText().toString().equals("Ini. 2ª Parte"))
                {
                    //O ponta pe de saida, pertence a outra equipa
                    //i = new Intent(HomeTeam.this, VisitingTeam.class);
                    actulizarVars(jsonclass);
                    i.putExtra("iniciar", "Ini. 2ª Parte");
                    i.putExtra("myclass", jsonclass);
                    startActivity(i);
                    return true;
                }
                else if (iniciar.getText().toString().equals("Fim 1ª Parte"))
                {
                    //O ponta pe de saida, pertence a outra equipa
                    //i = new Intent(HomeTeam.this, VisitingTeam.class);
                    actulizarVars(jsonclass);
                    i.putExtra("iniciar", "Fim 1ª Parte");
                    i.putExtra("myclass", jsonclass);
                    startActivity(i);
                    return true;
                }
                else if (iniciar.getText().toString().equals("Fim 2ª Parte"))
                {
                    //O ponta pe de saida, pertence a outra equipa
                    //i = new Intent(HomeTeam.this, VisitingTeam.class);
                    actulizarVars(jsonclass);
                    i.putExtra("iniciar", "Fim 2ª Parte");
                    i.putExtra("myclass", jsonclass);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });

        golo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "golo";

            }
        });

        remate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "remate";

            }
        });

        passe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "passe";

            }
        });

        perda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "perda";

            }
        });

        cruzamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "cruzamento";

            }
        });

        lancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "lancamento";

            }
        });

        canto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "canto";

            }
        });

        livre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "livre";

            }
        });

        penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "penalty";

            }
        });

        troca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "troca";
                //Disable todos os botoes
                butoes(0);
            }
        });

        subtituicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "subtituicao";

            }
        });

        GR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    GRPasse++;

                }
                else if (evento.equals("remate"))
                {
                    GRRemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    GRPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    //selectedPosition = alertdialog("Escolha o evento que aconteceu?", eventoperda);
                    //Toast.makeText(HomeTeam.this, Integer.toString(selectedPosition), Toast.LENGTH_SHORT).show();
                    //tmpint = alertdialog("Escolha o Jogador envolvido no evento?", EquipaForaNome);
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    GRCruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    GRLancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 0;
                        return;
                    }
                    else
                    {
                        jogador2 = 0;

                        if (jogador1 != jogador2)
                        {
                            GREventos[0] += GRRemate;
                            GREventos[1] += GRPasse;
                            GREventos[2] += GRPerda;
                            GREventos[3] = sharedpreferences.getInt("GRCcorte",0);
                            GREventos[4] = sharedpreferences.getInt("GRCRecuperacao",0);
                            GREventos[5] += GRCruzamento;
                            GREventos[6] += GRLancamento;
                            GREventos[7] += GRCanto;
                            GREventos[8] += GRLivre;
                            GREventos[9] += GRPenalty;
                            GREventos[10] += GRGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            GRRemate = 0; GRPasse = 0; GRPerda = 0; GRCruzamento = 0; GRLancamento = 0; GRCanto = 0; GRLivre = 0; GRPenalty = 0; GRGolo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, GREventos, GR.getText().toString(), "GRCcorte", "GRCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            GREventos = j1array;

                            GR.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                    //Ativar bottoes
                }
                else if (evento.equals("penalty"))
                {
                    GRPenalty++;

                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        GRGolo++;
                                        GRRemate++;
                                        //Eviar para a pagina para quem vai por a bola em jogo
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        GRRemate++;
                                        //passar o campo para os visitantes
                                        //i = new Intent(HomeTeam.this, VisitingTeam.class);
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        GRRemate++;
                                        //colocar quem fez o corte, e mantem a posse de bola
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        GRPasse++;
                                    }
                                    else {
                                        GRRemate++;
                                        //quem cortou
                                        //podemos ter uma opcao de corte e recuperacao ao mesmo tempo.
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        //i = new Intent(HomeTeam.this, VisitingTeam.class);
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    GRCanto++;
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 0);

                }
                else if (evento.equals("livre"))
                {
                    GRLivre++;

                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                    if (selectedPosition == 0){
                                        GRGolo++;
                                        GRRemate++;
                                        //Eviar para a pagina para quem vai por a bola em jogo
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        GRRemate++;
                                        //passar o campo para os visitantes
                                        //i = new Intent(HomeTeam.this, VisitingTeam.class);
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        GRRemate++;
                                        //colocar quem fez o corte, e mantem a posse de bola
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        GRPasse++;
                                    }
                                    else {
                                        GRRemate++;
                                        //quem cortou
                                        //podemos ter uma opcao de corte e recuperacao ao mesmo tempo.
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        //i = new Intent(HomeTeam.this, VisitingTeam.class);
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    GRGolo++;
                    GRRemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + GRGolo + "\nLivre: " + GRLivre + "\nCanto: " + GRCanto + "\nPenalty: " + GRPenalty + "\nLancamento: " + GRLancamento + "\nCruzamento: " + GRCruzamento + "\nPasse: " + GRPasse + "\nPerda: " + GRPerda + "\nRemate: " + GRRemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + GREventos[10] + "\nLivre: " + GREventos[8] + "\nCanto: " + GREventos[7] + "\nPenalty: " + GREventos[9] + "\nLancamento: " + GREventos[6] + "\nCruzamento: " + GREventos[5] + "\nPasse: " + GREventos[1] + "\nPerda: " + GREventos[2] + "\nRemate: " + GREventos[0] + "\nCorte: " + GREventos[3] + "\nRecuperacao: " + GREventos[4], Toast.LENGTH_SHORT).show();
            }
        });

        DD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    DDPasse++;

                }
                else if (evento.equals("remate"))
                {
                    DDRemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    DDPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    DDCruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    DDLancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 1;
                        return;
                    }
                    else
                    {
                        jogador2 = 1;

                        if (jogador1 != jogador2)
                        {
                            DDEventos[0] += DDRemate;
                            DDEventos[1] += DDPasse;
                            DDEventos[2] += DDPerda;
                            DDEventos[3] = sharedpreferences.getInt("DDCcorte",0);
                            DDEventos[4] = sharedpreferences.getInt("DDCRecuperacao",0);
                            DDEventos[5] += DDCruzamento;
                            DDEventos[6] += DDLancamento;
                            DDEventos[7] += DDCanto;
                            DDEventos[8] += DDLivre;
                            DDEventos[9] += DDPenalty;
                            DDEventos[10] += DDGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            DDRemate = 0; DDPasse = 0; DDPerda = 0; DDCruzamento = 0; DDLancamento = 0; DDCanto = 0; DDLivre = 0; DDPenalty = 0; DDGolo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, DDEventos, DD.getText().toString(), "DDCcorte", "DDCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DDEventos = j1array;

                            DD.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    DDPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DDGolo++;
                                        DDRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DDRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DDRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DDPasse++;
                                    }
                                    else {
                                        DDRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    DDCanto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 1);

                }
                else if (evento.equals("livre"))
                {
                    DDLivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DDGolo++;
                                        DDRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DDRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DDRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DDPasse++;
                                    }
                                    else {
                                        DDRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    DDGolo++;
                    DDRemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + DDGolo + "\nLivre: " + DDLivre + "\nCanto: " + DDCanto + "\nPenalty: " + DDPenalty + "\nLancamento: " + DDLancamento + "\nCruzamento: " + DDCruzamento + "\nPasse: " + DDPasse + "\nPerda: " + DDPerda + "\nRemate: " + DDRemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + DDEventos[10] + "\nLivre: " + DDEventos[8] + "\nCanto: " + DDEventos[7] + "\nPenalty: " + DDEventos[9] + "\nLancamento: " + DDEventos[6] + "\nCruzamento: " + DDEventos[5] + "\nPasse: " + DDEventos[1] + "\nPerda: " + DDEventos[2] + "\nRemate: " + DDEventos[0] + "\nCorte: " + DDEventos[3] + "\nRecuperacao: " + DDEventos[4], Toast.LENGTH_SHORT).show();

            }
        });

        DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    DEPasse++;

                }
                else if (evento.equals("remate"))
                {
                    DERemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    DEPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    DECruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    DELancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 2;
                        return;
                    }
                    else
                    {
                        jogador2 = 2;

                        if (jogador1 != jogador2)
                        {
                            DEEventos[0] += DERemate;
                            DEEventos[1] += DEPasse;
                            DEEventos[2] += DEPerda;
                            DEEventos[3] = sharedpreferences.getInt("DECcorte",0);
                            DEEventos[4] = sharedpreferences.getInt("DECRecuperacao",0);
                            DEEventos[5] += DECruzamento;
                            DEEventos[6] += DELancamento;
                            DEEventos[7] += DECanto;
                            DEEventos[8] += DELivre;
                            DEEventos[9] += DEPenalty;
                            DEEventos[10] += DEGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            DERemate = 0; DEPasse = 0; DEPerda = 0; DECruzamento = 0; DELancamento = 0; DECanto = 0; DELivre = 0; DEPenalty = 0; DEGolo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, DEEventos, DE.getText().toString(), "DECcorte", "DECRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DEEventos = j1array;

                            DE.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    DEPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DEGolo++;
                                        DERemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DERemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DERemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DEPasse++;
                                    }
                                    else {
                                        DERemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    DECanto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 2);

                }
                else if (evento.equals("livre"))
                {
                    DELivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DEGolo++;
                                        DERemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DERemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DERemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DEPasse++;
                                    }
                                    else {
                                        DERemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    DEGolo++;
                    DERemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + DEGolo + "\nLivre: " + DELivre + "\nCanto: " + DECanto + "\nPenalty: " + DEPenalty + "\nLancamento: " + DELancamento + "\nCruzamento: " + DECruzamento + "\nPasse: " + DEPasse + "\nPerda: " + DEPerda + "\nRemate: " + DERemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + DEEventos[10] + "\nLivre: " + DEEventos[8] + "\nCanto: " + DEEventos[7] + "\nPenalty: " + DEEventos[9] + "\nLancamento: " + DEEventos[6] + "\nCruzamento: " + DEEventos[5] + "\nPasse: " + DEEventos[1] + "\nPerda: " + DEEventos[2] + "\nRemate: " + DEEventos[0] + "\nCorte: " + DEEventos[3] + "\nRecuperacao: " + DEEventos[4], Toast.LENGTH_SHORT).show();

            }
        });

        DC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    DC1Passe++;

                }
                else if (evento.equals("remate"))
                {
                    DC1Remate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    DC1Perda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    DC1Cruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    DC1Lancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 3;
                        return;
                    }
                    else
                    {
                        jogador2 = 3;

                        if (jogador1 != jogador2)
                        {
                            DC1Eventos[0] += DC1Remate;
                            DC1Eventos[1] += DC1Passe;
                            DC1Eventos[2] += DC1Perda;
                            DC1Eventos[3] = sharedpreferences.getInt("DC1Ccorte",0);
                            DC1Eventos[4] = sharedpreferences.getInt("DC1CRecuperacao",0);
                            DC1Eventos[5] += DC1Cruzamento;
                            DC1Eventos[6] += DC1Lancamento;
                            DC1Eventos[7] += DC1Canto;
                            DC1Eventos[8] += DC1Livre;
                            DC1Eventos[9] += DC1Penalty;
                            DC1Eventos[10] += DC1Golo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            DC1Remate = 0; DC1Passe = 0; DC1Perda = 0; DC1Cruzamento = 0; DC1Lancamento = 0; DC1Canto = 0; DC1Livre = 0; DC1Penalty = 0; DC1Golo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, DC1Eventos, DC1.getText().toString(), "DC1Ccorte", "DC1CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DC1Eventos = j1array;

                            DC1.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    DC1Penalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DC1Golo++;
                                        DC1Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DC1Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DC1Passe++;
                                    }
                                    else {
                                        DC1Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    DC1Canto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 3);

                }
                else if (evento.equals("livre"))
                {
                    DC1Livre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DC1Golo++;
                                        DC1Remate++;

                                    }
                                    else if (selectedPosition == 1){
                                        DC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DC1Remate++;

                                    }
                                    else if (selectedPosition == 4){

                                        DC1Passe++;
                                    }
                                    else {
                                        DC1Remate++;
                                        //perguntar quem recuperou a bola
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    DC1Golo++;
                    DC1Remate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + DC1Golo + "\nLivre: " + DC1Livre + "\nCanto: " + DC1Canto + "\nPenalty: " + DC1Penalty + "\nLancamento: " + DC1Lancamento + "\nCruzamento: " + DC1Cruzamento + "\nPasse: " + DC1Passe + "\nPerda: " + DC1Perda + "\nRemate: " + DC1Remate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + DC1Eventos[10] + "\nLivre: " + DC1Eventos[8] + "\nCanto: " + DC1Eventos[7] + "\nPenalty: " + DC1Eventos[9] + "\nLancamento: " + DC1Eventos[6] + "\nCruzamento: " + DC1Eventos[5] + "\nPasse: " + DC1Eventos[1] + "\nPerda: " + DC1Eventos[2] + "\nRemate: " + DC1Eventos[0] + "\nCorte: " + DC1Eventos[3] + "\nRecuperacao: " + DC1Eventos[4], Toast.LENGTH_SHORT).show();


            }
        });

        DC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    DC2Passe++;

                }
                else if (evento.equals("remate"))
                {
                    DC2Remate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    DC2Perda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    DC2Cruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    DC2Lancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 4;
                        return;
                    }
                    else
                    {
                        jogador2 = 4;

                        if (jogador1 != jogador2)
                        {
                            DC2Eventos[0] += DC2Remate;
                            DC2Eventos[1] += DC2Passe;
                            DC2Eventos[2] += DC2Perda;
                            DC2Eventos[3] = sharedpreferences.getInt("DC2Ccorte",0);
                            DC2Eventos[4] = sharedpreferences.getInt("DC2CRecuperacao",0);
                            DC2Eventos[5] += DC2Cruzamento;
                            DC2Eventos[6] += DC2Lancamento;
                            DC2Eventos[7] += DC2Canto;
                            DC2Eventos[8] += DC2Livre;
                            DC2Eventos[9] += DC2Penalty;
                            DC2Eventos[10] += DC2Golo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            DC2Remate = 0; DC2Passe = 0; DC2Perda = 0; DC2Cruzamento = 0; DC2Lancamento = 0; DC2Canto = 0; DC2Livre = 0; DC2Penalty = 0; DC2Golo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, DC2Eventos, DC2.getText().toString(), "DC2Ccorte", "DC2CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DC2Eventos = j1array;

                            DC2.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    DC2Penalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DC2Golo++;
                                        DC2Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DC2Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DC2Passe++;
                                    }
                                    else {
                                        DC2Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    DC2Canto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 4);

                }
                else if (evento.equals("livre"))
                {
                    DC2Livre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        DC2Golo++;
                                        DC2Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        DC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        DC2Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        DC2Passe++;
                                    }
                                    else {
                                        DC2Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    DC2Golo++;
                    DC2Remate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + DC2Golo + "\nLivre: " + DC2Livre + "\nCanto: " + DC2Canto + "\nPenalty: " + DC2Penalty + "\nLancamento: " + DC2Lancamento + "\nCruzamento: " + DC2Cruzamento + "\nPasse: " + DC2Passe + "\nPerda: " + DC2Perda + "\nRemate: " + DC2Remate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + DC2Eventos[10] + "\nLivre: " + DC2Eventos[8] + "\nCanto: " + DC2Eventos[7] + "\nPenalty: " + DC2Eventos[9] + "\nLancamento: " + DC2Eventos[6] + "\nCruzamento: " + DC2Eventos[5] + "\nPasse: " + DC2Eventos[1] + "\nPerda: " + DC2Eventos[2] + "\nRemate: " + DC2Eventos[0] + "\nCorte: " + DC2Eventos[3] + "\nRecuperacao: " + DC2Eventos[4], Toast.LENGTH_SHORT).show();


            }
        });

        MDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    MDCPasse++;

                }
                else if (evento.equals("remate"))
                {
                    MDCRemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    MDCPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    MDCCruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    MDCLancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 5;
                        return;
                    }
                    else
                    {
                        jogador2 = 5;

                        if (jogador1 != jogador2)
                        {
                            MDCEventos[0] += MDCRemate;
                            MDCEventos[1] += MDCPasse;
                            MDCEventos[2] += MDCPerda;
                            MDCEventos[3] = sharedpreferences.getInt("MDCCcorte",0);
                            MDCEventos[4] = sharedpreferences.getInt("MDCCRecuperacao",0);
                            MDCEventos[5] += MDCCruzamento;
                            MDCEventos[6] += MDCLancamento;
                            MDCEventos[7] += MDCCanto;
                            MDCEventos[8] += MDCLivre;
                            MDCEventos[9] += MDCPenalty;
                            MDCEventos[10] += MDCGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            MDCRemate = 0; MDCPasse = 0; MDCPerda = 0; MDCCruzamento = 0; MDCLancamento = 0; MDCCanto = 0; MDCLivre = 0; MDCPenalty = 0; MDCGolo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, MDCEventos, MDC.getText().toString(), "MDCCcorte", "MDCCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MDCEventos = j1array;

                            MDC.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    MDCPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MDCGolo++;
                                        MDCRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MDCRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        MDCRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MDCPasse++;
                                    }
                                    else {
                                        MDCRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    MDCCanto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 5);

                }
                else if (evento.equals("livre"))
                {
                    MDCLivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MDCGolo++;
                                        MDCRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MDCRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        MDCRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MDCPasse++;
                                    }
                                    else {
                                        MDCRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    MDCGolo++;
                    MDCRemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + MDCGolo + "\nLivre: " + MDCLivre + "\nCanto: " + MDCCanto + "\nPenalty: " + MDCPenalty + "\nLancamento: " + MDCLancamento + "\nCruzamento: " + MDCCruzamento + "\nPasse: " + MDCPasse + "\nPerda: " + MDCPerda + "\nRemate: " + MDCRemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + MDCEventos[10] + "\nLivre: " + MDCEventos[8] + "\nCanto: " + MDCEventos[7] + "\nPenalty: " + MDCEventos[9] + "\nLancamento: " + MDCEventos[6] + "\nCruzamento: " + MDCEventos[5] + "\nPasse: " + MDCEventos[1] + "\nPerda: " + MDCEventos[2] + "\nRemate: " + MDCEventos[0] + "\nCorte: " + MDCEventos[3] + "\nRecuperacao: " + MDCEventos[4], Toast.LENGTH_SHORT).show();

            }
        });

        MC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    MC1Passe++;

                }
                else if (evento.equals("remate"))
                {
                    MC1Remate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    MC1Perda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    MC1Cruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    MC1Lancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 6;
                        return;
                    }
                    else
                    {
                        jogador2 = 6;

                        if (jogador1 != jogador2)
                        {
                            MC1Eventos[0] += MC1Remate;
                            MC1Eventos[1] += MC1Passe;
                            MC1Eventos[2] += MC1Perda;
                            MC1Eventos[3] = sharedpreferences.getInt("MC1Ccorte",0);
                            MC1Eventos[4] = sharedpreferences.getInt("MC1CRecuperacao",0);
                            MC1Eventos[5] += MC1Cruzamento;
                            MC1Eventos[6] += MC1Lancamento;
                            MC1Eventos[7] += MC1Canto;
                            MC1Eventos[8] += MC1Livre;
                            MC1Eventos[9] += MC1Penalty;
                            MC1Eventos[10] += MC1Golo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            MC1Remate = 0; MC1Passe = 0; MC1Perda = 0; MC1Cruzamento = 0; MC1Lancamento = 0; MC1Canto = 0; MC1Livre = 0; MC1Penalty = 0; MC1Golo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, MC1Eventos, MC1.getText().toString(), "MC1Ccorte", "MC1CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MC1Eventos = j1array;

                            MC1.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    MC1Penalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MC1Golo++;
                                        MC1Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MC1Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MC1Passe++;
                                    }
                                    else {
                                        MC1Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    MC1Canto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 6);

                }
                else if (evento.equals("livre"))
                {
                    MC1Livre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MC1Golo++;
                                        MC1Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MC1Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MC1Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MC1Passe++;
                                    }
                                    else {
                                        MC1Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    MC1Golo++;
                    MC1Remate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + MC1Golo + "\nLivre: " + MC1Livre + "\nCanto: " + MC1Canto + "\nPenalty: " + MC1Penalty + "\nLancamento: " + MC1Lancamento + "\nCruzamento: " + MC1Cruzamento + "\nPasse: " + MC1Passe + "\nPerda: " + MC1Perda + "\nRemate: " + MC1Remate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + MC1Eventos[10] + "\nLivre: " + MC1Eventos[8] + "\nCanto: " + MC1Eventos[7] + "\nPenalty: " + MC1Eventos[9] + "\nLancamento: " + MC1Eventos[6] + "\nCruzamento: " + MC1Eventos[5] + "\nPasse: " + MC1Eventos[1] + "\nPerda: " + MC1Eventos[2] + "\nRemate: " + MC1Eventos[0] + "\nCorte: " + MC1Eventos[3] + "\nRecuperacao: " + MC1Eventos[4], Toast.LENGTH_SHORT).show();

            }
        });

        MC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    MC2Passe++;

                }
                else if (evento.equals("remate"))
                {
                    MC2Remate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    MC2Perda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    MC2Cruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    MC2Lancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 7;
                        return;
                    }
                    else
                    {
                        jogador2 = 7;

                        if (jogador1 != jogador2)
                        {
                            MC2Eventos[0] += MC2Remate;
                            MC2Eventos[1] += MC2Passe;
                            MC2Eventos[2] += MC2Perda;
                            MC2Eventos[3] = sharedpreferences.getInt("MC2Ccorte",0);
                            MC2Eventos[4] = sharedpreferences.getInt("MC2CRecuperacao",0);
                            MC2Eventos[5] += MC2Cruzamento;
                            MC2Eventos[6] += MC2Lancamento;
                            MC2Eventos[7] += MC2Canto;
                            MC2Eventos[8] += MC2Livre;
                            MC2Eventos[9] += MC2Penalty;
                            MC2Eventos[10] += MC2Golo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            MC2Remate = 0; MC2Passe = 0; MC2Perda = 0; MC2Cruzamento = 0; MC2Lancamento = 0; MC2Canto = 0; MC2Livre = 0; MC2Penalty = 0; MC2Golo = 0;

                            int[] j1array = trocacarjogador(jogador1, jogador2, MC2Eventos, MC2.getText().toString(), "MC2Ccorte", "MC2CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MC2Eventos = j1array;

                            MC2.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    MC2Penalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MC2Golo++;
                                        MC2Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MC2Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MC2Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MC2Passe++;
                                    }
                                    else {
                                        MC2Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    MC2Canto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 7);

                }
                else if (evento.equals("livre"))
                {
                    MC2Livre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MC2Golo++;
                                        MC2Remate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MC2Remate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MC2Remate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MC2Passe++;
                                    }
                                    else {
                                        MC2Remate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    MC2Golo++;
                    MC2Remate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + MC2Golo + "\nLivre: " + MC2Livre + "\nCanto: " + MC2Canto + "\nPenalty: " + MC2Penalty + "\nLancamento: " + MC2Lancamento + "\nCruzamento: " + MC2Cruzamento + "\nPasse: " + MC2Passe + "\nPerda: " + MC2Perda + "\nRemate: " + MC2Remate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + MC2Eventos[10] + "\nLivre: " + MC2Eventos[8] + "\nCanto: " + MC2Eventos[7] + "\nPenalty: " + MC2Eventos[9] + "\nLancamento: " + MC2Eventos[6] + "\nCruzamento: " + MC2Eventos[5] + "\nPasse: " + MC2Eventos[1] + "\nPerda: " + MC2Eventos[2] + "\nRemate: " + MC2Eventos[0] + "\nCorte: " + MC2Eventos[3] + "\nRecuperacao: " + MC2Eventos[4], Toast.LENGTH_SHORT).show();
            }
        });

        MOE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    MOEPasse++;

                }
                else if (evento.equals("remate"))
                {
                    MOERemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    MOEPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    MOECruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    MOELancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 9;
                        return;
                    }
                    else
                    {
                        jogador2 = 9;

                        if (jogador1 != jogador2)
                        {
                            MOEEventos[0] += MOERemate;
                            MOEEventos[1] += MOEPasse;
                            MOEEventos[2] += MOEPerda;
                            MOEEventos[3] = sharedpreferences.getInt("MOECcorte",0);
                            MOEEventos[4] = sharedpreferences.getInt("MOECRecuperacao",0);
                            MOEEventos[5] += MOECruzamento;
                            MOEEventos[6] += MOELancamento;
                            MOEEventos[7] += MOECanto;
                            MOEEventos[8] += MOELivre;
                            MOEEventos[9] += MOEPenalty;
                            MOEEventos[10] += MOEGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            MOERemate = 0; MOEPasse = 0; MOEPerda = 0; MOECruzamento = 0; MOELancamento = 0; MOECanto = 0; MOELivre = 0; MOEPenalty = 0; MOEGolo = 0;

                            int[] j1array = trocacarjogador(jogador1, jogador2, MOEEventos, MOE.getText().toString(), "MOECcorte", "MOECRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MOEEventos = j1array;

                            MOE.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    MOEPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MOEGolo++;
                                        MOERemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MOERemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MOERemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MOEPasse++;
                                    }
                                    else {
                                        MOERemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    MOECanto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 9);

                }
                else if (evento.equals("livre"))
                {
                    MOELivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MOEGolo++;
                                        MOERemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MOERemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);

                                    }
                                    else if (selectedPosition == 2){
                                        MOERemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MOEPasse++;
                                    }
                                    else {
                                        MOERemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    MOEGolo++;
                    MOERemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + MOEGolo + "\nLivre: " + MOELivre + "\nCanto: " + MOECanto + "\nPenalty: " + MOEPenalty + "\nLancamento: " + MOELancamento + "\nCruzamento: " + MOECruzamento + "\nPasse: " + MOEPasse + "\nPerda: " + MOEPerda + "\nRemate: " + MOERemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + MOEEventos[10] + "\nLivre: " + MOEEventos[8] + "\nCanto: " + MOEEventos[7] + "\nPenalty: " + MOEEventos[9] + "\nLancamento: " + MOEEventos[6] + "\nCruzamento: " + MOEEventos[5] + "\nPasse: " + MOEEventos[1] + "\nPerda: " + MOEEventos[2] + "\nRemate: " + MOEEventos[0] + "\nCorte: " + MOEEventos[3] + "\nRecuperacao: " + MOEEventos[4], Toast.LENGTH_SHORT).show();


            }
        });

        MOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (evento.equals("passe"))
                {
                    MODPasse++;

                }
                else if (evento.equals("remate"))
                {
                    MODRemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    MODPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    MODCruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    MODLancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 8;
                        return;
                    }
                    else
                    {
                        jogador2 = 8;

                        if (jogador1 != jogador2)
                        {
                            MODEventos[0] += MODRemate;
                            MODEventos[1] += MODPasse;
                            MODEventos[2] += MODPerda;
                            MODEventos[3] = sharedpreferences.getInt("MODCcorte",0);
                            MODEventos[4] = sharedpreferences.getInt("MODCRecuperacao",0);
                            MODEventos[5] += MODCruzamento;
                            MODEventos[6] += MODLancamento;
                            MODEventos[7] += MODCanto;
                            MODEventos[8] += MODLivre;
                            MODEventos[9] += MODPenalty;
                            MODEventos[10] += MODGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            MODRemate = 0; MODPasse = 0; MODPerda = 0; MODCruzamento = 0; MODLancamento = 0; MODCanto = 0; MODLivre = 0; MODPenalty = 0; MODGolo = 0;

                            int[] j1array = trocacarjogador(jogador1, jogador2, MODEventos, MOD.getText().toString(), "MODCcorte", "MODCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MODEventos = j1array;

                            MOD.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    MODPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MODGolo++;
                                        MODRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MODRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        MODRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MODPasse++;
                                    }
                                    else {
                                        MODRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    MODCanto++;
                    //cruzamento, passa ou golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 8);
                }
                else if (evento.equals("livre"))
                {
                    MODLivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        MODGolo++;
                                        MODRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        MODRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        MODRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        MODPasse++;
                                    }
                                    else {
                                        MODRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    MODGolo++;
                    MODRemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + MODGolo + "\nLivre: " + MODLivre + "\nCanto: " + MODCanto + "\nPenalty: " + MODPenalty + "\nLancamento: " + MODLancamento + "\nCruzamento: " + MODCruzamento + "\nPasse: " + MODPasse + "\nPerda: " + MODPerda + "\nRemate: " + MODRemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + MODEventos[10] + "\nLivre: " + MODEventos[8] + "\nCanto: " + MODEventos[7] + "\nPenalty: " + MODEventos[9] + "\nLancamento: " + MODEventos[6] + "\nCruzamento: " + MODEventos[5] + "\nPasse: " + MODEventos[1] + "\nPerda: " + MODEventos[2] + "\nRemate: " + MODEventos[0] + "\nCorte: " + MODEventos[3] + "\nRecuperacao: " + MODEventos[4], Toast.LENGTH_SHORT).show();


            }
        });

        PL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("passe"))
                {
                    PLPasse++;

                }
                else if (evento.equals("remate"))
                {
                    PLRemate++;
                    rematedialog("Qual disfeicho teve a bola depois do ramate?", eventoremate);
                }
                else if (evento.equals("perda"))
                {
                    PLPerda++;
                    //Depois tem que saber quel recoperou a bola ou quem a cortou ou defesa se for GR...
                    //saber se foi um corte ou recuperacao
                    CorteRecuperacao();
                }
                else if (evento.equals("cruzamento"))
                {
                    PLCruzamento++;
                    cruzamentodialog("Qual disfeicho teve a bola depois do cruzamento?", eventocruzamento);
                }
                else if (evento.equals("lancamento"))
                {
                    PLLancamento++;

                }
                else if (evento.equals("troca"))
                {
                    //elaborar a troca dos jogadores das listas, removendo quem foi substiuido da lista principal, e o suplente da respectiva e colacar o suplente na principal
                    if (jogador1 == -1)
                    {
                        jogador1 = 10;
                        return;
                    }
                    else
                    {
                        jogador2 = 10;

                        if (jogador1 != jogador2)
                        {
                            PLEventos[0] += PLRemate;
                            PLEventos[1] += PLPasse;
                            PLEventos[2] += PLPerda;
                            PLEventos[3] = sharedpreferences.getInt("PLCcorte",0);
                            PLEventos[4] = sharedpreferences.getInt("PLCRecuperacao",0);
                            PLEventos[5] += PLCruzamento;
                            PLEventos[6] += PLLancamento;
                            PLEventos[7] += PLCanto;
                            PLEventos[8] += PLLivre;
                            PLEventos[9] += PLPenalty;
                            PLEventos[10] += PLGolo;

                            //Zerar as variaveis pq o jogador esta numa nova posicao
                            PLRemate = 0; PLPasse = 0; PLPerda = 0; PLCruzamento = 0; PLLancamento = 0; PLCanto = 0; PLLivre = 0; PLPenalty = 0; PLGolo = 0;

                            int[] j1array = trocacarjogador(jogador1,jogador2, PLEventos, PL.getText().toString(), "PLCcorte", "PLCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            PLEventos = j1array;

                            PL.setText(tmp);

                            // tem que trocar a perda e recuperacao que esto guardados na shared prefernces
                            atualizartitularesSP(); //ja foi actulizado na funcao de troca
                        }
                        else
                            Toast.makeText(HomeTeam.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else if (evento.equals("penalty"))
                {
                    PLPenalty++;
                    //remate falhado, golo ou passe
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        PLGolo++;
                                        PLRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        PLRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        PLRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        PLPasse++;
                                    }
                                    else {
                                        PLRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("canto"))
                {
                    PLCanto++;
                    //cruzamento, passa ou golo, depois de dizer que o cruzamento foi feito com sucesso, atribuimos o golo
                    cantodialog("Qual disfeicho teve a bola depois do canto?", eventocanto, 10);
                }
                else if (evento.equals("livre"))
                {
                    PLLivre++;
                    //verificar se for remate falhado, golo ou cruzamento
                    new AlertDialog.Builder(HomeTeam.this)
                            .setTitle("Escolha o evento que aconteceu?")
                            .setSingleChoiceItems(opcao, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                    if (selectedPosition == 0){
                                        PLGolo++;
                                        PLRemate++;
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 1){
                                        PLRemate++;
                                        //passar o campo para os visitantes
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                    else if (selectedPosition == 2){
                                        PLRemate++;
                                        corte();
                                    }
                                    else if (selectedPosition == 4){
                                        PLPasse++;
                                    }
                                    else {
                                        PLRemate++;
                                        //perguntar quem recuperou a bola
                                        recuperacao();
                                        actulizarVars(jsonclass);
                                        i.putExtra("iniciar", iniciar.getText().toString());
                                        i.putExtra("myclass", jsonclass);
                                        startActivity(i);
                                    }
                                }
                            })
                            .show();

                }
                else if (evento.equals("golo"))
                {
                    PLGolo++;
                    PLRemate++;

                }
                else if (evento.equals("subtituicao"))
                {

                }
                else
                    Toast.makeText(HomeTeam.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();


                evento = "";
                Toast.makeText(HomeTeam.this, "Golo: " + PLGolo + "\nLivre: " + PLLivre + "\nCanto: " + PLCanto + "\nPenalty: " + PLPenalty + "\nLancamento: " + PLLancamento + "\nCruzamento: " + PLCruzamento + "\nPasse: " + PLPasse + "\nPerda: " + PLPerda + "\nRemate: " + PLRemate, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeTeam.this, "Golo: " + PLEventos[10] + "\nLivre: " + PLEventos[8] + "\nCanto: " + PLEventos[7] + "\nPenalty: " + PLEventos[9] + "\nLancamento: " + PLEventos[6] + "\nCruzamento: " + PLEventos[5] + "\nPasse: " + PLEventos[1] + "\nPerda: " + PLEventos[2] + "\nRemate: " + PLEventos[0] + "\nCorte: " + PLEventos[3] + "\nRecuperacao: " + PLEventos[4], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tempo ()
    {
        fimposse = System.currentTimeMillis() - inicioposse;
        int sec = (int) ((fimposse / 1000) % 60);
        int min = (int) ((fimposse / 60000) % 60);
        int tsec = min * 60 + sec;
        Log.i("TEMPO: ", "Tempo em segundos: " + tsec);
        Toast.makeText(HomeTeam.this, "Tempo em segundos: " + tsec, Toast.LENGTH_SHORT).show();
        editor.putInt("tempo", sharedpreferences.getInt("tempo", 0) + tsec);
        editor.commit();
    }

    private void atulizararrays (json json)
    {
        GREventos = json.getGREventos();
        DDEventos = json.getDDEventos();
        DEEventos = json.getDEEventos();
        DC1Eventos = json.getDC1Eventos();
        DC2Eventos = json.getDC2Eventos();
        MDCEventos = json.getMDCEventos();
        MC1Eventos = json.getMC1Eventos();
        MC2Eventos = json.getMC2Eventos();
        MODEventos = json.getMODEventos();
        MOEEventos = json.getMOEEventos();
        PLEventos = json.getPLEventos();
    }

    private void atualizartitularesSP ()
    {
        //Preparar as shered preferences das duas equipas
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < EquipaCasaNome.length; i++) {
            str.append(EquipaCasaNome[i]).append(",");
        }
        editor.putString("EquipaCasaNome", str.toString());

        str = new StringBuilder();
        for (int i = 0; i < EquipaCasaIDJ.length; i++) {
            str.append(EquipaCasaIDJ[i]).append(",");
        }
        editor.putString("EquipaCasaIDJ", str.toString());

        editor.commit();
    }

    private void recuperacao ()
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle("Escolha o Jogador que fez a recuperacao?")
                        //Temos de actualizar as equipas para poder ter os jogadores nas posicoes correctas
                .setSingleChoiceItems(EquipaForaNome, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        Toast.makeText(HomeTeam.this, "Recuperacao", Toast.LENGTH_SHORT).show();
                        selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                        Toast.makeText(HomeTeam.this, "Posicao:" + selectedPosition + "Jogador:" + EquipaForaNome[selectedPosition], Toast.LENGTH_SHORT).show();
                        //buscar a lista dos jogadores adversario, atravez do shared preferences
                        //fazer um if para saber se e corte ou recuperacao, e para poder saber a posicao do array para fazer update
                        if (selectedPosition == 0) {
                            GRVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 1) {
                            DDVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 2) {
                            DEVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 3) {
                            DC1VRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 4) {
                            DC2VRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 5) {
                            MDCVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 6) {
                            MC1VRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 7) {
                            MC2VRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 8) {
                            MODVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (selectedPosition == 9) {
                            MOEVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else {
                            PLVRecuperacao++;
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        }
                    }
                })
                .show();
    }

    private void cantodialog (String titulo, String[] opcaosc, int idj)
    {
        selectedPosition = idj;
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle(titulo)
                .setSingleChoiceItems(opcaosc, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        tmpint = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (tmpint == 0) {
                        } else if (tmpint == 1) {
                            if (selectedPosition == 0) {
                                GRCruzamento++;
                            } else if (selectedPosition == 1) {
                                DDCruzamento++;
                            } else if (selectedPosition == 2) {
                                DECruzamento++;
                            } else if (selectedPosition == 3) {
                                DC1Cruzamento++;
                            } else if (selectedPosition == 4) {
                                DC2Cruzamento++;
                            } else if (selectedPosition == 5) {
                                MDCCruzamento++;
                            } else if (selectedPosition == 6) {
                                MC1Cruzamento++;
                            } else if (selectedPosition == 7) {
                                MC2Cruzamento++;
                            } else if (selectedPosition == 8) {
                                MODCruzamento++;
                            } else if (selectedPosition == 9) {
                                MOECruzamento++;
                            } else {
                                PLCruzamento++;
                            }
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (tmpint == 2) {
                            if (selectedPosition == 0) {
                                GRCruzamento++;
                            } else if (selectedPosition == 1) {
                                DDCruzamento++;
                            } else if (selectedPosition == 2) {
                                DECruzamento++;
                            } else if (selectedPosition == 3) {
                                DC1Cruzamento++;
                            } else if (selectedPosition == 4) {
                                DC2Cruzamento++;
                            } else if (selectedPosition == 5) {
                                MDCCruzamento++;
                            } else if (selectedPosition == 6) {
                                MC1Cruzamento++;
                            } else if (selectedPosition == 7) {
                                MC2Cruzamento++;
                            } else if (selectedPosition == 8) {
                                MODCruzamento++;
                            } else if (selectedPosition == 9) {
                                MOECruzamento++;
                            } else {
                                PLCruzamento++;
                            }
                            corte();
                        } else if (tmpint == 3) {
                            if (selectedPosition == 0) {
                                GRCruzamento++;
                            } else if (selectedPosition == 1) {
                                DDCruzamento++;
                            } else if (selectedPosition == 2) {
                                DECruzamento++;
                            } else if (selectedPosition == 3) {
                                DC1Cruzamento++;
                            } else if (selectedPosition == 4) {
                                DC2Cruzamento++;
                            } else if (selectedPosition == 5) {
                                MDCCruzamento++;
                            } else if (selectedPosition == 6) {
                                MC1Cruzamento++;
                            } else if (selectedPosition == 7) {
                                MC2Cruzamento++;
                            } else if (selectedPosition == 8) {
                                MODCruzamento++;
                            } else if (selectedPosition == 9) {
                                MOECruzamento++;
                            } else {
                                PLCruzamento++;
                            }
                            recuperacao();
                        } else {
                            // fazer o if para saber que passou a bola para incrementar
                            if (selectedPosition == 0) {
                                GRPasse++;
                            } else if (selectedPosition == 1) {
                                DDPasse++;
                            } else if (selectedPosition == 2) {
                                DEPasse++;
                            } else if (selectedPosition == 3) {
                                DC1Passe++;
                            } else if (selectedPosition == 4) {
                                DC2Passe++;
                            } else if (selectedPosition == 5) {
                                MDCPasse++;
                            } else if (selectedPosition == 6) {
                                MC1Passe++;
                            } else if (selectedPosition == 7) {
                                MC2Passe++;
                            } else if (selectedPosition == 8) {
                                MODPasse++;
                            } else if (selectedPosition == 9) {
                                MOEPasse++;
                            } else {
                                PLPasse++;
                            }
                        }
                    }
                })
                .show();
    }

    private void cruzamentodialog (String titulo, String[] opcaosc)
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle(titulo)
                .setSingleChoiceItems(opcaosc, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        tmpint = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (tmpint == 0) {
                        } else if (tmpint == 1) {
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (tmpint == 2) {
                            corte();
                        }
                        else
                        {
                            recuperacao();
                        }
                    }
                })
                .show();
    }

    private void rematedialog (String titulo, String[] opcaosc)
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle(titulo)
                .setSingleChoiceItems(opcaosc, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        tmpint = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (tmpint == 0) {
                            actulizarVars(jsonclass);
                            i.putExtra("iniciar", iniciar.getText().toString());
                            i.putExtra("myclass", jsonclass);
                            startActivity(i);
                        } else if (tmpint == 1) {
                            corte();
                        }
                        else
                        {
                             recuperacao();
                        }
                    }
                })
                .show();
    }

    private void corte ()
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle("Escolha o Jogador que cortou a bola?")
                        //Temos de actualizar as equipas para poder ter os jogadores nas posicoes correctas
                .setSingleChoiceItems(EquipaForaNome, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        Toast.makeText(HomeTeam.this, "Corte", Toast.LENGTH_SHORT).show();
                        selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                        Toast.makeText(HomeTeam.this, "Posicao:" + selectedPosition + "Jogador:" + EquipaForaNome[selectedPosition], Toast.LENGTH_SHORT).show();
                        //buscar a lista dos jogadores adversario, atravez do shared preferences
                        //fazer um if para saber se e corte ou recuperacao, e para poder saber a posicao do array para fazer update
                        if (selectedPosition == 0) {
                            GRVcorte++;
                        } else if (selectedPosition == 1) {
                            DDVcorte++;
                        } else if (selectedPosition == 2) {
                            DEVcorte++;
                        } else if (selectedPosition == 3) {
                            DC1Vcorte++;
                        } else if (selectedPosition == 4) {
                            DC2Vcorte++;
                        } else if (selectedPosition == 5) {
                            MDCVcorte++;
                        } else if (selectedPosition == 6) {
                            MC1Vcorte++;
                        } else if (selectedPosition == 7) {
                            MC2Vcorte++;
                        } else if (selectedPosition == 8) {
                            MODVcorte++;
                        } else if (selectedPosition == 9) {
                            MOEVcorte++;
                        } else {
                            PLVcorte++;
                        }
                    }
                })
                .show();
    }

    private void CorteRecuperacao ()
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle("Escolha o evento que aconteceu?")
                .setSingleChoiceItems(eventoperda, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button

                        new AlertDialog.Builder(HomeTeam.this)
                                .setTitle("Escolha o Jogador envolvido no evento?")
                                        //Temos de actualizar as equipas para poder ter os jogadores nas posicoes correctas
                                .setSingleChoiceItems(EquipaForaNome, 0, null)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                        if (selectedPosition == 0)
                                        {
                                            Toast.makeText(HomeTeam.this, "Corte", Toast.LENGTH_SHORT).show();
                                            selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                            // Do something useful withe the position of the selected radio button
                                            Toast.makeText(HomeTeam.this, "Posicao:" + selectedPosition + "Jogador:" + EquipaForaNome[selectedPosition], Toast.LENGTH_SHORT).show();
                                            //buscar a lista dos jogadores adversario, atravez do shared preferences
                                            //fazer um if para saber se e corte ou recuperacao, e para poder saber a posicao do array para fazer update
                                            if (selectedPosition == 0)
                                            {
                                                GRVcorte++;
                                            }
                                            else if (selectedPosition == 1)
                                            {
                                                DDVcorte++;
                                            }
                                            else if (selectedPosition == 2)
                                            {
                                                DEVcorte++;
                                            }
                                            else if (selectedPosition == 3)
                                            {
                                                DC1Vcorte++;
                                            }
                                            else if (selectedPosition == 4)
                                            {
                                                DC2Vcorte++;
                                            }
                                            else if (selectedPosition == 5)
                                            {
                                                MDCVcorte++;
                                            }
                                            else if (selectedPosition == 6)
                                            {
                                                MC1Vcorte++;
                                            }
                                            else if (selectedPosition == 7)
                                            {
                                                MC2Vcorte++;
                                            }
                                            else if (selectedPosition == 8)
                                            {
                                                MODVcorte++;
                                            }
                                            else if (selectedPosition == 9)
                                            {
                                                MOEVcorte++;
                                            }
                                            else
                                            {
                                                PLVcorte++;
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(HomeTeam.this, "Recuperacao", Toast.LENGTH_SHORT).show();
                                            selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                            // Do something useful withe the position of the selected radio button
                                            Toast.makeText(HomeTeam.this, "Posicao:" + selectedPosition + "Jogador:" + EquipaForaNome[selectedPosition], Toast.LENGTH_SHORT).show();
                                            //buscar a lista dos jogadores adversario, atravez do shared preferences
                                            //fazer um if para saber se e corte ou recuperacao, e para poder saber a posicao do array para fazer update
                                            if (selectedPosition == 0)
                                            {
                                                GRVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 1)
                                            {
                                                DDVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 2)
                                            {
                                                DEVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 3)
                                            {
                                                DC1VRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 4)
                                            {
                                                DC2VRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 5)
                                            {
                                                MDCVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 6)
                                            {
                                                MC1VRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 7)
                                            {
                                                MC2VRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 8)
                                            {
                                                MODVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else if (selectedPosition == 9)
                                            {
                                                MOEVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                            else
                                            {
                                                PLVRecuperacao++;
                                                actulizarVars(jsonclass);
                                                i.putExtra("iniciar", iniciar.getText().toString());
                                                i.putExtra("myclass", jsonclass);
                                                startActivity(i);
                                            }
                                        }

                                    }
                                })
                                .show();
                    }
                })
                .show();
    }

    private int alertdialog (String titulo, String[] opcaosc)
    {
        new AlertDialog.Builder(HomeTeam.this)
                .setTitle(titulo)
                .setSingleChoiceItems(opcaosc, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        tmpint = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                    }
                })
                .show();

        return tmpint;
    }

    private void actulizarVars (json jsclass)
    {
        // obter todos os arrays da classe
        GREventos[0] += GRRemate;
        GREventos[1] += GRPasse;
        GREventos[2] += GRPerda;
        GREventos[3] = sharedpreferences.getInt("GRCcorte", 0);//colocar as sp
        GREventos[4] = sharedpreferences.getInt("GRCRecuperacao", 0);
        GREventos[5] += GRCruzamento;
        GREventos[6] += GRLancamento;
        GREventos[7] += GRCanto;
        GREventos[8] += GRLivre;
        GREventos[9] += GRPenalty;
        GREventos[10] += GRGolo;
        jsclass.setGREventos(GREventos);

        GRVcorte += sharedpreferences.getInt("GRVcorte", 0);
        editor.putInt("GRVcorte", GRVcorte);
        GRVRecuperacao += sharedpreferences.getInt("GRVRecuperacao", 0);
        editor.putInt("GRVRecuperacao", GRVRecuperacao);
        tmpEventos = jsclass.getGRVEventos();
        tmpEventos[3] = GRVcorte;
        tmpEventos[4] = GRVRecuperacao;
        jsclass.setGRVEventos(tmpEventos);

        //tmpEventos = jsclass.getDDEventos();
        DDEventos[0] += DDRemate;
        DDEventos[1] += DDPasse;
        DDEventos[2] += DDPerda;
        DDEventos[3] = sharedpreferences.getInt("DDCcorte", 0);//colocar as sp
        DDEventos[4] = sharedpreferences.getInt("DDCRecuperacao", 0);
        DDEventos[5] += DDCruzamento;
        DDEventos[6] += DDLancamento;
        DDEventos[7] += DDCanto;
        DDEventos[8] += DDLivre;
        DDEventos[9] += DDPenalty;
        DDEventos[10] += DDGolo;
        jsclass.setDDEventos(DDEventos);

        DDVcorte += sharedpreferences.getInt("DDVcorte", 0);
        editor.putInt("DDVcorte", DDVcorte);
        DDVRecuperacao += sharedpreferences.getInt("DDVRecuperacao", 0);
        editor.putInt("DDVRecuperacao", DDVRecuperacao);
        tmpEventos = jsclass.getDDVEventos();
        tmpEventos[3] = DDVcorte;
        tmpEventos[4] = DDVRecuperacao;
        jsclass.setDDVEventos(tmpEventos);

        //tmpEventos = jsclass.getDEEventos();
        DEEventos[0] += DERemate;
        DEEventos[1] += DEPasse;
        DEEventos[2] += DEPerda;
        DEEventos[3] = sharedpreferences.getInt("DECcorte", 0);//colocar as sp
        DEEventos[4] = sharedpreferences.getInt("DECRecuperacao", 0);
        DEEventos[5] += DECruzamento;
        DEEventos[6] += DELancamento;
        DEEventos[7] += DECanto;
        DEEventos[8] += DELivre;
        DEEventos[9] += DEPenalty;
        DEEventos[10] += DEGolo;
        jsclass.setDEEventos(DEEventos);

        DEVcorte += sharedpreferences.getInt("DEVcorte", 0);
        editor.putInt("DEVcorte", DEVcorte);
        DEVRecuperacao += sharedpreferences.getInt("DEVRecuperacao", 0);
        editor.putInt("DEVRecuperacao", DEVRecuperacao);
        tmpEventos = jsclass.getDEVEventos();
        tmpEventos[3] = DEVcorte;
        tmpEventos[4] = DEVRecuperacao;
        jsclass.setDEVEventos(tmpEventos);

        //tmpEventos = jsclass.getDC1Eventos();
        DC1Eventos[0] += DC1Remate;
        DC1Eventos[1] += DC1Passe;
        DC1Eventos[2] += DC1Perda;
        DC1Eventos[3] = sharedpreferences.getInt("DC1Ccorte", 0);//colocar as sp
        DC1Eventos[4] = sharedpreferences.getInt("DC1CRecuperacao", 0);
        DC1Eventos[5] += DC1Cruzamento;
        DC1Eventos[6] += DC1Lancamento;
        DC1Eventos[7] += DC1Canto;
        DC1Eventos[8] += DC1Livre;
        DC1Eventos[9] += DC1Penalty;
        DC1Eventos[10] += DC1Golo;
        jsclass.setDC1Eventos(DC1Eventos);

        DC1Vcorte += sharedpreferences.getInt("DC1Vcorte", 0);
        editor.putInt("DC1Vcorte", DC1Vcorte);
        DC1VRecuperacao += sharedpreferences.getInt("DC1VRecuperacao", 0);
        editor.putInt("DC1VRecuperacao", DC1VRecuperacao);
        tmpEventos = jsclass.getDC1VEventos();
        tmpEventos[3] = DC1Vcorte;
        tmpEventos[4] = DC1VRecuperacao;
        jsclass.setDC1VEventos(tmpEventos);

        //tmpEventos = jsclass.getDC2Eventos();
        DC2Eventos[0] += DC2Remate;
        DC2Eventos[1] += DC2Passe;
        DC2Eventos[2] += DC2Perda;
        DC2Eventos[3] = sharedpreferences.getInt("DC2Ccorte", 0);//colocar as sp
        DC2Eventos[4] = sharedpreferences.getInt("DC2CRecuperacao", 0);
        DC2Eventos[5] += DC2Cruzamento;
        DC2Eventos[6] += DC2Lancamento;
        DC2Eventos[7] += DC2Canto;
        DC2Eventos[8] += DC2Livre;
        DC2Eventos[9] += DC2Penalty;
        DC2Eventos[10] += DC2Golo;
        jsclass.setDC2Eventos(DC2Eventos);

        DC2Vcorte += sharedpreferences.getInt("DC2Vcorte", 0);
        editor.putInt("DC2Vcorte", DC2Vcorte);
        DC2VRecuperacao += sharedpreferences.getInt("DC2VRecuperacao", 0);
        editor.putInt("DC2VRecuperacao", DC2VRecuperacao);
        tmpEventos = jsclass.getDC2VEventos();
        tmpEventos[3] = DC2Vcorte;
        tmpEventos[4] = DC2VRecuperacao;
        jsclass.setDC2VEventos(tmpEventos);

        //tmpEventos = jsclass.getMDCEventos();
        MDCEventos[0] += MDCRemate;
        MDCEventos[1] += MDCPasse;
        MDCEventos[2] += MDCPerda;
        MDCEventos[3] = sharedpreferences.getInt("MDCCcorte", 0);//colocar as sp
        MDCEventos[4] = sharedpreferences.getInt("MDCCRecuperacao", 0);
        MDCEventos[5] += MDCCruzamento;
        MDCEventos[6] += MDCLancamento;
        MDCEventos[7] += MDCCanto;
        MDCEventos[8] += MDCLivre;
        MDCEventos[9] += MDCPenalty;
        MDCEventos[10] += MDCGolo;
        jsclass.setMDCEventos(MDCEventos);

        MDCVcorte += sharedpreferences.getInt("MDCVcorte", 0);
        Toast.makeText(this, String.valueOf(MDCVcorte), Toast.LENGTH_LONG).show();
        editor.putInt("MDCVcorte", MDCVcorte);
        MDCVRecuperacao += sharedpreferences.getInt("MDCVRecuperacao", 0);
        editor.putInt("MDCVRecuperacao", MDCVRecuperacao);
        tmpEventos = jsclass.getMDCVEventos();
        tmpEventos[3] = MDCVcorte;
        tmpEventos[4] = MDCVRecuperacao;
        jsclass.setMDCVEventos(tmpEventos);

        //tmpEventos = jsclass.getMC1Eventos();
        MC1Eventos[0] += MC1Remate;
        MC1Eventos[1] += MC1Passe;
        MC1Eventos[2] += MC1Perda;
        MC1Eventos[3] = sharedpreferences.getInt("MC1Ccorte", 0);//colocar as sp
        MC1Eventos[4] = sharedpreferences.getInt("MC1CRecuperacao", 0);
        MC1Eventos[5] += MC1Cruzamento;
        MC1Eventos[6] += MC1Lancamento;
        MC1Eventos[7] += MC1Canto;
        MC1Eventos[8] += MC1Livre;
        MC1Eventos[9] += MC1Penalty;
        MC1Eventos[10] += MC1Golo;
        jsclass.setMC1Eventos(MC1Eventos);

        MC1Vcorte += sharedpreferences.getInt("MC1Vcorte", 0);
        editor.putInt("MC1Vcorte", MC1Vcorte);
        MC1VRecuperacao += sharedpreferences.getInt("MC1VRecuperacao", 0);
        editor.putInt("MC1VRecuperacao", MC1VRecuperacao);
        tmpEventos = jsclass.getMC1VEventos();
        tmpEventos[3] = MC1Vcorte;
        tmpEventos[4] = MC1VRecuperacao;
        jsclass.setMC1VEventos(tmpEventos);

        //tmpEventos = jsclass.getMC1Eventos();
        MC2Eventos[0] += MC2Remate;
        MC2Eventos[1] += MC2Passe;
        MC2Eventos[2] += MC2Perda;
        MC2Eventos[3] = sharedpreferences.getInt("MC2Ccorte", 0);//colocar as sp
        MC2Eventos[4] = sharedpreferences.getInt("MC2CRecuperacao", 0);
        MC2Eventos[5] += MC2Cruzamento;
        MC2Eventos[6] += MC2Lancamento;
        MC2Eventos[7] += MC2Canto;
        MC2Eventos[8] += MC2Livre;
        MC2Eventos[9] += MC2Penalty;
        MC2Eventos[10] += MC2Golo;
        jsclass.setMC2Eventos(MC2Eventos);

        MC2Vcorte += sharedpreferences.getInt("MC2Vcorte", 0);
        editor.putInt("MC2Vcorte", MC2Vcorte);
        MC2VRecuperacao += sharedpreferences.getInt("MC2VRecuperacao", 0);
        editor.putInt("MC2VRecuperacao", MC2VRecuperacao);
        tmpEventos = jsclass.getMC2VEventos();
        tmpEventos[3] = MC2Vcorte;
        tmpEventos[4] = MC2VRecuperacao;
        jsclass.setMC2VEventos(tmpEventos);

        //tmpEventos = jsclass.getMODEventos();
        MODEventos[0] += MODRemate;
        MODEventos[1] += MODPasse;
        MODEventos[2] += MODPerda;
        MODEventos[3] = sharedpreferences.getInt("MODCcorte", 0);//colocar as sp
        MODEventos[4] = sharedpreferences.getInt("MODCRecuperacao", 0);
        MODEventos[5] += MODCruzamento;
        MODEventos[6] += MODLancamento;
        MODEventos[7] += MODCanto;
        MODEventos[8] += MODLivre;
        MODEventos[9] += MODPenalty;
        MODEventos[10] += MODGolo;
        jsclass.setMODEventos(MODEventos);

        MODVcorte += sharedpreferences.getInt("MODVcorte", 0);
        editor.putInt("MODVcorte", MODVcorte);
        MODVRecuperacao += sharedpreferences.getInt("MODVRecuperacao", 0);
        editor.putInt("MODVRecuperacao", MODVRecuperacao);
        tmpEventos = jsclass.getMODVEventos();
        tmpEventos[3] = MODVcorte;
        tmpEventos[4] = MODVRecuperacao;
        jsclass.setMODVEventos(tmpEventos);

        //tmpEventos = jsclass.getMOEEventos();
        MOEEventos[0] += MOERemate;
        MOEEventos[1] += MOEPasse;
        MOEEventos[2] += MOEPerda;
        MOEEventos[3] = sharedpreferences.getInt("MOECcorte", 0);//colocar as sp
        MOEEventos[4] = sharedpreferences.getInt("MOECRecuperacao", 0);
        MOEEventos[5] += MOECruzamento;
        MOEEventos[6] += MOELancamento;
        MOEEventos[7] += MOECanto;
        MOEEventos[8] += MOELivre;
        MOEEventos[9] += MOEPenalty;
        MOEEventos[10] += MOEGolo;
        jsclass.setMOEEventos(MOEEventos);

        MOEVcorte += sharedpreferences.getInt("MOEVcorte", 0);
        editor.putInt("MOEVcorte", MOEVcorte);
        MOEVRecuperacao += sharedpreferences.getInt("MOEVRecuperacao", 0);
        editor.putInt("MOEVRecuperacao", MOEVRecuperacao);
        tmpEventos = jsclass.getMOEVEventos();
        tmpEventos[3] = MOEVcorte;
        tmpEventos[4] = MOEVRecuperacao;
        jsclass.setMOEVEventos(tmpEventos);

        //tmpEventos = jsclass.getPLEventos();
        PLEventos[0] += PLRemate;
        PLEventos[1] += PLPasse;
        PLEventos[2] += PLPerda;
        PLEventos[3] = sharedpreferences.getInt("PLCcorte", 0);//colocar as sp
        PLEventos[4] = sharedpreferences.getInt("PLCRecuperacao", 0);
        PLEventos[5] += PLCruzamento;
        PLEventos[6] += PLLancamento;
        PLEventos[7] += PLCanto;
        PLEventos[8] += PLLivre;
        PLEventos[9] += PLPenalty;
        PLEventos[10] += PLGolo;
        jsclass.setPLEventos(PLEventos);

        PLVcorte += sharedpreferences.getInt("PLVcorte", 0);
        editor.putInt("PLVcorte", PLVcorte);
        PLVRecuperacao += sharedpreferences.getInt("PLVRecuperacao", 0);
        editor.putInt("PLVRecuperacao", PLVRecuperacao);
        tmpEventos = jsclass.getPLVEventos();
        tmpEventos[3] = PLVcorte;
        tmpEventos[4] = PLVRecuperacao;
        jsclass.setPLVEventos(tmpEventos);

        GRRemate = 0; GRPasse = 0; GRPerda = 0; GRCruzamento = 0; GRLancamento = 0; GRCanto = 0; GRLivre = 0; GRPenalty = 0; GRGolo = 0;
        DDRemate = 0; DDPasse = 0; DDPerda = 0; DDCruzamento = 0; DDLancamento = 0; DDCanto = 0; DDLivre = 0; DDPenalty = 0; DDGolo = 0;
        DERemate = 0; DEPasse = 0; DEPerda = 0; DECruzamento = 0; DELancamento = 0; DECanto = 0; DELivre = 0; DEPenalty = 0; DEGolo = 0;
        DC1Remate = 0; DC1Passe = 0; DC1Perda = 0; DC1Cruzamento = 0; DC1Lancamento = 0; DC1Canto = 0; DC1Livre = 0; DC1Penalty = 0; DC1Golo = 0;
        DC2Remate = 0; DC2Passe = 0; DC2Perda = 0; DC2Cruzamento = 0; DC2Lancamento = 0; DC2Canto = 0; DC2Livre = 0; DC2Penalty = 0; DC2Golo = 0;
        MDCRemate = 0; MDCPasse = 0; MDCPerda = 0; MDCCruzamento = 0; MDCLancamento = 0; MDCCanto = 0; MDCLivre = 0; MDCPenalty = 0; MDCGolo = 0;
        MC1Remate = 0; MC1Passe = 0; MC1Perda = 0; MC1Cruzamento = 0; MC1Lancamento = 0; MC1Canto = 0; MC1Livre = 0; MC1Penalty = 0; MC1Golo = 0;
        MC2Remate = 0; MC2Passe = 0; MC2Perda = 0; MC2Cruzamento = 0; MC2Lancamento = 0; MC2Canto = 0; MC2Livre = 0; MC2Penalty = 0; MC2Golo = 0;
        MODRemate = 0; MODPasse = 0; MODPerda = 0; MODCruzamento = 0; MODLancamento = 0; MODCanto = 0; MODLivre = 0; MODPenalty = 0; MODGolo = 0;
        MOERemate = 0; MOEPasse = 0; MOEPerda = 0; MOECruzamento = 0; MOELancamento = 0; MOECanto = 0; MOELivre = 0; MOEPenalty = 0; MOEGolo = 0;
        PLRemate = 0; PLPasse = 0; PLPerda = 0; PLCruzamento = 0; PLLancamento = 0; PLCanto = 0; PLLivre = 0; PLPenalty = 0; PLGolo = 0;

        GRVcorte = 0; DDVcorte = 0;DEVcorte = 0; DC1Vcorte = 0; DC2Vcorte = 0; MDCVcorte = 0; MC1Vcorte = 0; MC2Vcorte = 0; MODVcorte = 0; MOEVcorte = 0; PLVcorte = 0;
        GRVRecuperacao = 0; DDVRecuperacao = 0;DEVRecuperacao = 0; DC1VRecuperacao = 0; DC2VRecuperacao = 0; MDCVRecuperacao = 0; MC1VRecuperacao = 0; MC2VRecuperacao = 0; MODVRecuperacao = 0; MOEVRecuperacao = 0; PLVRecuperacao = 0;

        tempo();

        //editor.commit();
    }

    private void butoes(int i) {

        if (i == 1)
        {
            //activar todos os bottoes
            //iniciar.setEnabled(true);
            passe.setEnabled(true);
            cruzamento.setEnabled(true);
            penalty.setEnabled(true);
            perda.setEnabled(true);
            livre.setEnabled(true);
            lancamento.setEnabled(true);
            subtituicao.setEnabled(true);
            canto.setEnabled(true);
            golo.setEnabled(true);
            remate.setEnabled(true);
        }
        else
        {
            //desactivar todos os bottoes
            //iniciar.setEnabled(false);
            passe.setEnabled(false);
            cruzamento.setEnabled(false);
            penalty.setEnabled(false);
            perda.setEnabled(false);
            livre.setEnabled(false);
            lancamento.setEnabled(false);
            subtituicao.setEnabled(false);
            canto.setEnabled(false);
            golo.setEnabled(false);
            remate.setEnabled(false);
        }

    }

    private void preencherequipa() {

        //buscar a shered preferences dos nome dos titulares e bancos para montar a equipa.
        tmp = sharedpreferences.getString("EquipaCasaNome", "");
        StringTokenizer st = new StringTokenizer(tmp,",");
        tmp = sharedpreferences.getString("EquipaCasaIDJ", "");
        StringTokenizer st2 = new StringTokenizer(tmp,",");

        if (!tmp.equals("")) {
            for (int i = 0; i < EquipaCasaNome.length; i++) {

                if (i == 0) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    GR.setText(EquipaCasaNome[i]);

                } else if (i == 1) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    DD.setText(EquipaCasaNome[i]);

                } else if (i == 2) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    DE.setText(EquipaCasaNome[i]);

                } else if (i == 3) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    DC1.setText(EquipaCasaNome[i]);

                } else if (i == 4) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    DC2.setText(EquipaCasaNome[i]);

                } else if (i == 5) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    MDC.setText(EquipaCasaNome[i]);

                } else if (i == 6) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    MC1.setText(EquipaCasaNome[i]);

                } else if (i == 7) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    MC2.setText(EquipaCasaNome[i]);

                } else if (i == 8) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    MOD.setText(EquipaCasaNome[i]);

                } else if (i == 9) {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    MOE.setText(EquipaCasaNome[i]);

                } else {

                    EquipaCasaNome[i] = st.nextToken();
                    EquipaCasaIDJ[i] = st2.nextToken();
                    PL.setText(EquipaCasaNome[i]);

                }

            }

        }

        tmp = sharedpreferences.getString("SuplentesCasa", "");
        st = new StringTokenizer(tmp,",");
        tmp = sharedpreferences.getString("SuplentesCasaIDJ", "");
        st2 = new StringTokenizer(tmp,",");

        if(!tmp.equals(""))
        {
            for (int i = 0; i < st.countTokens(); i++) {

                SuplentesCasa.add(st.nextToken());
                SuplentesCasaIDJ.add(st2.nextToken());

            }
        }

        //Preencher a equipa visitante
        tmp = sharedpreferences.getString("EquipaForaNome", "");
        st = new StringTokenizer(tmp,",");
        tmp = sharedpreferences.getString("EquipaForaIDJ", "");
        st2 = new StringTokenizer(tmp,",");

        if (!tmp.equals("")) {
            for (int i = 0; i < EquipaForaNome.length; i++) {

                if (i == 0) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 1) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 2) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 3) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 4) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 5) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 6) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 7) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 8) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else if (i == 9) {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                } else {

                    EquipaForaNome[i] = st.nextToken();
                    EquipaForaIDJ[i] = st2.nextToken();

                }

            }
        }

        tmp = sharedpreferences.getString("SuplentesFora", "");
        st = new StringTokenizer(tmp,",");
        tmp = sharedpreferences.getString("SuplentesForaIDJ", "");
        st2 = new StringTokenizer(tmp,",");

        if(!tmp.equals(""))
        {
            for (int i = 0; i < st.countTokens(); i++) {

                SuplentesFora.add(st.nextToken());
                SuplentesForaIDJ.add(st2.nextToken());

            }
        }
    }

    private int[] trocacarjogador(int j1, int j2, int[] Jarray, String nometmp, String corte, String recuperacao)
    {
        //1o trocar os nomes e os numeros da lista
        tmp = EquipaCasaNome[j1];
        EquipaCasaNome[j1] = EquipaCasaNome[j2];
        EquipaCasaNome[j2] = tmp;

        tmp = EquipaCasaIDJ[j1];
        EquipaCasaIDJ[j1] = EquipaCasaIDJ[j2];
        EquipaCasaIDJ[j2] = tmp;

        //activar os botoes
        if (iniciar.getText().toString().equals("Confimar") || iniciar.getText().toString().equals("Ini. 1ª Parte") ||iniciar.getText().toString().equals("Ini. 2ª Parte")) {}
        else
            butoes(1);

        //agora trocar os dados das suas repectivas posicoes
        if (j1 == 0)
        {
            GREventos[0] += GRRemate;
            GREventos[1] += GRPasse;
            GREventos[2] += GRPerda;
            //buscar sp e trocar posicao
            GREventos[3] = sharedpreferences.getInt("GRCcorte",0);
            GREventos[4] = sharedpreferences.getInt("GRCRecuperacao",0);
            GREventos[5] += GRCruzamento;
            GREventos[6] += GRLancamento;
            GREventos[7] += GRCanto;
            GREventos[8] += GRLivre;
            GREventos[9] += GRPenalty;
            GREventos[10] += GRGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            GRRemate = 0; GRPasse = 0; GRPerda = 0; GRCruzamento = 0; GRLancamento = 0; GRCanto = 0; GRLivre = 0; GRPenalty = 0; GRGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = GREventos;
            GREventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("GRCcorte",0);
            editor.putInt("GRCcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("GRCRecuperacao",0);
            editor.putInt("GRCRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = GR.getText().toString();
            GR.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 1)
        {
            DDEventos[0] += DDRemate;
            DDEventos[1] += DDPasse;
            DDEventos[2] += DDPerda;
            DDEventos[3] = sharedpreferences.getInt("DDCcorte",0);
            DDEventos[4] = sharedpreferences.getInt("DDCRecuperacao",0);
            DDEventos[5] += DDCruzamento;
            DDEventos[6] += DDLancamento;
            DDEventos[7] += DDCanto;
            DDEventos[8] += DDLivre;
            DDEventos[9] += DDPenalty;
            DDEventos[10] += DDGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            DDRemate = 0; DDPasse = 0; DDPerda = 0; DDCruzamento = 0; DDLancamento = 0; DDCanto = 0; DDLivre = 0; DDPenalty = 0; DDGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = DDEventos;
            DDEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("DDCcorte",0);
            editor.putInt("DDCcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("DDCRecuperacao",0);
            editor.putInt("DDCRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = DD.getText().toString();
            DD.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 2)
        {
            DEEventos[0] += DERemate;
            DEEventos[1] += DEPasse;
            DEEventos[2] += DEPerda;
            DEEventos[3] = sharedpreferences.getInt("DECcorte",0);
            DEEventos[4] = sharedpreferences.getInt("DECRecuperacao",0);
            DEEventos[5] += DECruzamento;
            DEEventos[6] += DELancamento;
            DEEventos[7] += DECanto;
            DEEventos[8] += DELivre;
            DEEventos[9] += DEPenalty;
            DEEventos[10] += DEGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            DERemate = 0; DEPasse = 0; DEPerda = 0; DECruzamento = 0; DELancamento = 0; DECanto = 0; DELivre = 0; DEPenalty = 0; DEGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = DEEventos;
            DEEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("DECcorte",0);
            editor.putInt("DECcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("DECRecuperacao",0);
            editor.putInt("DECRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = DE.getText().toString();
            DE.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 3)
        {
            DC1Eventos[0] += DC1Remate;
            DC1Eventos[1] += DC1Passe;
            DC1Eventos[2] += DC1Perda;
            DC1Eventos[3] = sharedpreferences.getInt("DC1Ccorte",0);
            DC1Eventos[4] = sharedpreferences.getInt("DC1CRecuperacao",0);
            DC1Eventos[5] += DC1Cruzamento;
            DC1Eventos[6] += DC1Lancamento;
            DC1Eventos[7] += DC1Canto;
            DC1Eventos[8] += DC1Livre;
            DC1Eventos[9] += DC1Penalty;
            DC1Eventos[10] += DC1Golo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            DC1Remate = 0; DC1Passe = 0; DC1Perda = 0; DC1Cruzamento = 0; DC1Lancamento = 0; DC1Canto = 0; DC1Livre = 0; DC1Penalty = 0; DC1Golo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = DC1Eventos;
            DC1Eventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("DC1Ccorte",0);
            editor.putInt("DC1Ccorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("DC1CRecuperacao",0);
            editor.putInt("DC1CRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = DC1.getText().toString();
            DC1.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 4)
        {
            DC2Eventos[0] += DC2Remate;
            DC2Eventos[1] += DC2Passe;
            DC2Eventos[2] += DC2Perda;
            DC2Eventos[3] = sharedpreferences.getInt("DC2Ccorte",0);
            DC2Eventos[4] = sharedpreferences.getInt("DC2CRecuperacao",0);
            DC2Eventos[5] += DC2Cruzamento;
            DC2Eventos[6] += DC2Lancamento;
            DC2Eventos[7] += DC2Canto;
            DC2Eventos[8] += DC2Livre;
            DC2Eventos[9] += DC2Penalty;
            DC2Eventos[10] += DC2Golo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            DC2Remate = 0; DC2Passe = 0; DC2Perda = 0; DC2Cruzamento = 0; DC2Lancamento = 0; DC2Canto = 0; DC2Livre = 0; DC2Penalty = 0; DC2Golo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = DC2Eventos;
            DC2Eventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("DC2Ccorte",0);
            editor.putInt("DC2Ccorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("DC2CRecuperacao",0);
            editor.putInt("DC2CRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = DC2.getText().toString();
            DC2.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 5)
        {
            MDCEventos[0] += MDCRemate;
            MDCEventos[1] += MDCPasse;
            MDCEventos[2] += MDCPerda;
            MDCEventos[3] = sharedpreferences.getInt("MDCCcorte",0);
            MDCEventos[4] = sharedpreferences.getInt("MDCCRecuperacao",0);
            MDCEventos[5] += MDCCruzamento;
            MDCEventos[6] += MDCLancamento;
            MDCEventos[7] += MDCCanto;
            MDCEventos[8] += MDCLivre;
            MDCEventos[9] += MDCPenalty;
            MDCEventos[10] += MDCGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            MDCRemate = 0; MDCPasse = 0; MDCPerda = 0; MDCCruzamento = 0; MDCLancamento = 0; MDCCanto = 0; MDCLivre = 0; MDCPenalty = 0; MDCGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = MDCEventos;
            MDCEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("MDCCcorte",0);
            editor.putInt("MDCCcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("MDCCRecuperacao",0);
            editor.putInt("MDCCRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = MDC.getText().toString();
            MDC.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 6)
        {
            MC1Eventos[0] += MC1Remate;
            MC1Eventos[1] += MC1Passe;
            MC1Eventos[2] += MC1Perda;
            MC1Eventos[3] = sharedpreferences.getInt("MC1Ccorte",0);
            MC1Eventos[4] = sharedpreferences.getInt("MC1CRecuperacao",0);
            MC1Eventos[5] += MC1Cruzamento;
            MC1Eventos[6] += MC1Lancamento;
            MC1Eventos[7] += MC1Canto;
            MC1Eventos[8] += MC1Livre;
            MC1Eventos[9] += MC1Penalty;
            MC1Eventos[10] += MC1Golo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            MC1Remate = 0; MC1Passe = 0; MC1Perda = 0; MC1Cruzamento = 0; MC1Lancamento = 0; MC1Canto = 0; MC1Livre = 0; MC1Penalty = 0; MC1Golo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = MC1Eventos;
            MC1Eventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("MC1Ccorte",0);
            editor.putInt("MC1Ccorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("MC1CRecuperacao",0);
            editor.putInt("MC1CRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = MC1.getText().toString();
            MC1.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 7)
        {
            MC2Eventos[0] += MC2Remate;
            MC2Eventos[1] += MC2Passe;
            MC2Eventos[2] += MC2Perda;
            MC2Eventos[3] = sharedpreferences.getInt("MC2Ccorte",0);
            MC2Eventos[4] = sharedpreferences.getInt("MC2CRecuperacao",0);
            MC2Eventos[5] += MC2Cruzamento;
            MC2Eventos[6] += MC2Lancamento;
            MC2Eventos[7] += MC2Canto;
            MC2Eventos[8] += MC2Livre;
            MC2Eventos[9] += MC2Penalty;
            MC2Eventos[10] += MC2Golo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            MC2Remate = 0; MC2Passe = 0; MC2Perda = 0; MC2Cruzamento = 0; MC2Lancamento = 0; MC2Canto = 0; MC2Livre = 0; MC2Penalty = 0; MC2Golo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = MC2Eventos;
            MC2Eventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("MC2Ccorte",0);
            editor.putInt("MC2Ccorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("MC2CRecuperacao",0);
            editor.putInt("MC2CRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = MC2.getText().toString();
            MC2.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 8)
        {
            MODEventos[0] += MODRemate;
            MODEventos[1] += MODPasse;
            MODEventos[2] += MODPerda;
            MODEventos[3] = sharedpreferences.getInt("MODCcorte",0);
            MODEventos[4] = sharedpreferences.getInt("MODCRecuperacao",0);
            MODEventos[5] += MODCruzamento;
            MODEventos[6] += MODLancamento;
            MODEventos[7] += MODCanto;
            MODEventos[8] += MODLivre;
            MODEventos[9] += MODPenalty;
            MODEventos[10] += MODGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            MODRemate = 0; MODPasse = 0; MODPerda = 0; MODCruzamento = 0; MODLancamento = 0; MODCanto = 0; MODLivre = 0; MODPenalty = 0; MODGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = MODEventos;
            MODEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("MODCcorte",0);
            editor.putInt("MODCcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("MODCRecuperacao",0);
            editor.putInt("MODCRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = MOD.getText().toString();
            MOD.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 9)
        {
            MOEEventos[0] += MOERemate;
            MOEEventos[1] += MOEPasse;
            MOEEventos[2] += MOEPerda;
            MOEEventos[3] = sharedpreferences.getInt("MOECcorte",0);
            MOEEventos[4] = sharedpreferences.getInt("MOECRecuperacao",0);
            MOEEventos[5] += MOECruzamento;
            MOEEventos[6] += MOELancamento;
            MOEEventos[7] += MOECanto;
            MOEEventos[8] += MOELivre;
            MOEEventos[9] += MOEPenalty;
            MOEEventos[10] += MOEGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            MOERemate = 0; MOEPasse = 0; MOEPerda = 0; MOECruzamento = 0; MOELancamento = 0; MOECanto = 0; MOELivre = 0; MOEPenalty = 0; MOEGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = MOEEventos;
            MOEEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("MOECcorte",0);
            editor.putInt("MOECcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("MOECRecuperacao",0);
            editor.putInt("MOECRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = MOE.getText().toString();
            MOE.setText(nometmp);

            return tmpEventos;
        }
        else
        {
            PLEventos[0] += PLRemate;
            PLEventos[1] += PLPasse;
            PLEventos[2] += PLPerda;
            PLEventos[3] = sharedpreferences.getInt("PLCcorte",0);
            PLEventos[4] = sharedpreferences.getInt("PLCRecuperacao",0);
            PLEventos[5] += PLCruzamento;
            PLEventos[6] += PLLancamento;
            PLEventos[7] += PLCanto;
            PLEventos[8] += PLLivre;
            PLEventos[9] += PLPenalty;
            PLEventos[10] += PLGolo;

            //Zerar as variaveis pq o jogador esta numa nova posicao
            PLRemate = 0; PLPasse = 0; PLPerda = 0; PLCruzamento = 0; PLLancamento = 0; PLCanto = 0; PLLivre = 0; PLPenalty = 0; PLGolo = 0;

            //colocar os valores no array temporario e substituir o valor pelo do novo jogador
            tmpEventos = PLEventos;
            PLEventos = Jarray;

            //trocar os valores na sp cosante a nova posicao do jogador
            tmpint = sharedpreferences.getInt("PLCcorte",0);
            editor.putInt("PLCcorte", sharedpreferences.getInt(corte,0));
            editor.putInt(corte, tmpint);
            tmpint = sharedpreferences.getInt("PLCRecuperacao",0);
            editor.putInt("PLCRecuperacao", sharedpreferences.getInt(recuperacao,0));
            editor.putInt(recuperacao, tmpint);

            editor.commit();

            //trocar nomes no botao
            tmp = PL.getText().toString();
            PL.setText(nometmp);

            return tmpEventos;
        }
    }
}
