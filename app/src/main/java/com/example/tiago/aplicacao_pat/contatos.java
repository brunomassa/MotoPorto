package com.example.tiago.aplicacao_pat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tiago on 10-05-2015.
 */
public class contatos extends ActionBarActivity {

    TextView contatos_txt,msg_txt,nome,mail,sugestao;

    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //associa o layout contatos a esta atividade
        setContentView(R.layout.contactos_layout);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        contatos_txt=(TextView)findViewById(R.id.textView2);
        msg_txt=(TextView)findViewById(R.id.textView4);
        msg_txt.setText("Deixe-nos aqui a sua opinião e dê sugestões…");
        contatos_txt.setText("Contacte-nos para tira as suas duvidas e para nos ajudar a melhorar o nosso serviço\n" +
                "E-mail: motoporto2015@gmail.com\n" +
                "\n" +
                "Morada: Rua Costa Cabral\n" +
                "\n" +
                "Contacto :222333456\n" +
                "\n");

        nome=(EditText)findViewById(R.id.editText);
        mail=(EditText)findViewById(R.id.editText16);
        sugestao=(EditText)findViewById(R.id.editText17);

        enviar=(Button)findViewById(R.id.button9);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envaimail();
                //apos enviar o email
                nome.setText("");
                mail.setText("");
                sugestao.setText("");
            }
        });
    }

    protected void envaimail(){
        //recetor do email
        String recetor[]={"motoporto2015@gmail.com"};
        //atividade que envia o mail
        Intent email=new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recetor);
        email.putExtra(Intent.EXTRA_SUBJECT, "Opiniao");
        email.putExtra(Intent.EXTRA_TEXT, "Nome: " + nome.getText().toString() + "\n" + "E-mail: " + mail.getText().toString() + "\n" + "Sugestão: " + sugestao.getText().toString());
        //verifica se existe algum cliente de email no dispositivo
        try {

            startActivity(Intent.createChooser(email, "Enviar email através de:"));

        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(contatos.this, "Nenhum cliente de email encontrado", Toast.LENGTH_LONG).show();
        }

    }


}
