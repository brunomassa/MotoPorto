package com.example.tiago.aplicacao_pat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 13-05-2015.
 */
public class Aluger extends ActionBarActivity {

    Spinner mota,carro;
    Button registar;
    Intent login;
    EditText nome,mail,pass,confirmarpass,morada,telemovel,contribuinte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alugar_layout);

        //define a orientação do ecra
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Edittext
        nome=(EditText)findViewById(R.id.editText2);
        mail=(EditText)findViewById(R.id.editText3);
        pass=(EditText)findViewById(R.id.editText4);
        confirmarpass=(EditText)findViewById(R.id.editText5);
        morada=(EditText)findViewById(R.id.editText6);
        telemovel=(EditText)findViewById(R.id.editText9);
        contribuinte=(EditText)findViewById(R.id.editText10);

        //spinner mota
        mota=(Spinner)findViewById(R.id.motaopcoes);
        //vai buscar as opcoes
        ArrayAdapter<CharSequence> adptador = ArrayAdapter.createFromResource(Aluger.this, R.array.Mota,android.R.layout.simple_spinner_item);
        //mete as opcoes no spinner
        adptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mota.setAdapter(adptador);



        //spinner carro
        carro=(Spinner)findViewById(R.id.carroopcoes);
        //vai buscar as opcoes
        ArrayAdapter<CharSequence> adptador1 = ArrayAdapter.createFromResource(Aluger.this, R.array.Carro,android.R.layout.simple_spinner_item);
        //mete as opcoes no spinner
        adptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        carro.setAdapter(adptador1);

        registar=(Button)findViewById(R.id.button5);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().length()==0|| mail.getText().toString().length()==0|| pass.getText().toString().length()==0||
                        confirmarpass.getText().toString().length()==0|| morada.getText().toString().length()==0|| telemovel.getText().toString().length()==0||
                        contribuinte.getText().toString().length()==0|| mota.getSelectedItem().toString().equals("É titular de carta de condução de mota") || carro.getSelectedItem().toString().equals("É titular de carta de condução de Carro")){

                    Toast.makeText(Aluger.this,"Preencha todos os campos e responda a todas as questoes",Toast.LENGTH_LONG).show();

                }
                else {
                    if(telemovel.getText().toString().length()<9||contribuinte.getText().toString().length()<9)
                    {
                        Toast.makeText(Aluger.this,"O numero de telemovel e o numero de contribuinte devem conter 9 numeros",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (pass.getText().toString().equals(confirmarpass.getText().toString())) {
                            if (mota.getSelectedItem().toString().equals("Sim")
                                    && carro.getSelectedItem().toString().equals("Sim")) {
                                Toast.makeText(Aluger.this, "registo válido", Toast.LENGTH_LONG).show();

                                //strings
                                String n,m,p,cp,Mail,Telemovel,Contribuinte,cc,cm;
                                InputStream is;

                                //colocar dados nas strings

                                n=nome.getText().toString();
                                Mail=mail.getText().toString();
                                p=pass.getText().toString();
                                cp=confirmarpass.getText().toString();
                                Telemovel=telemovel.getText().toString();
                                Contribuinte=contribuinte.getText().toString();
                                m=morada.getText().toString();
                                cm=mota.getSelectedItem().toString();
                                cc=carro.getSelectedItem().toString();

                                //namevaluepairs

                                List<NameValuePair> nomes= new ArrayList<NameValuePair>(1);

                                //adicionar as strings a lista


                                nomes.add(new BasicNameValuePair("nome",String.valueOf(n)));
                                nomes.add(new BasicNameValuePair("pass",String.valueOf(p)));
                                nomes.add(new BasicNameValuePair("confpass",String.valueOf(cp)));
                                nomes.add(new BasicNameValuePair("morada",String.valueOf(m)));
                                nomes.add(new BasicNameValuePair("email",String.valueOf(Mail)));
                                nomes.add(new BasicNameValuePair("tel",String.valueOf(Telemovel)));
                                nomes.add(new BasicNameValuePair("contribuinte",String.valueOf(Contribuinte)));
                                nomes.add(new BasicNameValuePair("cartamota",String.valueOf(cm)));
                                nomes.add(new BasicNameValuePair("cartacarro",String.valueOf(cc)));







                                //Faz a conexão a base de dados

                                try{
                                    HttpClient cliente=new DefaultHttpClient();
                                    HttpPost publica=new HttpPost("http://motoporto.esy.es/registo.php");
                                    publica.setEntity(new UrlEncodedFormEntity(nomes));
                                    HttpResponse resposta= cliente.execute(publica);
                                    HttpEntity ent= resposta.getEntity();
                                    is = ent.getContent();

                                    Toast.makeText(Aluger.this,"Registo efetuado com sucesso",Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e){
                                    Toast.makeText(Aluger.this,"Ocorreu um erro"+e.toString(),Toast.LENGTH_LONG).show();

                                }
                            }
                            if (mota.getSelectedItem().toString().equals("Nao")
                                    && carro.getSelectedItem().toString().equals("sim")) {
                                Toast.makeText(Aluger.this, "registo válido", Toast.LENGTH_LONG).show();

                                //strings
                                String n,m,p,cp,Mail,Telemovel,Contribuinte,cc,cm;
                                InputStream is;

                                //colocar dados nas strings

                                n=nome.getText().toString();
                                Mail=mail.getText().toString();
                                p=pass.getText().toString();
                                cp=confirmarpass.getText().toString();
                                Telemovel=telemovel.getText().toString();
                                Contribuinte=contribuinte.getText().toString();
                                m=morada.getText().toString();
                                cm=mota.getSelectedItem().toString();
                                cc=carro.getSelectedItem().toString();

                                //namevaluepairs

                                List<NameValuePair> nomes= new ArrayList<NameValuePair>(1);

                                //adicionar as strings a lista


                                nomes.add(new BasicNameValuePair("nome",String.valueOf(n)));
                                nomes.add(new BasicNameValuePair("pass",String.valueOf(p)));
                                nomes.add(new BasicNameValuePair("confpass",String.valueOf(cp)));
                                nomes.add(new BasicNameValuePair("morada",String.valueOf(m)));
                                nomes.add(new BasicNameValuePair("email",String.valueOf(Mail)));
                                nomes.add(new BasicNameValuePair("tel",String.valueOf(Telemovel)));
                                nomes.add(new BasicNameValuePair("contribuinte",String.valueOf(Contribuinte)));
                                nomes.add(new BasicNameValuePair("cartamota",String.valueOf(cm)));
                                nomes.add(new BasicNameValuePair("cartacarro",String.valueOf(cc)));







                                //Faz a conexão a base de dados

                                try{
                                    HttpClient cliente=new DefaultHttpClient();
                                    HttpPost publica=new HttpPost("http://motoporto.esy.es/registo.php");
                                    publica.setEntity(new UrlEncodedFormEntity(nomes));
                                    HttpResponse resposta= cliente.execute(publica);
                                    HttpEntity ent= resposta.getEntity();
                                    is = ent.getContent();

                                    Toast.makeText(Aluger.this,"Registo efetuado com sucesso",Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e){
                                    Toast.makeText(Aluger.this,"Ocorreu um erro"+e.toString(),Toast.LENGTH_LONG).show();

                                }
                            }
                            if (mota.getSelectedItem().toString().equals("Sim")
                                    && carro.getSelectedItem().toString().equals("Nao")) {
                                Toast.makeText(Aluger.this, "registo válido", Toast.LENGTH_LONG).show();

                                //strings
                                String n,m,p,cp,Mail,Telemovel,Contribuinte,cc,cm;
                                InputStream is;

                                //colocar dados nas strings

                                n=nome.getText().toString();
                                Mail=mail.getText().toString();
                                p=pass.getText().toString();
                                cp=confirmarpass.getText().toString();
                                Telemovel=telemovel.getText().toString();
                                Contribuinte=contribuinte.getText().toString();
                                m=morada.getText().toString();
                                cm=mota.getSelectedItem().toString();
                                cc=carro.getSelectedItem().toString();

                                //namevaluepairs

                                List<NameValuePair> nomes= new ArrayList<NameValuePair>(1);

                                //adicionar as strings a lista


                                nomes.add(new BasicNameValuePair("nome",String.valueOf(n)));
                                nomes.add(new BasicNameValuePair("pass",String.valueOf(p)));
                                nomes.add(new BasicNameValuePair("confpass",String.valueOf(cp)));
                                nomes.add(new BasicNameValuePair("morada",String.valueOf(m)));
                                nomes.add(new BasicNameValuePair("email",String.valueOf(Mail)));
                                nomes.add(new BasicNameValuePair("tel",String.valueOf(Telemovel)));
                                nomes.add(new BasicNameValuePair("contribuinte",String.valueOf(Contribuinte)));
                                nomes.add(new BasicNameValuePair("cartamota",String.valueOf(cm)));
                                nomes.add(new BasicNameValuePair("cartacarro",String.valueOf(cc)));







                                //Faz a conexão a base de dados

                                try{
                                    HttpClient cliente=new DefaultHttpClient();
                                    HttpPost publica=new HttpPost("http://motoporto.esy.es/registo.php");
                                    publica.setEntity(new UrlEncodedFormEntity(nomes));
                                    HttpResponse resposta= cliente.execute(publica);
                                    HttpEntity ent= resposta.getEntity();
                                    is = ent.getContent();

                                    Toast.makeText(Aluger.this,"Registo efetuado com sucesso",Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e){
                                    Toast.makeText(Aluger.this,"Ocorreu um erro"+e.toString(),Toast.LENGTH_LONG).show();

                                }
                            }
                            if (mota.getSelectedItem().toString().equals("Nao")
                                    && carro.getSelectedItem().toString().equals("Nao")) {
                                Toast.makeText(Aluger.this, "registo inválido", Toast.LENGTH_LONG).show();
                            }


                            //inicia a atividade login
                            login = new Intent(Aluger.this, iniciar_sessao.class);
                            //encerra a atividade
                            Aluger.this.finish();
                        } else {
                            Toast.makeText(Aluger.this, "As palavras-passe devem ser iguais", Toast.LENGTH_LONG).show();
                        }
                    }

                }







            }
        });

    }
}
