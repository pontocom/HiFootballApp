package com.example.brunobarros.hifootball;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class AnnotateEvents extends AppCompatActivity {

    Button golo, agolo, remate, passe, perda, btpg, btlg, btfalta, btmd, cruzamento, lancamento, lancamento2, canto, canto2, livre, penalty, troca, btrf, btrf2, btrb,
            btrb2, btbf, btbf2, passe2, passe3, passe4, passe5, perda2, perda3, perda4, perda5, GR, DD, DE, DC1, DC2, MDC, MC1, MC2, MOE, MOD, PL,
            iniciar, subtituicao, camarelo, cvermelho, btpbaliza, btpbaliza2, lancamentg, lancamentg2, cantog, cantog2;
    String[] EquipaCasaNome = new String[11];
    ArrayList<String> SuplentesCasa = new ArrayList<String>();
    String[] EquipaCasaIDJ = new String[11];
    ArrayList<String> SuplentesCasaIDJ = new ArrayList<String>();
    int GRRemate = 0, GRPasse = 0, GRPerda = 0, GRCorte = 0, GRRecuperacao = 0, GRCruzamento = 0, GRLancamento = 0, GRCanto = 0, GRLivre = 0, GRPenalty = 0, GRGolo = 0;
    int DDRemate = 1, DDPasse = 1, DDPerda = 1, DDCorte = 1, DDRecuperacao = 1, DDCruzamento = 1, DDLancamento = 1, DDCanto = 1, DDLivre = 1, DDPenalty = 1, DDGolo = 1;
    int DERemate = 2, DEPasse = 2, DEPerda = 2, DECorte = 2, DERecuperacao = 2, DECruzamento = 2, DELancamento = 0, DECanto = 2, DELivre = 2, DEPenalty = 2, DEGolo = 2;
    int DC1Remate = 3, DC1Passe = 3, DC1Perda = 3, DC1Corte = 3, DC1Recuperacao = 3, DC1Cruzamento = 3, DC1Lancamento = 3, DC1Canto = 3, DC1Livre = 3, DC1Penalty = 3, DC1Golo = 3;
    int DC2Remate = 4, DC2Passe = 4, DC2Perda = 4, DC2Corte = 4, DC2Recuperacao = 4, DC2Cruzamento = 4, DC2Lancamento = 4, DC2Canto = 4, DC2Livre = 4, DC2Penalty = 4, DC2Golo = 4;
    int MDCRemate = 5, MDCPasse = 5, MDCPerda = 5, MDCCorte = 5, MDCRecuperacao = 5, MDCCruzamento = 5, MDCLancamento = 5, MDCCanto = 5, MDCLivre = 5, MDCPenalty = 5, MDCGolo = 5;
    int MC1Remate = 6, MC1Passe = 6, MC1Perda = 6, MC1Corte = 6, MC1Recuperacao = 6, MC1Cruzamento = 6, MC1Lancamento = 6, MC1Canto = 6, MC1Livre = 6, MC1Penalty = 6, MC1Golo = 6;
    int MC2Remate = 7, MC2Passe = 7, MC2Perda = 7, MC2Corte = 7, MC2Recuperacao = 7, MC2Cruzamento = 7, MC2Lancamento = 7, MC2Canto = 7, MC2Livre = 7, MC2Penalty = 7, MC2Golo = 7;
    int MODRemate = 8, MODPasse = 8, MODPerda = 8, MODCorte = 8, MODRecuperacao = 8, MODCruzamento = 8, MODLancamento = 8, MODCanto = 8, MODLivre = 8, MODPenalty = 8, MODGolo = 8;
    int MOERemate = 9, MOEPasse = 9, MOEPerda = 9, MOECorte = 9, MOERecuperacao = 9, MOECruzamento = 9, MOELancamento = 9, MOECanto = 9, MOELivre = 9, MOEPenalty = 9, MOEGolo = 9;
    int PLRemate = 10, PLPasse = 10, PLPerda = 10, PLCorte = 10, PLRecuperacao = 10, PLCruzamento = 10, PLLancamento = 10, PLCanto = 10, PLLivre = 10, PLPenalty = 10, PLGolo = 10;
    String method, tmp = "", escolha1 = "", resej, evento = "", idev, idj = "", modo="M O", idP = "";
    int jogador1 = -1, jogador2 = -1, tmpint, selectedPosition;
    json jsonclass;
    JSONArray jsonArray;
    JSONObject jsonres;
    int[] tmpEventos = new int[11];
    int[] tmpEventosP = new int[11];
    RelativeLayout relativeLayoutEC;
    Context cxt;
    State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipada_casa);

        //Toast.makeText(this, "On Create. IdJ: " + idj, Toast.LENGTH_LONG).show();
        cxt = this;
        state = new State();

        //declarar a pagina para poder alterar o fundo
        relativeLayoutEC = (RelativeLayout) findViewById(R.id.relativeLayoutEC);

        //declar os showButtons
        golo = (Button) findViewById(R.id.btgolo);
        remate = (Button) findViewById(R.id.btremate);
        passe = (Button) findViewById(R.id.btpasse);
        passe2 = (Button) findViewById(R.id.btpasse2);
        passe3 = (Button) findViewById(R.id.btpasse3);
        passe4 = (Button) findViewById(R.id.btpasse4);
        passe5 = (Button) findViewById(R.id.btpasse5);
        perda = (Button) findViewById(R.id.btperda);
        perda2 = (Button) findViewById(R.id.btpb2);
        perda3 = (Button) findViewById(R.id.btpb3);
        perda4 = (Button) findViewById(R.id.btpb4);
        perda5 = (Button) findViewById(R.id.btpb5);
        btbf = (Button) findViewById(R.id.btbf);
        btbf2 = (Button) findViewById(R.id.btbf2);
        cruzamento = (Button) findViewById(R.id.btcruzamento);
        lancamento = (Button) findViewById(R.id.btlancamento);
        lancamento2 = (Button) findViewById(R.id.btlancamento2);
        canto = (Button) findViewById(R.id.btcanto);
        canto2 = (Button) findViewById(R.id.btcanto2);
        livre = (Button) findViewById(R.id.btlivre);
        penalty = (Button) findViewById(R.id.btpenalti);
        troca = (Button) findViewById(R.id.bttroca);
        iniciar = (Button) findViewById(R.id.btinicio);
        subtituicao = (Button) findViewById(R.id.btsubstituicao);
        btpg = (Button) findViewById(R.id.btpg);
        btlg = (Button) findViewById(R.id.btlg);
        btfalta = (Button) findViewById(R.id.btfalta);
        agolo = (Button) findViewById(R.id.btagolo);
        btrb = (Button) findViewById(R.id.btrb);
        btrb2 = (Button) findViewById(R.id.btrb2);
        btrf = (Button) findViewById(R.id.btrf);
        btrf2 = (Button) findViewById(R.id.btrf2);
        btmd = (Button) findViewById(R.id.btmd);
        camarelo = (Button) findViewById(R.id.btamarelo);
        cvermelho = (Button) findViewById(R.id.btvermelho);
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
        btpbaliza =  (Button) findViewById(R.id.btpbaliza);
        btpbaliza2 =  (Button) findViewById(R.id.btpbaliza2);
        lancamentg = (Button) findViewById(R.id.btlancamentog);
        lancamentg2 = (Button) findViewById(R.id.btlancamentog2);
        cantog = (Button) findViewById(R.id.btcantog);
        cantog2 = (Button) findViewById(R.id.btcantog2);

        if (iniciar.getText().equals("Ini. Parte")) {
            //Obter as listas dos jogadores que vao jogar jogo, na proxima versao os dados devem vir da pagina criar evento.
            // get list of player playing this game
            method = "List_JogadorparaJogo";
            PHPConnection phpc = new PHPConnection(this, jsonclass);

            try {
                Bundle extras = getIntent().getExtras();
                escolha1 = extras.getString("IDES", "");
                idev = extras.getString("IDE", "");
                if (escolha1.equals("")){}
                else {
                    resej = phpc.execute(method, escolha1).get();

                    jsonArray = new JSONArray(resej);
                    // int opcupadodc = 0;
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
                                    GR.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" + jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("4")) {
                                    EquipaCasaNome[3] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[3] = jsonArray.getJSONObject(i).getString("Id");
                                    DC1.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("2")) {
                                    EquipaCasaNome[1] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[1] = jsonArray.getJSONObject(i).getString("Id");
                                    DD.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("3")) {
                                    EquipaCasaNome[2] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[2] = jsonArray.getJSONObject(i).getString("Id");
                                    DE.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("6")) {
                                    EquipaCasaNome[5] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[5] = jsonArray.getJSONObject(i).getString("Id");
                                    MDC.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("7")) {
                                    EquipaCasaNome[6] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[6] = jsonArray.getJSONObject(i).getString("Id");
                                    MC1.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("8")) {
                                    EquipaCasaNome[7] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[7] = jsonArray.getJSONObject(i).getString("Id");
                                    MC2.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("10")) {
                                    EquipaCasaNome[9] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[9] = jsonArray.getJSONObject(i).getString("Id");
                                    MOE.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("9")) {
                                    EquipaCasaNome[8] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[8] = jsonArray.getJSONObject(i).getString("Id");
                                    MOD.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else if (jsonArray.getJSONObject(i).getString("PCampo").equals("11")) {
                                    EquipaCasaNome[10] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[10] = jsonArray.getJSONObject(i).getString("Id");
                                    PL.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                } else {
                                    EquipaCasaNome[4] = jsonArray.getJSONObject(i).getString("Nome");
                                    EquipaCasaIDJ[4] = jsonArray.getJSONObject(i).getString("Id");
                                    DC2.setText(jsonArray.getJSONObject(i).getString("Numero") + "\n" +jsonArray.getJSONObject(i).getString("Nome"));
                                }
                            } else {
                                SuplentesCasa.add(jsonArray.getJSONObject(i).getString("Nome"));
                                SuplentesCasaIDJ.add(jsonArray.getJSONObject(i).getString("Id"));
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
            showButtons(false);
        }

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iniciar.getText().toString().equals("Confimar"))
                {
                    iniciar.setText("Ini. 1ª Parte");
                }
                else if (iniciar.getText().toString().equals("Ini. Parte"))
                {
                    iniciar.setText("Fim Parte");
                    showButtons(true);
                    md();
                    // inserir na ba que ouve saida de bola da outra equipa
                }
                else if (iniciar.getText().toString().equals("Fim Parte"))
                {
                    iniciar.setText("Ini. Parte");
                    showButtons(false);
                }
                else if (iniciar.getText().toString().equals("Ini. 2ª Parte"))
                {
                    iniciar.setText("Fim 2ª Parte");
                    showButtons(true);
                }
                else if (iniciar.getText().toString().equals("Fim 2ª Parte"))
                {
                    iniciar.setText("Terminou");
                    showButtons(false);
                }
                else if (iniciar.getText().toString().equals("Terminou"))
                {
                    //enviar para a pagina inicial
                    startActivity(new Intent(cxt, InitialPage.class));
                }
            }
        });

        golo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals("")) {
                    //inserireventotecnico("11", cxt);
                    state.setState(getApplicationContext ());
                    startActivityForResult(new Intent(AnnotateEvents.this, Pop.class).putExtra("IdJ",idj),2);
                }
            }
        });

        remate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(cxt, "IdJ: " + idj, Toast.LENGTH_SHORT).show();
                if (!idj.equals("")) {
                    //inserireventotecnico("1", cxt);
                    state.setState(getApplicationContext ());
                    startActivityForResult(new Intent(AnnotateEvents.this, Pop.class).putExtra("IdJ",idj),2);
                }
            }
        });

        cruzamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals(""))
                    inserireventotecnico("6", cxt);
            }
        });

        livre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals("")) {
                    inserireventotecnico("13", cxt);
                    escolherexecutante();
                }
            }
        });

        penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals("")) {
                    inserireventotecnico("12", cxt);
                    escolherexecutante();
                }
            }
        });

        troca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evento = "troca";
                //Disable todos os botoes
                showButtons(false);
            }
        });

        btpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals("")) {
                    inserireventotecnico("12", cxt);
                    escolherexecutante();
                    /*
                    if (modo.equals("M D")) {
                        md();
                    }*/
                }
            }
        });

        btlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!idj.equals("")) {
                    //inserireventotecnico("13", cxt);
                    //escolherexecutante();
                    if (modo.equals("M D")) {
                        md();
                    }
                }*/
                // terminar o jogo e enviar para a pagina inicial
                startActivity(new Intent(cxt, InitialPage.class));
            }
        });

        btfalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!idj.equals("")) {
                    if (btfalta.getText().equals("Foul O")) {
                        inserireventotecnico("16", cxt);
                        if (modo.equals("M O")) {
                            md();
                        }
                    }
                    else
                        inserireventotecnico("17", cxt);
                }*/
                //Toast.makeText(cxt, "IdJ: " + idj, Toast.LENGTH_SHORT).show();
                if (!idj.equals("")) {
                    //inserireventotecnico("1", cxt);
                    state.setState(getApplicationContext ());
                    startActivityForResult(new Intent(AnnotateEvents.this, Foul.class).putExtra("IdJ",idj),2);
                }
            }
        });

        agolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idj.equals("")) {
                    if (modo.equals("M D")) {
                        inserireventotecnico("35", cxt);
                        md();
                    }
                    else
                    {
                        inserireventotecnico("20", cxt);
                        md();
                    }
                }
            }
        });

        camarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (!idj.equals("")) {
                    inserireventotecnico("24", cxt);
                    //GR.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    if (idP.equals("0"))
                        GR.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("1"))
                        DD.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("2"))
                        DE.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("3"))
                        DC1.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("4"))
                        DC2.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("5"))
                        MDC.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("6"))
                        MC1.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("7"))
                        MC2.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("8"))
                        MOD.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("9"))
                        MOE.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else if (idP.equals("10"))
                        PL.setTextColor(getApplication().getResources().getColor(R.color.amarelo));
                    else
                        Toast.makeText(AnnotateEvents.this, "Jogador nao escolhido", Toast.LENGTH_SHORT);
                }*/
                escolherexecutante();
                inserireventotecnico("24", cxt);
                tmp = modo;
                modo = "amarelo";
            }
        });

        cvermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!idj.equals("")) {
                    inserireventotecnico("25", cxt);
                    //desativar o jogador expulso
                    if (idP.equals("0"))
                        GR.setEnabled(false);
                    else if (idP.equals("1"))
                        DD.setEnabled(false);
                    else if (idP.equals("2"))
                        DE.setEnabled(false);
                    else if (idP.equals("3"))
                        DC1.setEnabled(false);
                    else if (idP.equals("4"))
                        DC2.setEnabled(false);
                    else if (idP.equals("5"))
                        MDC.setEnabled(false);
                    else if (idP.equals("6"))
                        MC1.setEnabled(false);
                    else if (idP.equals("7"))
                        MC2.setEnabled(false);
                    else if (idP.equals("8"))
                        MOD.setEnabled(false);
                    else if (idP.equals("9"))
                        MOE.setEnabled(false);
                    else if (idP.equals("10"))
                        PL.setEnabled(false);
                    else
                        Toast.makeText(AnnotateEvents.this, "Jogador nao escolhido", Toast.LENGTH_SHORT);
                }*/
                inserireventotecnico("25", cxt);
                escolherexecutante();
                tmp = modo;
                modo = "vermelho";
            }
        });

        btmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se for possivel, mudar o texto do botao de mudanca de modo
                md();
            }
        });

        GR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = GRRemate;
                            tmpEventosP[1] = GRPasse;
                            tmpEventosP[2] = GRPerda;
                            tmpEventosP[3] = GRCorte;
                            tmpEventosP[4] = GRRecuperacao;
                            tmpEventosP[5] = GRCruzamento;
                            tmpEventosP[6] = GRLancamento;
                            tmpEventosP[7] = GRCanto;
                            tmpEventosP[8] = GRLivre;
                            tmpEventosP[9] = GRPenalty;
                            tmpEventosP[10] = GRGolo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, GR.getText().toString(), "GRCcorte", "GRCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            GRRemate = j1array[0];
                            GRPasse = j1array[1];
                            GRPerda = j1array[2];
                            GRCorte = j1array[3];
                            GRRecuperacao = j1array[4];
                            GRCruzamento =  j1array[5];
                            GRLancamento = j1array[6];
                            GRCanto = j1array[7];
                            GRLivre = j1array[8];
                            GRPenalty = j1array[9];
                            GRGolo = j1array[10];

                            GR.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                    //Ativar bottoes
                }
                else {
                    //Toast.makeText(AnnotateEvents.this, "Nao foi escolhido um evento.", Toast.LENGTH_SHORT).show();
                    idj = EquipaCasaIDJ[0];
                    idP = "0";
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }


                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + GRGolo + "\nLivre: " + GRLivre + "\nCanto: " + GRCanto + "\nPenalty: " + GRPenalty + "\nLancamento: " + GRLancamento + "\nCruzamento: " + GRCruzamento + "\nPasse: " + GRPasse + "\nPerda: " + GRPerda + "\nRemate: " + GRRemate, Toast.LENGTH_SHORT).show();
            }
        });

        DD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = DDRemate;
                            tmpEventosP[1] = DDPasse;
                            tmpEventosP[2] = DDPerda;
                            tmpEventosP[3] = DDCorte;
                            tmpEventosP[4] = DDRecuperacao;
                            tmpEventosP[5] = DDCruzamento;
                            tmpEventosP[6] = DDLancamento;
                            tmpEventosP[7] = DDCanto;
                            tmpEventosP[8] = DDLivre;
                            tmpEventosP[9] = DDPenalty;
                            tmpEventosP[10] = DDGolo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, DD.getText().toString(), "DDCcorte", "DDCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DDRemate = j1array[0];
                            DDPasse = j1array[1];
                            DDPerda = j1array[2];
                            DDCorte = j1array[3];
                            DDRecuperacao = j1array[4];
                            DDCruzamento =  j1array[5];
                            DDLancamento = j1array[6];
                            DDCanto = j1array[7];
                            DDLivre = j1array[8];
                            DDPenalty = j1array[9];
                            DDGolo = j1array[10];

                            DD.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[1];
                    idP = "1";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + DDGolo + "\nLivre: " + DDLivre + "\nCanto: " + DDCanto + "\nPenalty: " + DDPenalty + "\nLancamento: " + DDLancamento + "\nCruzamento: " + DDCruzamento + "\nPasse: " + DDPasse + "\nPerda: " + DDPerda + "\nRemate: " + DDRemate, Toast.LENGTH_SHORT).show();
            }
        });

        DE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = DERemate;
                            tmpEventosP[1] = DEPasse;
                            tmpEventosP[2] = DEPerda;
                            tmpEventosP[3] = DECorte;
                            tmpEventosP[4] = DERecuperacao;
                            tmpEventosP[5] = DECruzamento;
                            tmpEventosP[6] = DELancamento;
                            tmpEventosP[7] = DECanto;
                            tmpEventosP[8] = DELivre;
                            tmpEventosP[9] = DEPenalty;
                            tmpEventosP[10] = DEGolo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, DE.getText().toString(), "DECcorte", "DECRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DERemate = j1array[0];
                            DEPasse = j1array[1];
                            DEPerda = j1array[2];
                            DECorte = j1array[3];
                            DERecuperacao = j1array[4];
                            DECruzamento =  j1array[5];
                            DELancamento = j1array[6];
                            DECanto = j1array[7];
                            DELivre = j1array[8];
                            DEPenalty = j1array[9];
                            DEGolo = j1array[10];

                            DE.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[2];
                    idP = "2";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + DEGolo + "\nLivre: " + DELivre + "\nCanto: " + DECanto + "\nPenalty: " + DEPenalty + "\nLancamento: " + DELancamento + "\nCruzamento: " + DECruzamento + "\nPasse: " + DEPasse + "\nPerda: " + DEPerda + "\nRemate: " + DERemate, Toast.LENGTH_SHORT).show();
            }
        });

        DC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = DC1Remate;
                            tmpEventosP[1] = DC1Passe;
                            tmpEventosP[2] = DC1Perda;
                            tmpEventosP[3] = DC1Corte;
                            tmpEventosP[4] = DC1Recuperacao;
                            tmpEventosP[5] = DC1Cruzamento;
                            tmpEventosP[6] = DC1Lancamento;
                            tmpEventosP[7] = DC1Canto;
                            tmpEventosP[8] = DC1Livre;
                            tmpEventosP[9] = DC1Penalty;
                            tmpEventosP[10] = DC1Golo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, DC1.getText().toString(), "DC1Ccorte", "DC1CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DC1Remate = j1array[0];
                            DC1Passe = j1array[1];
                            DC1Perda = j1array[2];
                            DC1Corte = j1array[3];
                            DC1Recuperacao = j1array[4];
                            DC1Cruzamento =  j1array[5];
                            DC1Lancamento = j1array[6];
                            DC1Canto = j1array[7];
                            DC1Livre = j1array[8];
                            DC1Penalty = j1array[9];
                            DC1Golo = j1array[10];

                            DC1.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[3];
                    idP = "3";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + DC1Golo + "\nLivre: " + DC1Livre + "\nCanto: " + DC1Canto + "\nPenalty: " + DC1Penalty + "\nLancamento: " + DC1Lancamento + "\nCruzamento: " + DC1Cruzamento + "\nPasse: " + DC1Passe + "\nPerda: " + DC1Perda + "\nRemate: " + DC1Remate, Toast.LENGTH_SHORT).show();
            }
        });

        DC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = DC2Remate;
                            tmpEventosP[1] = DC2Passe;
                            tmpEventosP[2] = DC2Perda;
                            tmpEventosP[3] = DC2Corte;
                            tmpEventosP[4] = DC2Recuperacao;
                            tmpEventosP[5] = DC2Cruzamento;
                            tmpEventosP[6] = DC2Lancamento;
                            tmpEventosP[7] = DC2Canto;
                            tmpEventosP[8] = DC2Livre;
                            tmpEventosP[9] = DC2Penalty;
                            tmpEventosP[10] = DC2Golo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, DC2.getText().toString(), "DC2Ccorte", "DC2CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            DC2Remate = j1array[0];
                            DC2Passe = j1array[1];
                            DC2Perda = j1array[2];
                            DC2Corte = j1array[3];
                            DC2Recuperacao = j1array[4];
                            DC2Cruzamento =  j1array[5];
                            DC2Lancamento = j1array[6];
                            DC2Canto = j1array[7];
                            DC2Livre = j1array[8];
                            DC2Penalty = j1array[9];
                            DC2Golo = j1array[10];

                            DC2.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[4];
                    idP = "4";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + DC2Golo + "\nLivre: " + DC2Livre + "\nCanto: " + DC2Canto + "\nPenalty: " + DC2Penalty + "\nLancamento: " + DC2Lancamento + "\nCruzamento: " + DC2Cruzamento + "\nPasse: " + DC2Passe + "\nPerda: " + DC2Perda + "\nRemate: " + DC2Remate, Toast.LENGTH_SHORT).show();
            }
        });

        MDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = MDCRemate;
                            tmpEventosP[1] = MDCPasse;
                            tmpEventosP[2] = MDCPerda;
                            tmpEventosP[3] = MDCCorte;
                            tmpEventosP[4] = MDCRecuperacao;
                            tmpEventosP[5] = MDCCruzamento;
                            tmpEventosP[6] = MDCLancamento;
                            tmpEventosP[7] = MDCCanto;
                            tmpEventosP[8] = MDCLivre;
                            tmpEventosP[9] = MDCPenalty;
                            tmpEventosP[10] = MDCGolo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, MDC.getText().toString(), "MDCCcorte", "MDCCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MDCRemate = j1array[0];
                            MDCPasse = j1array[1];
                            MDCPerda = j1array[2];
                            MDCCorte = j1array[3];
                            MDCRecuperacao = j1array[4];
                            MDCCruzamento =  j1array[5];
                            MDCLancamento = j1array[6];
                            MDCCanto = j1array[7];
                            MDCLivre = j1array[8];
                            MDCPenalty = j1array[9];
                            MDCGolo = j1array[10];

                            MDC.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[5];
                    idP = "5";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + MDCGolo + "\nLivre: " + MDCLivre + "\nCanto: " + MDCCanto + "\nPenalty: " + MDCPenalty + "\nLancamento: " + MDCLancamento + "\nCruzamento: " + MDCCruzamento + "\nPasse: " + MDCPasse + "\nPerda: " + MDCPerda + "\nRemate: " + MDCRemate, Toast.LENGTH_SHORT).show();
            }
        });

        MC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = MC1Remate;
                            tmpEventosP[1] = MC1Passe;
                            tmpEventosP[2] = MC1Perda;
                            tmpEventosP[3] = MC1Corte;
                            tmpEventosP[4] = MC1Recuperacao;
                            tmpEventosP[5] = MC1Cruzamento;
                            tmpEventosP[6] = MC1Lancamento;
                            tmpEventosP[7] = MC1Canto;
                            tmpEventosP[8] = MC1Livre;
                            tmpEventosP[9] = MC1Penalty;
                            tmpEventosP[10] = MC1Golo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, MC1.getText().toString(), "MC1Ccorte", "MC1CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MC1Remate = j1array[0];
                            MC1Passe = j1array[1];
                            MC1Perda = j1array[2];
                            MC1Corte = j1array[3];
                            MC1Recuperacao = j1array[4];
                            MC1Cruzamento =  j1array[5];
                            MC1Lancamento = j1array[6];
                            MC1Canto = j1array[7];
                            MC1Livre = j1array[8];
                            MC1Penalty = j1array[9];
                            MC1Golo = j1array[10];

                            MC1.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[6];
                    idP = "6";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + MC1Golo + "\nLivre: " + MC1Livre + "\nCanto: " + MC1Canto + "\nPenalty: " + MC1Penalty + "\nLancamento: " + MC1Lancamento + "\nCruzamento: " + MC1Cruzamento + "\nPasse: " + MC1Passe + "\nPerda: " + MC1Perda + "\nRemate: " + MC1Remate, Toast.LENGTH_SHORT).show();
            }
        });

        MC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (evento.equals("troca"))
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
                            tmpEventosP[0] = MC2Remate;
                            tmpEventosP[1] = MC2Passe;
                            tmpEventosP[2] = MC2Perda;
                            tmpEventosP[3] = MC2Corte;
                            tmpEventosP[4] = MC2Recuperacao;
                            tmpEventosP[5] = MC2Cruzamento;
                            tmpEventosP[6] = MC2Lancamento;
                            tmpEventosP[7] = MC2Canto;
                            tmpEventosP[8] = MC2Livre;
                            tmpEventosP[9] = MC2Penalty;
                            tmpEventosP[10] = MC2Golo;

                            int[] j1array = trocacarjogador(jogador1, jogador2, tmpEventosP, MC2.getText().toString(), "MC2Ccorte", "MC2CRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MC2Remate = j1array[0];
                            MC2Passe = j1array[1];
                            MC2Perda = j1array[2];
                            MC2Corte = j1array[3];
                            MC2Recuperacao = j1array[4];
                            MC2Cruzamento =  j1array[5];
                            MC2Lancamento = j1array[6];
                            MC2Canto = j1array[7];
                            MC2Livre = j1array[8];
                            MC2Penalty = j1array[9];
                            MC2Golo = j1array[10];

                            MC2.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[7];
                    idP = "7";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + MC2Golo + "\nLivre: " + MC2Livre + "\nCanto: " + MC2Canto + "\nPenalty: " + MC2Penalty + "\nLancamento: " + MC2Lancamento + "\nCruzamento: " + MC2Cruzamento + "\nPasse: " + MC2Passe + "\nPerda: " + MC2Perda + "\nRemate: " + MC2Remate, Toast.LENGTH_SHORT).show();
            }
        });

        MOE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = MOERemate;
                            tmpEventosP[1] = MOEPasse;
                            tmpEventosP[2] = MOEPerda;
                            tmpEventosP[3] = MOECorte;
                            tmpEventosP[4] = MOERecuperacao;
                            tmpEventosP[5] = MOECruzamento;
                            tmpEventosP[6] = MOELancamento;
                            tmpEventosP[7] = MOECanto;
                            tmpEventosP[8] = MOELivre;
                            tmpEventosP[9] = MOEPenalty;
                            tmpEventosP[10] = MOEGolo;

                            int[] j1array = trocacarjogador(jogador1, jogador2, tmpEventosP, MOE.getText().toString(), "MOECcorte", "MOECRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MOERemate = j1array[0];
                            MOEPasse = j1array[1];
                            MOEPerda = j1array[2];
                            MOECorte = j1array[3];
                            MOERecuperacao = j1array[4];
                            MOECruzamento =  j1array[5];
                            MOELancamento = j1array[6];
                            MOECanto = j1array[7];
                            MOELivre = j1array[8];
                            MOEPenalty = j1array[9];
                            MOEGolo = j1array[10];

                            MOE.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[9];
                    idP = "9";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + MOEGolo + "\nLivre: " + MOELivre + "\nCanto: " + MOECanto + "\nPenalty: " + MOEPenalty + "\nLancamento: " + MOELancamento + "\nCruzamento: " + MOECruzamento + "\nPasse: " + MOEPasse + "\nPerda: " + MOEPerda + "\nRemate: " + MOERemate, Toast.LENGTH_SHORT).show();
            }
        });

        MOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (evento.equals("troca"))
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
                            tmpEventosP[0] = MODRemate;
                            tmpEventosP[1] = MODPasse;
                            tmpEventosP[2] = MODPerda;
                            tmpEventosP[3] = MODCorte;
                            tmpEventosP[4] = MODRecuperacao;
                            tmpEventosP[5] = MODCruzamento;
                            tmpEventosP[6] = MODLancamento;
                            tmpEventosP[7] = MODCanto;
                            tmpEventosP[8] = MODLivre;
                            tmpEventosP[9] = MODPenalty;
                            tmpEventosP[10] = MODGolo;

                            int[] j1array = trocacarjogador(jogador1, jogador2, tmpEventosP, MOD.getText().toString(), "MODCcorte", "MODCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            MODRemate = j1array[0];
                            MODPasse = j1array[1];
                            MODPerda = j1array[2];
                            MODCorte = j1array[3];
                            MODRecuperacao = j1array[4];
                            MODCruzamento =  j1array[5];
                            MODLancamento = j1array[6];
                            MODCanto = j1array[7];
                            MODLivre = j1array[8];
                            MODPenalty = j1array[9];
                            MODGolo = j1array[10];

                            MOD.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[8];
                    idP = "8";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo")
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + MODGolo + "\nLivre: " + MODLivre + "\nCanto: " + MODCanto + "\nPenalty: " + MODPenalty + "\nLancamento: " + MODLancamento + "\nCruzamento: " + MODCruzamento + "\nPasse: " + MODPasse + "\nPerda: " + MODPerda + "\nRemate: " + MODRemate, Toast.LENGTH_SHORT).show();
            }
        });

        PL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evento.equals("troca"))
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
                            tmpEventosP[0] = PLRemate;
                            tmpEventosP[1] = PLPasse;
                            tmpEventosP[2] = PLPerda;
                            tmpEventosP[3] = PLCorte;
                            tmpEventosP[4] = PLRecuperacao;
                            tmpEventosP[5] = PLCruzamento;
                            tmpEventosP[6] = PLLancamento;
                            tmpEventosP[7] = PLCanto;
                            tmpEventosP[8] = PLLivre;
                            tmpEventosP[9] = PLPenalty;
                            tmpEventosP[10] = PLGolo;

                            int[] j1array = trocacarjogador(jogador1,jogador2, tmpEventosP, PL.getText().toString(), "PLCcorte", "PLCRecuperacao");

                            //trcocar os dados dos eventos de cada jogador
                            PLRemate = j1array[0];
                            PLPasse = j1array[1];
                            PLPerda = j1array[2];
                            PLCorte = j1array[3];
                            PLRecuperacao = j1array[4];
                            PLCruzamento =  j1array[5];
                            PLLancamento = j1array[6];
                            PLCanto = j1array[7];
                            PLLivre = j1array[8];
                            PLPenalty = j1array[9];
                            PLGolo = j1array[10];

                            PL.setText(tmp);
                        }
                        else
                            Toast.makeText(AnnotateEvents.this, "Nao se pode trocar os mesmo jogador", Toast.LENGTH_SHORT).show();
                    }
                    jogador1 = -1;
                }
                else {
                    idj = EquipaCasaIDJ[10];
                    idP = "10";
                    //insertEvent(idj);
                    if (iniciar.getText().toString().equals("Ini. Parte"))
                    {
                        iniciar.setText("Fim Parte");
                        showButtons(true);
                        inserireventotecnico("29", cxt);
                    }
                    /*else if (iniciar.getText().toString().equals("Ini Parte"))
                    {
                        iniciar.setText("Fim 2ª Parte");
                        showButtons(1);
                        inserireventotecnico("29", cxt);
                    }*/
                    else if (modo == "M D")
                    {
                        inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S I")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "E S E")
                    {
                        insertEvent(idj);
                        //inserireventotecnico("5", cxt);
                        md();
                    }
                    else if (modo == "vermelho") // red
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else if (modo == "amarelo") // yellow
                    {
                        insertEvent(idj);
                        escolherexecutante();
                        modo = tmp;
                        cards(true);
                    }
                    else
                    {
                        insertEvent(idj);
                        if (iniciar.getVisibility() == View.INVISIBLE) {
                            btmd.setText("M O");
                            md();
                        }
                    }
                }
                evento = "";
                //Toast.makeText(AnnotateEvents.this, "Golo: " + PLGolo + "\nLivre: " + PLLivre + "\nCanto: " + PLCanto + "\nPenalty: " + PLPenalty + "\nLancamento: " + PLLancamento + "\nCruzamento: " + PLCruzamento + "\nPasse: " + PLPasse + "\nPerda: " + PLPerda + "\nRemate: " + PLRemate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            if (data != null){
                //data.getStringExtra("msg");
                //Toast.makeText(this, data.getStringExtra("msg") + " - IdJogador: " + idj, Toast.LENGTH_LONG).show();
                inserireventotecnico(data.getStringExtra("msg"), cxt);
                md();
            }
        }
        if (resultCode == 1){
            if (data != null){
                //data.getStringExtra("msg");
                //Toast.makeText(this, data.getStringExtra("msg") + " - IdJogador: " + idj, Toast.LENGTH_LONG).show();
                inserireventotecnico(data.getStringExtra("msg"), cxt);
                escolherexecutante();
                //cometemos falta
                modo = "E S I";
                btmd.setText("M D");
                cards(true);
            }
        }
        if (resultCode == 3){
            if (data != null){
                //data.getStringExtra("msg");
                //Toast.makeText(this, data.getStringExtra("msg") + " - IdJogador: " + idj, Toast.LENGTH_LONG).show();
                inserireventotecnico(data.getStringExtra("msg"), cxt);
                escolherexecutante();
                //Sefremos falta
                modo = "E S E";
                btmd.setText("M O");
                cards(true);
            }
        }
    }

    public void cards(boolean visible)
    {
        if (visible)
        {
            camarelo.setVisibility(View.VISIBLE);
            cvermelho.setVisibility(View.VISIBLE);
        }
        else
        {
            camarelo.setVisibility(View.INVISIBLE);
            cvermelho.setVisibility(View.INVISIBLE);
        }
    }

    public void escolherexecutante ()
    {
        btlg.setVisibility(View.INVISIBLE);
        //btpg.setVisibility(View.INVISIBLE);
        //subtituicao.setVisibility(View.INVISIBLE);
        troca.setVisibility(View.INVISIBLE);
        cantog.setVisibility(View.INVISIBLE);
        cantog2.setVisibility(View.INVISIBLE);
        lancamentg.setVisibility(View.INVISIBLE);
        lancamentg2.setVisibility(View.INVISIBLE);
        btpbaliza2.setVisibility(View.INVISIBLE);
        btpbaliza.setVisibility(View.INVISIBLE);
        agolo.setVisibility(View.INVISIBLE);
        btfalta.setVisibility(View.INVISIBLE);
        btmd.setVisibility(View.INVISIBLE);
        btbf.setVisibility(View.INVISIBLE);
        btbf2.setVisibility(View.INVISIBLE);
        perda.setVisibility(View.INVISIBLE);
        perda2.setVisibility(View.INVISIBLE);
        perda3.setVisibility(View.INVISIBLE);
        perda4.setVisibility(View.INVISIBLE);
        perda5.setVisibility(View.INVISIBLE);
        passe.setVisibility(View.INVISIBLE);
        passe2.setVisibility(View.INVISIBLE);
        passe3.setVisibility(View.INVISIBLE);
        passe4.setVisibility(View.INVISIBLE);
        passe5.setVisibility(View.INVISIBLE);
        golo.setVisibility(View.INVISIBLE);
        remate.setVisibility(View.INVISIBLE);
        cruzamento.setVisibility(View.INVISIBLE);
        penalty.setVisibility(View.INVISIBLE);
        livre.setVisibility(View.INVISIBLE);
        btrb.setVisibility(View.INVISIBLE);
        btrb2.setVisibility(View.INVISIBLE);
        btrf.setVisibility(View.INVISIBLE);
        btrf2.setVisibility(View.INVISIBLE);
        canto.setVisibility(View.INVISIBLE);
        canto2.setVisibility(View.INVISIBLE);
        lancamento.setVisibility(View.INVISIBLE);
        lancamento2.setVisibility(View.INVISIBLE);
        camarelo.setVisibility(View.INVISIBLE);
        cvermelho.setVisibility(View.INVISIBLE);
        iniciar.setVisibility(View.INVISIBLE);
    }

    public void md ()
    {
        if (btmd.getText().equals("M D")) {
            // Modo defencivo
            // Mudar o texto do botao falta
            //btfalta.setText("Foul");
            btmd.setText("M O");
            btbf.setText("Po\nnta\npé");
            btbf2.setText("Ba\nli\nza");
            perda.setText("Lançamento");
            perda2.setText("Lançamento");
            perda3.setText("Lançamento");
            perda4.setText("Lançamento");
            perda5.setText("Lançamento");
            passe.setText("Lançamento");
            passe2.setText("Lançamento");
            passe3.setText("Lançamento");
            passe4.setText("Lançamento");
            passe5.setText("Lançamento");
            agolo.setText("Go\nlo\n\nSo\nfri\ndo");
            perda.setVisibility(View.VISIBLE);
            perda2.setVisibility(View.VISIBLE);
            perda3.setVisibility(View.VISIBLE);
            perda4.setVisibility(View.VISIBLE);
            perda5.setVisibility(View.VISIBLE);
            agolo.setVisibility(View.VISIBLE);
            passe.setVisibility(View.VISIBLE);
            passe2.setVisibility(View.VISIBLE);
            passe3.setVisibility(View.VISIBLE);
            passe4.setVisibility(View.VISIBLE);
            passe5.setVisibility(View.VISIBLE);
            golo.setVisibility(View.INVISIBLE);
            remate.setVisibility(View.INVISIBLE);
            cruzamento.setVisibility(View.INVISIBLE);
            penalty.setVisibility(View.INVISIBLE);
            //livre.setVisibility(View.VISIBLE);
            btrb.setVisibility(View.INVISIBLE);
            btrb2.setVisibility(View.INVISIBLE);
            btrf.setVisibility(View.INVISIBLE);
            btrf2.setVisibility(View.INVISIBLE);
            canto.setVisibility(View.VISIBLE);
            canto2.setVisibility(View.VISIBLE);
            cantog.setVisibility(View.VISIBLE);
            cantog2.setVisibility(View.VISIBLE);
            lancamento.setVisibility(View.VISIBLE);
            lancamento2.setVisibility(View.VISIBLE);
            lancamentg.setVisibility(View.VISIBLE);
            lancamentg2.setVisibility(View.VISIBLE);
            camarelo.setVisibility(View.INVISIBLE);
            cvermelho.setVisibility(View.INVISIBLE);
            btpbaliza.setVisibility(View.VISIBLE);
            btpbaliza2.setVisibility(View.VISIBLE);
            btfalta.setVisibility(View.VISIBLE);
            iniciar.setVisibility(View.VISIBLE);
            btlg.setVisibility(View.VISIBLE);
            btbf.setVisibility(View.VISIBLE);
            btbf2.setVisibility(View.VISIBLE);
            modo = "M D";
        }
        else {
            //btfalta.setText("Foul O");
            //modo ofencivo
            btmd.setText("M D");
            //btbf.setText("B");
            //btbf2.setText("F");
            perda.setText("Perda de bola");
            perda2.setText("Perda de bola");
            perda3.setText("Perda de bola");
            perda4.setText("Perda de bola");
            perda5.setText("Perda de bola");
            passe.setText("Passe Falhado");
            passe2.setText("Passe Falhado");
            passe3.setText("Passe Falhado");
            passe4.setText("Passe Falhado");
            passe5.setText("Passe Falhado");
            agolo.setText("Au\nto\n\nGo\nlo");
            btlg.setVisibility(View.VISIBLE);
            iniciar.setVisibility(View.VISIBLE);
            btfalta.setVisibility(View.VISIBLE);
            agolo.setVisibility(View.VISIBLE);
            golo.setVisibility(View.VISIBLE);
            //remate.setVisibility(View.VISIBLE);
            //cruzamento.setVisibility(View.VISIBLE);
            //penalty.setVisibility(View.VISIBLE);
            //livre.setVisibility(View.VISIBLE);
            perda.setVisibility(View.VISIBLE);
            perda2.setVisibility(View.VISIBLE);
            perda3.setVisibility(View.VISIBLE);
            perda4.setVisibility(View.VISIBLE);
            perda5.setVisibility(View.VISIBLE);
            agolo.setVisibility(View.VISIBLE);
            passe.setVisibility(View.VISIBLE);
            passe2.setVisibility(View.VISIBLE);
            passe3.setVisibility(View.VISIBLE);
            passe4.setVisibility(View.VISIBLE);
            passe5.setVisibility(View.VISIBLE);
            btrb.setVisibility(View.VISIBLE);
            btrb2.setVisibility(View.VISIBLE);
            btrf.setVisibility(View.VISIBLE);
            btrf2.setVisibility(View.VISIBLE);
            canto.setVisibility(View.INVISIBLE);
            canto2.setVisibility(View.INVISIBLE);
            lancamento.setVisibility(View.INVISIBLE);
            lancamento2.setVisibility(View.INVISIBLE);
            lancamentg.setVisibility(View.INVISIBLE);
            lancamentg2.setVisibility(View.INVISIBLE);
            camarelo.setVisibility(View.INVISIBLE);
            cvermelho.setVisibility(View.INVISIBLE);
            cantog.setVisibility(View.INVISIBLE);
            cantog2.setVisibility(View.INVISIBLE);
            btpbaliza.setVisibility(View.INVISIBLE);
            btpbaliza2.setVisibility(View.INVISIBLE);
            btbf.setVisibility(View.INVISIBLE);
            btbf2.setVisibility(View.INVISIBLE);
            modo = "M O";
        }
        /*
        if ( iniciar.getVisibility() == View.INVISIBLE)
        {
            btlg.setVisibility(View.VISIBLE);
            //btpg.setVisibility(View.VISIBLE);
            //subtituicao.setVisibility(View.VISIBLE);
            troca.setVisibility(View.VISIBLE);
            //cantog.setVisibility(View.VISIBLE);
            //cantog2.setVisibility(View.VISIBLE);
            //lancamentg.setVisibility(View.VISIBLE);
            //lancamentg2.setVisibility(View.VISIBLE);
            //btpbaliza2.setVisibility(View.VISIBLE);
            //btpbaliza.setVisibility(View.VISIBLE);
            agolo.setVisibility(View.VISIBLE);
            iniciar.setVisibility(View.VISIBLE);
            btfalta.setVisibility(View.VISIBLE);
            btmd.setVisibility(View.VISIBLE);
            //btbf.setVisibility(View.VISIBLE);
            //btbf2.setVisibility(View.VISIBLE);
            perda.setVisibility(View.VISIBLE);
            perda2.setVisibility(View.VISIBLE);
            perda3.setVisibility(View.VISIBLE);
            perda4.setVisibility(View.VISIBLE);
            perda5.setVisibility(View.VISIBLE);
            passe.setVisibility(View.VISIBLE);
            passe2.setVisibility(View.VISIBLE);
            passe3.setVisibility(View.VISIBLE);
            passe4.setVisibility(View.VISIBLE);
            passe5.setVisibility(View.VISIBLE);
        }*/
    }

    public void perdadebola(View view)
    {
        if (!idj.equals("")) {
            if (modo.equals("M O")) {
                inserireventotecnico("3", cxt);
                md();
            }else {
                lancamentog(view);
            }
        }
    }

    public void lancamento(View view)
    {
        if (!idj.equals("")) {
            inserireventotecnico("14", cxt);
            escolherexecutante();
            modo = "E S E";
            btmd.setText("M O");
            cards(true);
        }
    }
    public void canto(View view)
    {
        if (!idj.equals("")) {
            inserireventotecnico("15", cxt);
            escolherexecutante();
            modo = "E S E";
            btmd.setText("M O");
            cards(true);
        }
    }
    public void passefalhado(View view)
    {
        if (!idj.equals("")) {
            if (modo.equals("M O")) {
                inserireventotecnico("31", cxt);
                md();
            }else {
                lancamentog(view);
            }
        }
    }

    public void lancamentog(View view)
    {
        if (!idj.equals("")) {
            inserireventotecnico("14", cxt);
            escolherexecutante();
            modo = "E S E";
            btmd.setText("M O");
            cards(true);
            //Se estiver no modo defensivo, passar para o modo ofenciso
            /*
            if (modo.equals("M D")) {
                md();
            }*/
        }
    }

    public void cantog(View view)
    {
        if (!idj.equals("")) {
            inserireventotecnico("15", cxt);
            escolherexecutante();
            modo = "E S E";
            btmd.setText("M O");
            cards(true);
            //Se estiver no modo defensivo, passar para o modo ofenciso
            /*
            if (modo.equals("M D")) {
                md();
            }*/
        }
    }

    public void rematef(View view)
    {
        if (!idj.equals("")) {
            state.setState(getApplicationContext ());
            startActivityForResult(new Intent(AnnotateEvents.this, Pop.class).putExtra("IdJ",idj),2);
        }
    }

    public void remateb(View view)
    {
        if (!idj.equals("")) {
            state.setState(getApplicationContext());
            startActivityForResult(new Intent(AnnotateEvents.this, Pop.class).putExtra("IdJ", idj), 2);
        }
    }

    public void bolafora(View view)
    {
        if (!idj.equals("")) {
            if (modo.equals("M O")) {
                inserireventotecnico("21", cxt);
                md();
            }else {
                // bola fora no modo defensivo e ponta pe de baliza
                ppbaliza(view);
            }
        }
    }

    public void ppbaliza(View view)
    {
        if (!idj.equals("")) {
            inserireventotecnico("22", cxt);
            escolherexecutante();
            modo = "E S E";
            btmd.setText("M O");
            cards(true);
            /*
            if (modo.equals("M D")) {
                md();
            }*/
        }
    }

    public String inserireventotecnico (String acao, Context cxt)
    {
        String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        //Toast.makeText(AnnotateEvents.this, "Data: " + currentDateTimeString, Toast.LENGTH_SHORT).show();

        //declarar uma nova connecao
        PHPConnection phpc = new PHPConnection(cxt, jsonclass);
        method = "inserir_enventos";
        phpc.execute(method, idev, currentDateTimeString, "41", acao, getApplicationContext().getSharedPreferences("login", 0).getString("login", null));
        //Toast.makeText(AnnotateEvents.this, resej, Toast.LENGTH_SHORT).show();
        return null;
    }

    public String insertEvent(String idj)
    {
        String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        //Toast.makeText(AnnotateEvents.this, "Data: " + currentDateTimeString, Toast.LENGTH_SHORT).show();

        //declarar uma nova connecao
        PHPConnection phpc = new PHPConnection(this, jsonclass);
        method = "Inserir_EventoJogador";
        phpc.execute(method, idj, idev, currentDateTimeString, getApplicationContext().getSharedPreferences("login", 0).getString("login", null));
        //Toast.makeText(AnnotateEvents.this, resej, Toast.LENGTH_SHORT).show();
        return null;
    }

    private void showButtons(boolean show) {

        if (show)
        {
            //troca.setVisibility(View.VISIBLE);
            agolo.setVisibility(View.VISIBLE);
            btfalta.setVisibility(View.VISIBLE);
            perda.setVisibility(View.VISIBLE);
            perda2.setVisibility(View.VISIBLE);
            perda3.setVisibility(View.VISIBLE);
            perda4.setVisibility(View.VISIBLE);
            perda5.setVisibility(View.VISIBLE);
            passe.setVisibility(View.VISIBLE);
            passe2.setVisibility(View.VISIBLE);
            passe3.setVisibility(View.VISIBLE);
            passe4.setVisibility(View.VISIBLE);
            passe5.setVisibility(View.VISIBLE);
            golo.setVisibility(View.VISIBLE);
            btrb.setVisibility(View.VISIBLE);
            btrb2.setVisibility(View.VISIBLE);
            btrf.setVisibility(View.VISIBLE);
            btrf2.setVisibility(View.VISIBLE);
        }
        else
        {
            passe.setVisibility(View.INVISIBLE);
            troca.setVisibility(View.INVISIBLE);
            cantog.setVisibility(View.INVISIBLE);
            cantog2.setVisibility(View.INVISIBLE);
            lancamentg.setVisibility(View.INVISIBLE);
            lancamentg2.setVisibility(View.INVISIBLE);
            btpbaliza2.setVisibility(View.INVISIBLE);
            btpbaliza.setVisibility(View.INVISIBLE);
            agolo.setVisibility(View.INVISIBLE);
            btfalta.setVisibility(View.INVISIBLE);
            btmd.setVisibility(View.INVISIBLE);
            btbf.setVisibility(View.INVISIBLE);
            btbf2.setVisibility(View.INVISIBLE);
            perda.setVisibility(View.INVISIBLE);
            perda2.setVisibility(View.INVISIBLE);
            perda3.setVisibility(View.INVISIBLE);
            perda4.setVisibility(View.INVISIBLE);
            perda5.setVisibility(View.INVISIBLE);
            passe.setVisibility(View.INVISIBLE);
            passe2.setVisibility(View.INVISIBLE);
            passe3.setVisibility(View.INVISIBLE);
            passe4.setVisibility(View.INVISIBLE);
            passe5.setVisibility(View.INVISIBLE);
            golo.setVisibility(View.INVISIBLE);
            remate.setVisibility(View.INVISIBLE);
            cruzamento.setVisibility(View.INVISIBLE);
            penalty.setVisibility(View.INVISIBLE);
            livre.setVisibility(View.INVISIBLE);
            btrb.setVisibility(View.INVISIBLE);
            btrb2.setVisibility(View.INVISIBLE);
            btrf.setVisibility(View.INVISIBLE);
            btrf2.setVisibility(View.INVISIBLE);
            canto.setVisibility(View.INVISIBLE);
            canto2.setVisibility(View.INVISIBLE);
            lancamento.setVisibility(View.INVISIBLE);
            lancamento2.setVisibility(View.INVISIBLE);
            camarelo.setVisibility(View.INVISIBLE);
            cvermelho.setVisibility(View.INVISIBLE);
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
            showButtons(true);

        //agora trocar os dados das suas repectivas posicoes
        if (j1 == 0)
        {
            tmpEventos[0] = GRRemate;
            tmpEventos[1] = GRPasse;
            tmpEventos[2] = GRPerda;
            tmpEventos[3] = GRCorte;
            tmpEventos[4] = GRRecuperacao;
            tmpEventos[5] = GRCruzamento;
            tmpEventos[6] = GRLancamento;
            tmpEventos[7] = GRCanto;
            tmpEventos[8] = GRLivre;
            tmpEventos[9] = GRPenalty;
            tmpEventos[10] = GRGolo;

            GRRemate = Jarray[0];
            GRPasse = Jarray[1];
            GRPerda = Jarray[2];
            GRCorte = Jarray[3];
            GRRecuperacao = Jarray[4];
            GRCruzamento =  Jarray[5];
            GRLancamento = Jarray[6];
            GRCanto = Jarray[7];
            GRLivre = Jarray[8];
            GRPenalty = Jarray[9];
            GRGolo = Jarray[10];
            //trocar nomes no botao
            tmp = GR.getText().toString();
            GR.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 1)
        {
            tmpEventos[0] = DDRemate;
            tmpEventos[1] = DDPasse;
            tmpEventos[2] = DDPerda;
            tmpEventos[3] = DDCorte;
            tmpEventos[4] = DDRecuperacao;
            tmpEventos[5] = DDCruzamento;
            tmpEventos[6] = DDLancamento;
            tmpEventos[7] = DDCanto;
            tmpEventos[8] = DDLivre;
            tmpEventos[9] = DDPenalty;
            tmpEventos[10] = DDGolo;

            DDRemate = Jarray[0];
            DDPasse = Jarray[1];
            DDPerda = Jarray[2];
            DDCorte = Jarray[3];
            DDRecuperacao = Jarray[4];
            DDCruzamento =  Jarray[5];
            DDLancamento = Jarray[6];
            DDCanto = Jarray[7];
            DDLivre = Jarray[8];
            DDPenalty = Jarray[9];
            DDGolo = Jarray[10];
            //trocar nomes no botao
            tmp = DD.getText().toString();
            DD.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 2)
        {
            tmpEventos[0] = DERemate;
            tmpEventos[1] = DEPasse;
            tmpEventos[2] = DEPerda;
            tmpEventos[3] = DECorte;
            tmpEventos[4] = DERecuperacao;
            tmpEventos[5] = DECruzamento;
            tmpEventos[6] = DELancamento;
            tmpEventos[7] = DECanto;
            tmpEventos[8] = DELivre;
            tmpEventos[9] = DEPenalty;
            tmpEventos[10] = DEGolo;

            DERemate = Jarray[0];
            DEPasse = Jarray[1];
            DEPerda = Jarray[2];
            DECorte = Jarray[3];
            DERecuperacao = Jarray[4];
            DECruzamento =  Jarray[5];
            DELancamento = Jarray[6];
            DECanto = Jarray[7];
            DELivre = Jarray[8];
            DEPenalty = Jarray[9];
            DEGolo = Jarray[10];
            //trocar nomes no botao
            tmp = DE.getText().toString();
            DE.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 3)
        {
            tmpEventos[0] = DC1Remate;
            tmpEventos[1] = DC1Passe;
            tmpEventos[2] = DC1Perda;
            tmpEventos[3] = DC1Corte;
            tmpEventos[4] = DC1Recuperacao;
            tmpEventos[5] = DC1Cruzamento;
            tmpEventos[6] = DC1Lancamento;
            tmpEventos[7] = DC1Canto;
            tmpEventos[8] = DC1Livre;
            tmpEventos[9] = DC1Penalty;
            tmpEventos[10] = DC1Golo;

            DC1Remate = Jarray[0];
            DC1Passe = Jarray[1];
            DC1Perda = Jarray[2];
            DC1Corte = Jarray[3];
            DC1Recuperacao = Jarray[4];
            DC1Cruzamento =  Jarray[5];
            DC1Lancamento = Jarray[6];
            DC1Canto = Jarray[7];
            DC1Livre = Jarray[8];
            DC1Penalty = Jarray[9];
            DC1Golo = Jarray[10];
            //trocar nomes no botao
            tmp = DC1.getText().toString();
            DC1.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 4)
        {
            tmpEventos[0] = DC2Remate;
            tmpEventos[1] = DC2Passe;
            tmpEventos[2] = DC2Perda;
            tmpEventos[3] = DC2Corte;
            tmpEventos[4] = DC2Recuperacao;
            tmpEventos[5] = DC2Cruzamento;
            tmpEventos[6] = DC2Lancamento;
            tmpEventos[7] = DC2Canto;
            tmpEventos[8] = DC2Livre;
            tmpEventos[9] = DC2Penalty;
            tmpEventos[10] = DC2Golo;

            DC2Remate = Jarray[0];
            DC2Passe = Jarray[1];
            DC2Perda = Jarray[2];
            DC2Corte = Jarray[3];
            DC2Recuperacao = Jarray[4];
            DC2Cruzamento =  Jarray[5];
            DC2Lancamento = Jarray[6];
            DC2Canto = Jarray[7];
            DC2Livre = Jarray[8];
            DC2Penalty = Jarray[9];
            DC2Golo = Jarray[10];
            //trocar nomes no botao
            tmp = DC2.getText().toString();
            DC2.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 5)
        {
            tmpEventos[0] = MDCRemate;
            tmpEventos[1] = MDCPasse;
            tmpEventos[2] = MDCPerda;
            tmpEventos[3] = MDCCorte;
            tmpEventos[4] = MDCRecuperacao;
            tmpEventos[5] = MDCCruzamento;
            tmpEventos[6] = MDCLancamento;
            tmpEventos[7] = MDCCanto;
            tmpEventos[8] = MDCLivre;
            tmpEventos[9] = MDCPenalty;
            tmpEventos[10] = MDCGolo;

            MDCRemate = Jarray[0];
            MDCPasse = Jarray[1];
            MDCPerda = Jarray[2];
            MDCCorte = Jarray[3];
            MDCRecuperacao = Jarray[4];
            MDCCruzamento =  Jarray[5];
            MDCLancamento = Jarray[6];
            MDCCanto = Jarray[7];
            MDCLivre = Jarray[8];
            MDCPenalty = Jarray[9];
            MDCGolo = Jarray[10];
            //trocar nomes no botao
            tmp = MDC.getText().toString();
            MDC.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 6)
        {
            tmpEventos[0] = MC1Remate;
            tmpEventos[1] = MC1Passe;
            tmpEventos[2] = MC1Perda;
            tmpEventos[3] = MC1Corte;
            tmpEventos[4] = MC1Recuperacao;
            tmpEventos[5] = MC1Cruzamento;
            tmpEventos[6] = MC1Lancamento;
            tmpEventos[7] = MC1Canto;
            tmpEventos[8] = MC1Livre;
            tmpEventos[9] = MC1Penalty;
            tmpEventos[10] = MC1Golo;

            MC1Remate = Jarray[0];
            MC1Passe = Jarray[1];
            MC1Perda = Jarray[2];
            MC1Corte = Jarray[3];
            MC1Recuperacao = Jarray[4];
            MC1Cruzamento =  Jarray[5];
            MC1Lancamento = Jarray[6];
            MC1Canto = Jarray[7];
            MC1Livre = Jarray[8];
            MC1Penalty = Jarray[9];
            MC1Golo = Jarray[10];
            //trocar nomes no botao
            tmp = MC1.getText().toString();
            MC1.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 7)
        {
            tmpEventos[0] = MC2Remate;
            tmpEventos[1] = MC2Passe;
            tmpEventos[2] = MC2Perda;
            tmpEventos[3] = MC2Corte;
            tmpEventos[4] = MC2Recuperacao;
            tmpEventos[5] = MC2Cruzamento;
            tmpEventos[6] = MC2Lancamento;
            tmpEventos[7] = MC2Canto;
            tmpEventos[8] = MC2Livre;
            tmpEventos[9] = MC2Penalty;
            tmpEventos[10] = MC2Golo;

            MC2Remate = Jarray[0];
            MC2Passe = Jarray[1];
            MC2Perda = Jarray[2];
            MC2Corte = Jarray[3];
            MC2Recuperacao = Jarray[4];
            MC2Cruzamento =  Jarray[5];
            MC2Lancamento = Jarray[6];
            MC2Canto = Jarray[7];
            MC2Livre = Jarray[8];
            MC2Penalty = Jarray[9];
            MC2Golo = Jarray[10];
            //trocar nomes no botao
            tmp = MC2.getText().toString();
            MC2.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 8)
        {
            tmpEventos[0] = MODRemate;
            tmpEventos[1] = MODPasse;
            tmpEventos[2] = MODPerda;
            tmpEventos[3] = MODCorte;
            tmpEventos[4] = MODRecuperacao;
            tmpEventos[5] = MODCruzamento;
            tmpEventos[6] = MODLancamento;
            tmpEventos[7] = MODCanto;
            tmpEventos[8] = MODLivre;
            tmpEventos[9] = MODPenalty;
            tmpEventos[10] = MODGolo;

            MODRemate = Jarray[0];
            MODPasse = Jarray[1];
            MODPerda = Jarray[2];
            MODCorte = Jarray[3];
            MODRecuperacao = Jarray[4];
            MODCruzamento =  Jarray[5];
            MODLancamento = Jarray[6];
            MODCanto = Jarray[7];
            MODLivre = Jarray[8];
            MODPenalty = Jarray[9];
            MODGolo = Jarray[10];
            //trocar nomes no botao
            tmp = MOD.getText().toString();
            MOD.setText(nometmp);

            return tmpEventos;
        }
        else if (j1 == 9)
        {
            tmpEventos[0] = MOERemate;
            tmpEventos[1] = MOEPasse;
            tmpEventos[2] = MOEPerda;
            tmpEventos[3] = MOECorte;
            tmpEventos[4] = MOERecuperacao;
            tmpEventos[5] = MOECruzamento;
            tmpEventos[6] = MOELancamento;
            tmpEventos[7] = MOECanto;
            tmpEventos[8] = MOELivre;
            tmpEventos[9] = MOEPenalty;
            tmpEventos[10] = MOEGolo;

            MOERemate = Jarray[0];
            MOEPasse = Jarray[1];
            MOEPerda = Jarray[2];
            MOECorte = Jarray[3];
            MOERecuperacao = Jarray[4];
            MOECruzamento =  Jarray[5];
            MOELancamento = Jarray[6];
            MOECanto = Jarray[7];
            MOELivre = Jarray[8];
            MOEPenalty = Jarray[9];
            MOEGolo = Jarray[10];
            //trocar nomes no botao
            tmp = MOE.getText().toString();
            MOE.setText(nometmp);

            return tmpEventos;
        }
        else
        {

            tmpEventos[0] = PLRemate;
            tmpEventos[1] = PLPasse;
            tmpEventos[2] = PLPerda;
            tmpEventos[3] = PLCorte;
            tmpEventos[4] = PLRecuperacao;
            tmpEventos[5] = PLCruzamento;
            tmpEventos[6] = PLLancamento;
            tmpEventos[7] = PLCanto;
            tmpEventos[8] = PLLivre;
            tmpEventos[9] = PLPenalty;
            tmpEventos[10] = PLGolo;

            PLRemate = Jarray[0];
            PLPasse = Jarray[1];
            PLPerda = Jarray[2];
            PLCorte = Jarray[3];
            PLRecuperacao = Jarray[4];
            PLCruzamento =  Jarray[5];
            PLLancamento = Jarray[6];
            PLCanto = Jarray[7];
            PLLivre = Jarray[8];
            PLPenalty = Jarray[9];
            PLGolo = Jarray[10];
            //trocar nomes no botao
            tmp = PL.getText().toString();
            PL.setText(nometmp);

            return tmpEventos;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().getSharedPreferences("login", 0).edit().clear().commit();
    }
}
