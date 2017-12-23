package com.example.daniel.tecnoquiz7;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Db_quiz extends SQLiteOpenHelper {

    private static final String Banco_Pergunta = "Db_quiz";
    private static final int Versao = 1;
    private static final String TAG = "usuario";
    private static final String Tabela_Pergunta = "Tabela_Pergunga";
    private static final String Tabela_Resposta = "Tabela_Resposta";

    private static final String PATH_DB = "/data/user/0/com.example.daniel.tecnouiz7/database/Db_quiz";
    private Context mContext;
    private SQLiteDatabase db;


    public Db_quiz(Context context) {
        super(context, Banco_Pergunta, null, Versao);
        this.mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        openDB();
        String createTable1 = "Create Table if not exists Pergunta (_id_Pergunta integer primary key autoincrement,"
                + "niveis_pergunta smallint(1)," + "pergunta Varchar(100)," + "id_Resposta integer,"
                + "Foreign Key(id_Resposta) References Resposta(_id_Resposta)";

        String createTable2 = "Create Table if not exists Resposta (_id_Resposta integer primary key autoincrement,"
                + "resposta varchar(30)," + "id_Pergunta integer,"
                + "Foreign Key(id_pergunta) References Pergunta(_id_Pergunta)";

        //db.execSQL("Create Table if not exist Categoria (_id_Cateoria primary Key autoincrement,"
        //+ "nome_categoria varchar(20))");

        //db.execSQL("Create Table if not exist Jogador (_id_Jogador integer primary key autoincrement,"
        // + "nome varchar(40)," + "pontuação integer),");


        try {
            Log.i(TAG, "Tabelas sendo Criadas");
            db.execSQL(createTable1);
            db.execSQL(createTable2);
            Log.i(TAG, "Tabelas Criadas com Sucesso");

        } catch (SQLException e) {
            Log.e(TAG, "Tabelas não Criadas"+ e);


        }finally {
            db.close();
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versao_antiga, int versao_nova) {

    }


            private void openDB (){
        if (!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE,null);
        }

    }




}
