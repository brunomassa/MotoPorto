package com.example.tiago.aplicacao_pat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 14-05-2015.
 */
public class iniciar_sessao extends ActionBarActivity {
    Button entrar,registar;
    Intent login,registo;
    EditText nomeutilizador,passutilizador;

    HttpResponse response;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciarsessao_layout);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nomeutilizador=(EditText)findViewById(R.id.editText7);
        passutilizador=(EditText)findViewById(R.id.editText8);


        entrar=(Button)findViewById(R.id.button6);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n, p;
                InputStream is = null;

                n = nomeutilizador.getText().toString();
                p = passutilizador.getText().toString();


                //namevaluepairs

                List<NameValuePair> nomes = new ArrayList<NameValuePair>(1);

                //adicionar as strings a lista


                nomes.add(new BasicNameValuePair("nome", String.valueOf(n)));
                nomes.add(new BasicNameValuePair("pass", String.valueOf(p)));

                //verifica a existencia do utilizador

                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://motoporto.esy.es/login.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nomes));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    String result = "";
                    // convert response to string
                    new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    InputStreamReader r = new InputStreamReader(is, "UTF-8");
                    int intch;
                    while ((intch = r.read()) != -1) {
                        char ch = (char) intch;
                        String s = new String(Character.toString(ch).getBytes(), "UTF-8");
                        sb.append(s);
                    }
                    is.close();
                    result = sb.toString();

                    if(result.length()==8){
                        //se o login for verdadeiro
                        Toast.makeText(iniciar_sessao.this,"Sucesso",Toast.LENGTH_LONG).show();
                        login=new Intent(iniciar_sessao.this,alugarmoto.class).putExtra("strnome",String.valueOf(n));
                        startActivity(login);
                    }
                    else{
                        //se o login for falso
                        Toast.makeText(iniciar_sessao.this,"utilizador e palavra-passe incorretos",Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(iniciar_sessao.this,"Ocorreu um erro",Toast.LENGTH_LONG).show();

                }
            }
        });
        registar=(Button)findViewById(R.id.button7);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //associa actividade
                registo=new Intent(iniciar_sessao.this,Aluger.class);
                //inicia actividade
                startActivity(registo);
            }
        });
    }
}
