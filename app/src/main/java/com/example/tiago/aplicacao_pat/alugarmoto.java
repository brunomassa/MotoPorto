package com.example.tiago.aplicacao_pat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class alugarmoto extends ActionBarActivity {
    Intent logout;

    Spinner motas;

    EditText nome,mail,morada,telemovel,contribuinte,dia,mes,ano,local,Ndias,preco;

    ImageView motos;

    Button alugar;

    String n;
    InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alugarmoto);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        motos=(ImageView)findViewById(R.id.imageView2);

        motas=(Spinner)findViewById(R.id.spinner);

        motas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        motos.setImageResource(R.drawable.m1);
                        break;
                    case 2:
                        motos.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        motos.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        motos.setImageResource(R.drawable.m5);
                        break;
                    case 5:
                        motos.setImageResource(R.drawable.m4);
                        break;
                    case 6:
                        motos.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        motos.setImageResource(R.drawable.m7);
                        break;
                    case 8:
                        motos.setImageResource(R.drawable.m8);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adptador = ArrayAdapter.createFromResource(alugarmoto.this, R.array.motas,android.R.layout.simple_spinner_item);
        //mete as opcoes no spinner
        adptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        motas.setAdapter(adptador);



        nome=(EditText)findViewById(R.id.editText11);
        nome.setText(getIntent().getStringExtra("strnome"));
        mail=(EditText)findViewById(R.id.editText12);
        morada=(EditText)findViewById(R.id.editText13);
        telemovel=(EditText)findViewById(R.id.editText14);
        contribuinte=(EditText)findViewById(R.id.editText15);
        dia=(EditText)findViewById(R.id.editText16);
        mes=(EditText)findViewById(R.id.editText17);
        ano=(EditText)findViewById(R.id.editText18);
        local=(EditText)findViewById(R.id.editText19);
        Ndias=(EditText)findViewById(R.id.editText20);
        preco=(EditText)findViewById(R.id.editText21);

        n=nome.getText().toString();

        List<NameValuePair> nomes = new ArrayList<NameValuePair>(1);

        //adicionar as strings a lista


        nomes.add(new BasicNameValuePair("nome", String.valueOf(n)));

        //recebe mail

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://motoporto.esy.es/recebemail.php");
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

            mail.setText(result);
        }
        catch (Exception ex){
            Toast.makeText(alugarmoto.this,"Ocorreu um erro",Toast.LENGTH_LONG).show();
        }



        //receber morada
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://motoporto.esy.es/recebemorada.php");
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

            morada.setText(result);
        }
        catch (Exception ex){
            Toast.makeText(alugarmoto.this,"Ocorreu um erro",Toast.LENGTH_LONG).show();
        }

        //recebe telemovel

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://motoporto.esy.es/recebetele.php");
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

            telemovel.setText(result);
        }
        catch (Exception ex){
            Toast.makeText(alugarmoto.this,"Ocorreu um erro",Toast.LENGTH_LONG).show();
        }

        //recebe contribuinte

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://motoporto.esy.es/recebecontri.php");
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

            contribuinte.setText(result);
        }
        catch (Exception ex){
            Toast.makeText(alugarmoto.this,"Ocorreu um erro",Toast.LENGTH_LONG).show();
        }

        alugar=(Button)findViewById(R.id.button8);
        alugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ms,ds;

                ms=Integer.parseInt(mes.getText().toString());
                ds=Integer.parseInt(Ndias.getText().toString());

                if(ms>=4 && ms<=10){
                    if(ds==1){
                        preco.setText("30.00€");

                    }
                    if(ds==2){
                        preco.setText("60.00€");

                    }
                    if(ds==3){
                        preco.setText("88.00€");

                    }
                    if(ds==4){
                        preco.setText("117.00€");

                    }
                    if(ds==5){
                        preco.setText("145.00€");

                    }
                    if(ds==6){
                        preco.setText("173.00€");

                    }
                    if(ds==7){
                        preco.setText("195.00€");

                    }
                    if(ds>7){
                        preco.setText("sob consulta");

                    }
                }
                else{
                    if(ds==1){
                        preco.setText("23.00€");

                    }
                    if(ds==2){
                        preco.setText("40.00€");

                    }
                    if(ds==3){
                        preco.setText("54.00€");

                    }
                    if(ds==4){
                        preco.setText("56.00€");

                    }
                    if(ds==5){
                        preco.setText("70.00€");

                    }
                    if(ds==6){
                        preco.setText("84.00€");

                    }
                    if(ds==7){
                        preco.setText("98.00€");

                    }
                    if(ds>7){
                        preco.setText("sob consulta");

                    }

                }

                new AlertDialogWrapper.Builder(alugarmoto.this)
                        .setTitle("Aluguer")
                        .setMessage("Deseja fazer o aluguer")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String result="";
                                String mt;

                                mt=motas.getSelectedItem().toString();


                                List<NameValuePair> nomes = new ArrayList<NameValuePair>(1);

                                //adicionar as strings a lista


                                nomes.add(new BasicNameValuePair("nome", String.valueOf(mt)));

                                try {
                                    HttpClient httpclient = new DefaultHttpClient();
                                    HttpPost httppost = new HttpPost("http://motoporto.esy.es/veralgs.php");
                                    httppost.setEntity(new UrlEncodedFormEntity(nomes));
                                    HttpResponse response = httpclient.execute(httppost);
                                    HttpEntity entity = response.getEntity();
                                    is = entity.getContent();

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

                                }
                                catch (Exception ex){
                                    Toast.makeText(alugarmoto.this,"Ocorreu um erro"+ex.toString(),Toast.LENGTH_LONG).show();
                                }


                                if(result.length()==5){
                                    Toast.makeText(alugarmoto.this,"Esta mota já está alugada",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    String m,Mail,Telemovel,Contribuinte,mota,Dia,Mes,Ano,Local,ndias;

                                    //envia o mail
                                    envaimail();

                                    m=morada.getText().toString();
                                    Mail=mail.getText().toString();
                                    Telemovel=telemovel.getText().toString();
                                    Contribuinte=contribuinte.getText().toString();
                                    mota=motas.getSelectedItem().toString();
                                    Dia=dia.getText().toString();
                                    Mes=mes.getText().toString();
                                    Ano=ano.getText().toString();
                                    ndias=Ndias.getText().toString();
                                    Local=local.getText().toString();
                                    if(Local.length()==0){
                                        Local="Empresa";
                                    }


                                    //faz o registo
                                    List<NameValuePair> dados= new ArrayList<NameValuePair>(1);

                                    //adicionar as strings a lista


                                    dados.add(new BasicNameValuePair("nome",String.valueOf(n)));
                                    dados.add(new BasicNameValuePair("morada",String.valueOf(m)));
                                    dados.add(new BasicNameValuePair("email",String.valueOf(Mail)));
                                    dados.add(new BasicNameValuePair("tel",String.valueOf(Telemovel)));
                                    dados.add(new BasicNameValuePair("contribuinte",String.valueOf(Contribuinte)));
                                    dados.add(new BasicNameValuePair("moto",String.valueOf(mota)));
                                    dados.add(new BasicNameValuePair("dia",String.valueOf(Dia)));
                                    dados.add(new BasicNameValuePair("mes",String.valueOf(Mes)));
                                    dados.add(new BasicNameValuePair("ano",String.valueOf(Ano)));
                                    dados.add(new BasicNameValuePair("local",String.valueOf(Local)));
                                    dados.add(new BasicNameValuePair("dias",String.valueOf(ndias)));








                                    //Faz a conexão a base de dados

                                    try{
                                        HttpClient cliente=new DefaultHttpClient();
                                        HttpPost publica=new HttpPost("http://motoporto.esy.es/regalg.php");
                                        publica.setEntity(new UrlEncodedFormEntity(dados));
                                        HttpResponse resposta= cliente.execute(publica);
                                        HttpEntity ent= resposta.getEntity();
                                        is = ent.getContent();

                                        Toast.makeText(alugarmoto.this,"Registo efetuado com sucesso",Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){
                                        Toast.makeText(alugarmoto.this,"Ocorreu um erro"+e.toString(),Toast.LENGTH_LONG).show();

                                    }


                                }

                            }
                        })
                        .show();






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
        email.putExtra(Intent.EXTRA_TEXT, "Nome: "+nome.getText().toString()+"\n"+"E-mail: "+mail.getText().toString()+"\n"+"Morada: "+morada.getText().toString()+"\n"+"Telemovél: "+telemovel.getText().toString()+"\n"+"Contribuinte: "+ contribuinte.getText().toString()+"\n"+"Nome moto: "+ motas.getSelectedItem().toString()+ "\n" + "Preço: "+preco.getText().toString());
        //verifica se existe algum cliente de email no dispositivo
        try {

            startActivity(Intent.createChooser(email, "Enviar email através de:"));

        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(alugarmoto.this, "Nenhum cliente de email encontrado", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alugarmoto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.tersessao) {
            logout=new Intent(alugarmoto.this,iniciar_sessao.class);
            startActivity(logout);
        }

        return super.onOptionsItemSelected(item);
    }
}
