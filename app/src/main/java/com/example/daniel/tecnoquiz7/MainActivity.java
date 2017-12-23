package com.example.daniel.tecnoquiz7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    Button game;

    static final String TAG ="tecno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText) findViewById(R.id.ednome);
        game = (Button) findViewById(R.id.btIniciar);

        Log.i(TAG, "layout tela principal carregado com sucesso 1");

      game.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String nome_usuario = nome.getText().toString();
              if (nome_usuario.equals("")){
                  Toast.makeText(MainActivity.this, "Informe seu nome!", Toast.LENGTH_SHORT).show();

              }else {
                  Log.i(TAG, "chama segunda tela 2");
                  Intent segundaTela = new Intent(MainActivity.this, SegundaTela.class);
                  Bundle parametros = new Bundle();
                  parametros.putString("usuario", nome_usuario);
                  segundaTela.putExtras(parametros);
                  startActivity(segundaTela);

              }





          }
      });

    }



}
