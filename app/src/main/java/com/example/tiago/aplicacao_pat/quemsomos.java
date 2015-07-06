package com.example.tiago.aplicacao_pat;

import android.app.Activity;
import android.app.Notification;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Tiago on 10-05-2015.
 */
public class quemsomos extends ActionBarActivity {

    TextView quemsomos_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //associa o layout quemsomos a esta actividade
        setContentView(R.layout.quemsomos_layout);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        quemsomos_txt=(TextView)findViewById(R.id.textView);
        quemsomos_txt.setText("A MotoPorto é uma empresa de aluguer de scooters, com base no Norte de Portugal.\n" +
                "\n" +
                "A sua actividade é dirigida a empresas, organizadores de eventos, agências de viagens, pequenos grupos e indivíduos.\n" +
                "\n" +
                "Alugar uma scooter é a forma mais fácil para visitar e desfrutar das partes mais escondidas do Porto, bem como conhecer a área metropolitana, sem ter problemas de estacionamento.\n" +
                "\n" +
                "Descubra como é interessante e divertido conhecer novos amigos, escapar ao trânsito e ver as vistas.\n" +
                "\n" +
                "A MotoPorto está devidamente certificada pelas entidades competentes.\n" +
                "\n" +
                "A MotoPorto pretende oferecer aos seus clientes serviços que se distingam pela sua exclusividade e distinção, nas diversas categorias de produtos disponibilizados.\n" +
                "\n" +
                "Garanta e escolha a sua scooter, efectuando reserva no nosso website ou na nossa aplicação em android\n" +
                "\n" +
                "A entrega e recolha das scooters são grátis na cidade do Porto para clientes habituais");
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
