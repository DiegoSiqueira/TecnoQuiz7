package com.example.daniel.tecnoquiz7;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Db_quiz extends SQLiteOpenHelper {

    private static final String Banco_Pergunta = "Db_quiz";
    private static final int Versao = 1;
    private static final String TAG = "tecno";
    //private static final String Tabela_Pergunta = "Tabela_Pergunga";
    //private static final String Tabela_Resposta = "Tabela_Resposta";


    private Context Context;
    private SQLiteDatabase db;


    public Db_quiz(Context context) {
        super(context, Banco_Pergunta, null, Versao);
        this.Context = context;
        db = getWritableDatabase();
        Log.i(TAG, "exibindo");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Tabelas sendo Criadas");
        try {
            db.execSQL("Create table if not exists Pergunta (_id_pergunta primary key," +
                    "nivel SMALLINT (5) not null," +
                    "pergunta text not null)");

            db.execSQL("CREATE TABLE IF NOT EXISTS resposta (" +
                    "  _id_resposta INTEGER ," +
                    "  resposta VARCHAR(50) NULL," +
                    "  PRIMARY KEY (_id_resposta))" );

            db.execSQL("CREATE TABLE IF NOT EXISTS Pergunta_resposta (\n" +
                    "                     _id_pergunta INT NOT NULL,\n" +
                    "                      _id_resposta INT NOT NULL,\n" +
                    "                      correto TINYINT NULL,\n" +
                    "                     CONSTRAINT fk_Pergunta_has_resposta_Pergunta1\n" +
                    "                      FOREIGN KEY (_id_pergunta)\n" +
                    "                        REFERENCES Pergunta (_id_pergunta)\n" +
                    "                        ON DELETE cascade\n" +
                    "                        ON UPDATE cascade,\n" +
                    "                      CONSTRAINT fk_Pergunta_has_resposta_resposta1\n" +
                    "                       FOREIGN KEY (_id_resposta)\n" +
                    "                        REFERENCES resposta (_id_resposta)\n" +
                    "                        ON DELETE cascade\n" +
                    "                        ON UPDATE cascade," +
                    "PRIMARY KEY(_id_pergunta, _id_resposta));");

            /*db.execSQL("Create Table if not exist Categoria (_id_Cateoria primary Key autoincrement,"
            + "nome_categoria varchar(20))");

            db.execSQL("Create Table if not exist Jogador (_id_Jogador integer primary key autoincrement,"
             + "nome varchar(40)," + "pontuação integer),");*/

        } catch (SQLException e) {
            Log.e(TAG, "Tabelas não Criadas" + e);


        }
    }

    public boolean insertPerguta(Db_quiz quiz) {
        SQLiteDatabase db = quiz.getWritableDatabase();
        Log.i(TAG, "INSERINDO PERGUNTAS");

        String SQLpergunta = "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (1,1,'A primeira geração dos computadores é marcada pela utilização de:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (2,1,'A primeira geração dos computadores ocorreu durante o período de:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (3,1,'Um dos representantes da primeira geração dos computadores foi o ENIAC. Ele possuía 17.468 válvulas, e pesava:');" ;
                /*"INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (4,2,'A palavra bug (inseto em inglês) é empregada atualmente para designar um:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (5,3,'A segunda geração de computadores foi marcada pela substituição da válvula pelo:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (6,2,'Os transistores foram incorporados aos computadores no final da década de:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (7,3,' Para representar as instruções de máquina a linguagem assembly possibilita a utilização de:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (8,2,'Em qual geração surgiram as linguagens de auto nível como Fortran e Cobol');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (9,2,'Periodo pelo qual se estendeu a segunda geração dos computadores');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (10,2,'A terceira geração de computadores é marcada pela utilização dos circuitos integrados, feitos de silício. Também conhecidos como:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (11,1,'Os computadores da quarta geração são reconhecidos pelo surgimento dos:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (12,2,'Em qual geração foram desenvolvidas as linguagens de programação orientada a objeto C++ e Smalltalk');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (13,1,'Na quinta geração surgiram arquitetura de quantos bits');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (14,2,'A quinta geração está sendo marcada pela');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (15,3,'A terceira geração dos computadores teve um período de');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (16,3,'Em um computador o número 2012, em base decimal, será representado, em base binária, por');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (17,3,'Dado o número binaria (110111010110)2 converta para base decimal');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (18,3,'A soma entre os possíveis números binários (1,0): 0+0=*, 0+1=* , 1+0=* , 1+1=* . A soma é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (19,3,'Subtração na base binaria do número (101101-100111) é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (20,3,'Multiplicação binaria do número (11101) x (1010) é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (21,2,'Faça a conversão da base (917)16 hexadecimal para decimal');"+
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (22,2,'A subtração entre os número binários 101101-100111 é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (23,1,'Número binário: aquele formado apenas com os algarismos');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (24,3,'É o sistema utilizado por máquinas com circuitos digitais para interpretar informações e executar ações');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (25,2,'Número decimal: aquele formado apenas com os algarismos');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (26,1,'A soma de 1+1+1=  à');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (27,3,'Os microprocessadores percebem somente sinais elétricos, distinguindo-os em dois níveis de voltagem');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (28,3,'O sistema de numeração binário é usado em várias áreas do conhecimento como matemática e física e foi criado no século');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (29,3,'O sistema binário consiste em um sistema numérico também conhecido como base 2, porque as suas unidades são potências de');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (30,1,'Um número do sistema decimal pode ser representado de acordo com o código binário por');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (31,1,'Um sistema computacional consiste num conjunto de dispositivos eletrônicos (hardware) capazes de');" +
                "INSERT INTO pergunta (_id_perunta,nivel,pergunta) VALUES (32,2,'Qual o hardware corresponde às partes eletrônicas e mecânicas (rígidas) que possibilitam a existência do');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (33,2,'Um sistema baseado em computador é caracterizado por alguns elementos fundamentais');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (34,3,'A parte física de um sistema de computação corresponde a todos os elementos materiais que constituem o computador e seus periféricos seus componentes básicos são:');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (35,3,'A unidade central de processamento (UCP) é responsável pela atividade principal de processamento dentro de um sistema computacional e tem por função');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (36,2,'O elemento central de um microcomputador é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (37,3,'Configuração típica de um computador consiste de três elementos básicos de hardware os quais são');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (38,3,'Os dispositivos de Entrada e Saída são');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (39,1,'Os circuitos de um computador que executam operações sobre dados é chamado?');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (40,1,'Todos computadores executam três operações básicas que são');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (41,1,'O transistor é um componente eletrônico criado na década de');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (42,1,'O princípio básico de utilizar a eletrônica é');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (43,1,'As gerações de computadores surgiram com a');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (44,1,'Quais são os componentes básicos de um computador ');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (45,2,'As quatro funções básicas de um computador são');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (46,3,'A evolução dos sistemas operacionais foi uma consequência dos avanços ocorridos no');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (47,2,'Pode-se criar um sistema tão grande e complexo como um sistema operacional somente');" +
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (48,2,'O Sistema de Arquivos Unificado está presente em todas as variantes');";
                "INSERT INTO pergunta (_id_pergunta,nivel,pergunta) VALUES (49,2,'O Scheduler é o programa encarregado de');";*/

        String SQLresposta = "INSERT INTO resposta(_id_resposta,resposta) VALUES (1,'Válvulas Eletrônicas');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (2,'Transistores');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (3,'Software');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (4,'Windows 10');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (5,'2011-2012');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (6,'1995-2000');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (7,'2000-1995');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (8,'1946-1954');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (9,'0,5 Toneladas');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (10,'10 Toneladas');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (11,'40 Toneladas');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (12,'30 Toneladas');" ;

               /* "INSERT INTO resposta(_id_resposta,resposta) VALUES (13,'Mal-estar no programador');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (14,'Ansiedade no software');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (15,'Incapacidade do soft');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (16,'Defeito no software');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (17,'Transformador');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (18,'Transistor');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (19,'Capacitor');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (20,'Resistor');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (21,'50');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (22,'90');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (23,'60');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (24,'70');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (25,'Atamos');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (26,'Aemônicos');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (27,'Mnemônicos');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (28,'Símbolos');" +

                "INSERT INTO resposta(_id_resposta,resposta) VALUES (29,'Segunda guerra');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (30,'Primeira geração');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (31,'Segunda geração');" +
                "INSERT INTO resposta(_id_resposta,resposta) VALUES (32,'Terceira geração');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (33,'1900-2000');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (34,'1955-1964');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (35,'1670-2012');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (36,'2016-2017');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (37,'Microchips');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (38,'Microcomputadores');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (39,'Micro SD');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (40,'Micro software');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (41,'Aplicativos');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (42,'Sistemas operacionais');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (43,'Processadores');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (44,'Notebook');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (45,'Quinta geração');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (46,'Quarta geração');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (47,'Primeira geração');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (48,'Segunda geração');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (49,'60bits');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (50,'32bits');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (51,'84bits');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (52,'64bits');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (53,'Inteligência artificial e por sua conectividade');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (54,'Excelentes maquinas disponíveis');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (55,'Mercado de soft movimentado');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (56,'randes empresas')" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (57,'1965-1999');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (58,'1964-1977');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (59,'1780-2000');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (60,'2000-2014');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (61,'110111');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (62,'11111011100');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (63,'111110111000');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (64,'111110111');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (65,'34567');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (66,'0644367');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (67,'2152337');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (68,'3542');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (69,'0 ,0 ,1,10');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (70,'0, 1, 1, 10 ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (71,'0,1,0,10');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (72,'0,1,1,2');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (73,'000110000110');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (74,'001110');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (75,'1100011');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (76,'011000');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (77,'1110010100');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (78,'10100010');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (79,'1001000000');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (80,'1110001010');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (81,'484');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (82,'956');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (83,'907');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (84,'2327');" +


                "INSERT INTO resposta(id_resposta,resposta) VALUES (85,'000111');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (86,'000110');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (87,'110000');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (88,'010100');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (89,'1 e 0');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (90,'0 e 0');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (91,'1 e 1');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (92,'2 e 11');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (93,'Sistema binário');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (94,'Sistema de informática');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (95,'Sistema de software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (96,'Sistema de gestão');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (97,'0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (98,'0,1,11,111,110');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (99,'0,1,2,3,4,5,6,7,8,9,10');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (100,'0,1,2,3,4,5,6,7,8,9,10,11');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (101,'3');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (102,'11');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (103,'10');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (104,'100');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (105,'Vertical e diagonal');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (106,'Horizontal e vertical');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (107,'Alto e baixo');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (108,'Médio e diagonal');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (109,'VX');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (110,'XV');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (111,'x');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (112,'XVII');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (113,'2,2');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (114,'2,5');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (115,'3');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (116,'2');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (117,'0 e 4');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (118,'0 e 3');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (119,'0 e 2');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (120,'0 e 1 ');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (121,'Processar informações de acordo com um programa');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (122,'Processar dados sem interferir em informações');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (123,'Processar dados fundamentais sem precisar de qualquer informações');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (124,'Processar todos os dados com auxílio de banco de dados');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (125,'Banco de dados');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (126,'Atualizações de informações ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (127,'Software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (128,'Usuário');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (129,'Hardware, Software, Informações, Usuários, Procedimentos ou Tarefas, Documentação.');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (130,'Banco  de dados,  Interface gráfica, Informações, Usuários');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (131,'Documentação, Interface  gráfica, Software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (132,'Rede se software, Banco de dados, Procedimentos e tarefas');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (133,'\\u2028 Unidade Central de Processamento (CPA ou CPU), \\u2021Memórias\n" +
                ", \\u2022Dispositivos de entrada e saída (put/output)\n');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (134,') \\u2022 Unidade Central de Processamento (UCP ou CPU), \\u2022Memórias\n" +
                ", \\u2022Dispositivos de entrada e saída (input/output)\n');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (135,'\\u2000 Unidade Central de Processamento (UCP ou CPU), \\u2022Memórias\n" +
                ", \\u2022Dispositivos de entrada e saída (input/ucp)\n');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (136,') \\u2022 Unidade Central de Informações (UCP ou CPU), \\u6022Memórias\n" +
                ", \\u2020Dispositivos de entrada e saída (input/output)\n');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (137,'Executar os arquivos internos na memória principal');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (138,'Executar os dados armazenados na memória externa');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (139,'Executar os Software na memória principal, buscando cada instrução');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (140,'Executar os programas armazenados na memória principal');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (141,'Placa Mãe');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (142,'Banco de informações');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (143,'Softwares instalados');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (144,'Sistema Computacional interno');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (145,'A unidade de processamento, Dispositivos de entrada e saída de dados, Dispositivos de armazenamento');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (146,'Unidade de banco de dados, dispositivos de armazenamento');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (147,'Dispositivos internos, armazenamentos do software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (148,'A unidade de processamento,armazenamentos do software');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (149,'Memória RAM, Placa mãe, mouse');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (150,'Disco rígido, Disquetes, teclado');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (151,'O teclado, o monitor de vídeo e impressora');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (152,'Memória RAM, Monitor, Programas');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (153,'Circuito modulado de banco de dados');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (154,'Unidade Central de Processamento UCP');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (155,'STF- Sistema de trafego de Informações');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (156,'Copilação de Software em sistemas internos');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (157,'Movimentação de dados, Processamentos de dados, Armazenamento de dados');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (158,'Movimentação de informações , Processamentos de bancos, Armazenamento internos');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (159,'Levantamentos de requisitos, processamentos de sistemas, Armazenamento interno');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (160,'Banco de dados, processamentos de sistemas, analise do software');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (161,'1949');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (162,'1950');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (163,'1951');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (164,'1952');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (165,'Criar bancos de dados e fazer integração');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (166,'Desenvolver em curto período um software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (167,'Transformar informações em códigos de redes');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (168,'Representar dados e depois poder executar operações com eles');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (169,'Com a fabricação de memorias utilizando códigos científicos');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (170,'As válvulas para armazenar dados');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (171,'Miniaturização dos transistores e sua integração em chips ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (172,'Criação de computadores e inclusão de banco de dados.');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (173,'Programas, mouse, teclado');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (174,'Processador, memória, HD, Placa-mãe, placa de vídeo e monitor');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (175,'Placa-mãe, mouse, teclado, software');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (176,'HD, mouse, teclado e informações de programas');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (177,'Informar dados, ocultar vírus, emitir sinal de alerta.');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (178,'Entrada, processamento, saída e armazenamento');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (179,'Processamento de dados, buscas de malwers');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (180,'Saídas, compartilhamento e entrada');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (181,'Sistema limitado');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (182,'Sistema de Entrada');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (183,'Sistema de Saída');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (184,'Hardware dos computadores');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (185,'Criando e removendo vírus');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (186,'Dividindo-o em pequenas partes');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (187,'Gerenciando sistemas');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (188,'Atualizando a memoria principal');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (189,'Unix');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (190,'Linux');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (191,'Schedule');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (192,'Inux');" +

                "INSERT INTO resposta(id_resposta,resposta) VALUES (193,'Agendar os processos');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (194,'Excluir processos ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (195,'Incluir variáveis ');" +
                "INSERT INTO resposta(id_resposta,resposta) VALUES (196,'Excluir variáveis ');";*/
Log.i(TAG,"testando");
        String SQLpergunta_resposta = "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta, correto) VALUES (1,1,1);"
                + "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (1,2,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (1,3,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (1,4,0);" +

                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (2,5,0);"
                + "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (2,6,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (2,7,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (2,8,1);" +

                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (3,9,0);"
                + "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (3,10,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (3,11,0);" +
                "INSERT INTO pergunta_resposta(_id_pergunta, _id_resposta,correto) VALUES (3,12,1);" ;

                /*"INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (4,13,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (4,14,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (4,15,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (4,16,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (5,17,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (5,18,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (5,19,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (5,20,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (6,21,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (6,22,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (6,23,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (6,24,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (7,25,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (7,26,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (7,27,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (7,28,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (8,29,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (8,30,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (8,31,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (8,32,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (9,33,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (9,34,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (9,35,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (9,36,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (10,37,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (10,38,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (10,39,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (10,40,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (11,41,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (11,42,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (11,43,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (11,44,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (12,45,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (12,46,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (12,47,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (12,48,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (13,49,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (13,50,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (13,51,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (13,52,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (14,53,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (14,54,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (14,55,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (14,56,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (15,57,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (15,58,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (15,59,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (15,60,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (16,61,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (16,62,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (16,63,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (16,64,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (17,65,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (17,66,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (17,67,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (17,68,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (18,69,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (18,70,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (18,71,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (18,72,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (19,73,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (19,74,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (19,75,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (19,76,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (20,77,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (20,78,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (20,79,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (20,80,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (21,81,0);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (21,82,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (21,83,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (21,84,1);"+

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (22,85,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (22,86,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (22,87,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (22,88,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (23,89,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (23,90,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (23,91,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (23,92,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (24,93,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (24,94,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (24,95,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (24,96,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (25,97,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (25,98,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (25,99,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (25,100,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (26,101,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (26,102,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (26,103,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (26,104,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (27,105,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (27,106,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (27,107,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (27,108,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (28,109,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (28,110,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (28,111,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (28,112,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (29,113,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (29,114,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (29,115,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (29,116,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (30,117,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (30,118,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (30,119,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (30,120,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (31,121,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (31,122,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (31,123,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (31,124,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (32,125,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (32,126,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (32,127,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (32,128,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (33,129,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (33,130,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (33,131,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (33,132,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (34,133,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (34,134,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (34,135,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (34,136,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (35,137,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (35,138,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (35,139,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (35,140,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (36,141,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (36,142,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (36,143,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (36,144,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (37,145,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (37,146,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (37,147,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (37,148,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (38,149,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (38,150,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (38,151,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (38,152,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (39,153,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (39,154,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (39,155,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (39,156,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (40,157,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (40,158,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (40,159,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (40,160,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (41,161,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (41,162,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (41,163,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (41,164,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (42,165,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (42,166,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (42,167,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (42,168,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (43,169,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (43,170,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (43,171,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (43,172,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (44,173,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (44,174,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (44,175,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (44,176,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (45,177,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (45,178,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (45,179,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (45,180,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (46,181,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (46,182,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (46,183,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (46,184,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (47,185,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (47,186,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (47,187,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (47,188,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (48,189,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (48,190,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (48,191,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (48,192,0);" +

                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (49,193,1);"
                + "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (49,194,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (49,195,0);" +
                "INSERT INTO pergunta_resposta(id_pergunta,id_resposta,correto) VALUES (49,196,0)";*/
        Log.i(TAG, "inserindo pergutas");
        try {
            db.execSQL(SQLpergunta);
            db.execSQL(SQLresposta);
            db.execSQL(SQLpergunta_resposta);
            return true;


        } catch (Exception e) {
            Log.e(TAG, "Erro insert" + e);
            return false;

        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versao_antiga, int versao_nova) {

    }
    public List<HashMap<String , Object>> buscarPergunta(Db_quiz jogador, int nivel){
        Cursor cursor = null;
        try {
            Log.i(TAG, "preparando acesso");
            SQLiteDatabase banco = jogador.getWritableDatabase();
            cursor = banco.rawQuery("Select _id_pergunta, pergunta From Pergunta Where nivel=" + nivel + " order by random () limit 1", null);
        }catch (Exception e) {
            Log.i(TAG, "Erro ao consultar o BD:" + e);
        }
        cursor.moveToFirst();

        ArrayList<HashMap<String, Object>> pergunta = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < cursor.getColumnCount(); i++){
            HashMap<String, Object> item = new HashMap<String, Object>();

            String descricao_pergunta = cursor.getString(1);
            String id = cursor.getString(0);

            item.put("pergunta", descricao_pergunta );
            item.put("id", id);

            pergunta.add(item);
            cursor.moveToNext();

        }
        cursor.close();
        return pergunta;

    }


}
