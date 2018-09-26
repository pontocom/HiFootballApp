package com.example.brunobarros.hifootball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by brunobarros on 23/08/17.
 */
public class Pop extends Activity {

    //AnnotateEvents ae;
    Context cxt, cxtAnotar;
    boolean longclik = false;
    String IdJ;
    AnnotateEvents ae;
    State estado;
    Intent im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remate);

        /*
        IdJ = getIntent().getStringExtra("IdJ");

        cxt = this;

        state = ((State) getApplicationContext());
        ae = new AnnotateEvents();
        ae = (AnnotateEvents) state.getState();

        Toast.makeText(cxt, "OnCreate. IdJ: " + ae.idj, Toast.LENGTH_LONG).show();*/

        im = new Intent();

        cxt = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int largura = dm.widthPixels;
        int altura = dm.heightPixels;

        getWindow().setLayout((int)(largura*.9),(int)(altura*.9));

        //getWindow().

        /*Button btbf = (Button) findViewById(R.id.btbf);
        Button btbb = (Button) findViewById(R.id.btbb);
        Button btbb2 = (Button) findViewById(R.id.btbb2);
        Button btbp = (Button) findViewById(R.id.btbp);
        Button btbp2 = (Button) findViewById(R.id.btbp2);*/
        Button btgolo = (Button) findViewById(R.id.btgolo);
        Button btgolo2 = (Button) findViewById(R.id.btgolo2);
        Button btgolo3 = (Button) findViewById(R.id.btgolo3);
        Button btrb = (Button) findViewById(R.id.btrb);
        Button btrb2 = (Button) findViewById(R.id.btrb2);
        Button btrb3 = (Button) findViewById(R.id.btrb3);

        /*btbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cxt, "Remate para Fora", Toast.LENGTH_LONG).show();
                if (!ae.idj.equals("")) {
                    ae.inserireventotecnico("18", cxt);
                    ae.md();
                    finish();
                }
            }
        });*/

        /*
        btbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cxt, "Remate na Barra", Toast.LENGTH_LONG).show();
                if (!ae.idj.equals("")) {
                    // Criar o evento bola na barra e alterar o id
                    ae.inserireventotecnico("27", cxt);
                    ae.md();
                    finish();

                }
            }
        });*/

        /*btbb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ae.idj.equals("")) {
                    // Criar o evento bola na barra e alterar o id
                    ae.inserireventotecnico("1", cxt);
                    ae.md();
                }
            }
        });

        btrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cxt, "Remate Bloqueado", Toast.LENGTH_LONG).show();
                if (!ae.idj.equals("")) {
                    // Criar o evento remate bloqueado e alterar o id
                    ae.inserireventotecnico("1", cxt);
                    ae.md();
                    finish();
                }
            }
        });

        btbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cxt, "Remate ao Poste", Toast.LENGTH_LONG).show();
                if (!ae.idj.equals("")) {
                    // Criar o evento bola no poste e alterar o id
                    ae.inserireventotecnico("1", cxt);
                    ae.md();
                    finish();
                }
            }
        });*/

        /*btbp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ae.idj.equals("")) {
                    // Criar o evento bola no poste e alterar o id
                    ae.inserireventotecnico("1", cxt);
                    ae.md();
                }
            }
        });

        btgolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cxt, "Golo", Toast.LENGTH_LONG).show();
                if (!ae.idj.equals("")) {
                    ae.inserireventotecnico("11", cxt);
                    ae.md();
                    finish();
                }
            }
        });*/
    /*
        btgolo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longclik = true;
                //Toast.makeText(cxt, "Golo", Toast.LENGTH_LONG).show();
                /*
                if (!IdJ.equals("")) {
                    ae.inserireventotecnico("11", cxt);
                    ae.md();
                    //finish();
                    return true;
                }
                im.putExtra("msg", "11");
                setResult(2, im);
                finish();
                return false;
            }
        });

        */
    }

    public void RemateFora(View view)
    {
        //view.getId() to get the id of the button was clicked
        //Toast.makeText(cxt, "Remate para Fora", Toast.LENGTH_SHORT).show();
        im.putExtra("msg", "18");
        setResult(2, im);
        finish();
        /*
        if (!IdJ.equals("")) {
            ae.inserireventotecnico("18", cxt);
            ae.md();
            //finish();
        }*/
    }

    public void RemateBarra(View view)
    {
        //Toast.makeText(cxt, "Remate na Barra", Toast.LENGTH_SHORT).show();
        im.putExtra("msg", "27");
        setResult(2, im);
        finish();
        /*
        if (!IdJ.equals("")) {
            // Criar o evento bola na barra e alterar o id
            ae.inserireventotecnico("27", cxt);
            ae.md();
            //finish();
        }*/
    }

    public void RemateBloqueado(View view)
    {
        im.putExtra("msg", "26");
        setResult(2, im);
        finish();
        /*
        if (longclik == false) {
            //Toast.makeText(cxt, "Remate Bloqueado", Toast.LENGTH_LONG).show();
            im.putExtra("msg", "26");
            setResult(2, im);
            finish();
            /*
            if (!IdJ.equals("")) {
                // Criar o evento remate bloqueado e alterar o id
                ae.inserireventotecnico("26", cxt);
                ae.md();
                //finish();
            }
        }
        else
        {
            //Toast.makeText(cxt, "Onclick On", Toast.LENGTH_LONG).show();
            longclik = false;
            //finish();
        }*/
    }

    public void RematePoste(View view)
    {
        //Toast.makeText(cxt, "Remate ao Poste", Toast.LENGTH_LONG).show();
        im.putExtra("msg", "28");
        setResult(2, im);
        finish();
        /*
        if (!IdJ.equals("")) {
            // Criar o evento bola no poste e alterar o id
            ae.inserireventotecnico("28", cxt);
            ae.md();
            //finish();
        }*/
    }

    public void Golo(View view)
    {
        //Toast.makeText(cxt, "Remate ao Poste", Toast.LENGTH_LONG).show();
        im.putExtra("msg", "11");
        setResult(2, im);
        finish();
    }
}
