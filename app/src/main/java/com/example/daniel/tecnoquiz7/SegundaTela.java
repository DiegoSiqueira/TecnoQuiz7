package com.example.daniel.tecnoquiz7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SegundaTela extends AppCompatActivity {

    static final String Tag ="tecno";
    RadioButton rdA,rdB, rdC, rdD;

    TextView tvPergunta, tvPontuacao;
    Button btResponder, Avancar;
    RadioGroup rdGrupe;
    Db_quiz banco;

    private final String TAG = "usuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Segunda tela");
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Layout segunda tela");
        setContentView(R.layout.segundatela);
        Log.i(TAG, "carregando nome do Usuario");
        Bundle args =  getIntent().getExtras();
        String usuario = args.getString("usuario");
        Log.i(TAG, "Nome usuario: " + usuario);

        final int pontuacao = args.getInt("pontuacao");
        Log.i(TAG, "Pontuacao : " + pontuacao);

        final byte nivel_pergunta = 1;
        Log.i(TAG, "Nivel facil: " + nivel_pergunta);
        final byte nivel_pergunta2 = 2;
        Log.i(TAG, "Nivel medio:" + nivel_pergunta2);
        final byte nivel_pergunta3 = 3;
        Log.i(TAG, "Nivel dificil:" + nivel_pergunta3);


        int idPergunta = args.getInt("");
        Log.i(TAG, "Pergunta id : " + idPergunta);
        String pergunta = "Quais são os algarismos que compõem o sistema Binarios?";
        Log.i(TAG,"Pergunta : " + pergunta);

        Log.i(TAG, "Carregando alternativas");
        int idA = 1;
        String a = "A. 2 e 1";

        int idB = 2;
        String b = "B. 1,  2 e 3";

        int idC = 3;
        String c = "C. 2 e 3";

        int idD = 4;
        String d = "D. 0 e 1";

        final String resposta_certa = d;
        Log.i(TAG, "Resposta id : " + resposta_certa);

        Log.i(TAG, "Pegando referências da GUI");

        rdA = (RadioButton) findViewById(R.id.rdA);
        rdB = (RadioButton) findViewById(R.id.rdB);
        rdC = (RadioButton) findViewById(R.id.rdC);
        rdD = (RadioButton) findViewById(R.id.rdD);
        tvPergunta = (TextView) findViewById(R.id.tvPergunta);
        tvPontuacao = (TextView) findViewById(R.id.tvPontuacao);
        btResponder = (Button) findViewById(R.id.btResponder);
        Avancar = (Button) findViewById(R.id.btAvancar);
        rdGrupe = (RadioGroup) findViewById(R.id.radioGroup);


        Log.i(TAG, "Inserindo texto dos componentes da GUI");

        tvPergunta.setText(pergunta);
        Log.i(TAG, "Pergunta");
        rdA.setText(a);
        Log.i(TAG, "A");
        rdB.setText(b);
        Log.i(TAG, "B");
        rdC.setText(c);
        Log.i(TAG, "C");
        rdD.setText(d);
        Log.i(TAG, "D");
        tvPontuacao.setText(Integer.toString(pontuacao));
        Log.i(TAG, "Pontuação");


        banco = new Db_quiz(this);


        Avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rdA.isChecked() || !rdB.isChecked() || !rdC.isChecked() || !rdD.isChecked())
                    Toast.makeText(SegundaTela.this, "Vc Não Confirmou a Resposta no Botão Responder", Toast.LENGTH_SHORT).show();

            }
        });


        btResponder.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {



             Log.i(TAG, "Evento do Botão bt_Responder");
             int op = rdGrupe.getCheckedRadioButtonId();
             Log.i(TAG, "RadioButton");
             RadioButton r = (RadioButton) findViewById(op);
             Log.i(TAG, "resposta_usuario");
             String resposta_usuario = r.getText().toString();

             Log.i(TAG, "Verificando Resposta");
             if (resposta_usuario == resposta_certa ){
                 Toast.makeText(SegundaTela.this, "Resposta Correta", Toast.LENGTH_SHORT).show();


                 //atribuindo pontuação

                 int nova_pontuacao = 1;


                 switch (nivel_pergunta){
                     case 1 :
                         nova_pontuacao = pontuacao + 50;
                         break;
                     case 2 :
                         nova_pontuacao = pontuacao + 75;
                         break;
                     case 3:
                         nova_pontuacao = pontuacao + 100;
                 }

                 tvPontuacao.setText(Integer.toString(nova_pontuacao));


             }else {
                 Toast.makeText(SegundaTela.this, resposta_certa, Toast.LENGTH_SHORT).show();


             }



         }
     });

    }


}
