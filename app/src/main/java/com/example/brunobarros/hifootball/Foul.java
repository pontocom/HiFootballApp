package com.example.brunobarros.hifootball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by brunobarros on 19/09/17.
 */
public class Foul extends Activity{

    Context cxt, cxtAnotar;
    boolean longclik = false;
    String IdJ;
    AnnotateEvents ae;
    State state;
    Intent im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.falta);

        im = new Intent();

        cxt = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        Button falta = (Button) findViewById(R.id.falta);
        Button penalty = (Button) findViewById(R.id.penalty);
        Button forajogo = (Button) findViewById(R.id.forajogo); // impedimento = Offside?
        Button sfalta = (Button) findViewById(R.id.sfalta);
        Button spenalty = (Button) findViewById(R.id.spenalty);
        Button sforajogo = (Button) findViewById(R.id.sforajogo);

        falta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Cometeu Foul", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "17");
                setResult(1, im);
                finish();
            }
        });

        penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Cometeu Penalty", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "10");
                setResult(1, im);
                finish();
            }
        });

        forajogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Cometeu Fora Jogo", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "32");
                setResult(1, im);
                finish();
            }
        });

        sfalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Sofreu Foul", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "30");
                setResult(3, im);
                finish();
            }
        });

        spenalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Sofreu Penalty", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "12");
                setResult(3, im);
                finish();
            }
        });

        sforajogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cxt, "Sofreu Fora Jogo", Toast.LENGTH_SHORT).show();
                im.putExtra("msg", "33");
                setResult(3, im);
                finish();
            }
        });

    }
}
