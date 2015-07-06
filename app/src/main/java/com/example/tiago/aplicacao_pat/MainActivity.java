package com.example.tiago.aplicacao_pat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    //variaveis butoes
    Button btn1,btn2,btn3,btn4;
    //variaveis Atividades
    Intent qs,cont,aluger,localizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //inicia as variaveis e associa os butoes as variaveis
        btn1=(Button)findViewById(R.id.button3);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button);
        btn4=(Button)findViewById(R.id.button4);
        //Onclicklistener do primento butao
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //associa a Atividade a variavél
                qs=new Intent(MainActivity.this,quemsomos.class);
                //inicia a Actividade
                startActivity(qs);
            }
        });
          //Onclicklistener do terceiro butao
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //associa actividade
                cont=new Intent(MainActivity.this,contatos.class);
                //inicia actividade
                startActivity(cont);
            }
        });
        //Onclicklistener do quarto butao
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //associa actividade
                aluger=new Intent(MainActivity.this, iniciar_sessao.class);
                //inicia actividade
                startActivity(aluger);
            }
        });
        //Onclicklistener do segundo butao
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //associa actividade
                localizacao=new Intent(MainActivity.this,MapsActivity.class);
                //inicia actividade
                startActivity(localizacao);
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
}
