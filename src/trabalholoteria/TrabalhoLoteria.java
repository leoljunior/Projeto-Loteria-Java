package trabalholoteria;

import classes.Jogos;
import classes.Loterias;
import factory.LoteriasFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TrabalhoLoteria {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(System.in);

        //Fazendo leitura dos arquivos CSV
        File megaCSV = new File("C:\\Users\\juhh\\Downloads\\mega.csv");
        File quinaCSV = new File("C:\\Users\\juhh\\Downloads\\quina.csv");
        File lotofacilCSV = new File("C:\\Users\\juhh\\Downloads\\lotofacil.csv");
        BufferedReader buffMega = new BufferedReader(new FileReader(megaCSV));
        BufferedReader buffQuina = new BufferedReader(new FileReader(quinaCSV));
        BufferedReader buffLoto = new BufferedReader(new FileReader(lotofacilCSV));

        ArrayList<Jogos> mList = new ArrayList(); //Array de Objetos do tipo Jogos MegaSena
        ArrayList<Jogos> qList = new ArrayList(); //Array de Objetos do tipo Jogos Quina
        ArrayList<Jogos> fList = new ArrayList(); //Array de Objetos do tipo Jogos Lotofacil
        String line = "";

        //Split nas linhas do CSV, salvando no objeto e carregando lista de objetos MegaSena
        try {
            while ((line = buffMega.readLine()) != null) {

                Jogos mega = new Jogos();
                String[] c = line.split(","); //Array de String para separar cada linha do arquivo CSV
                mega.setConcurso(Integer.parseInt(c[0]));
                mega.setData(c[1]);
                for (int i = 2; i < 8; i++) {
                    mega.adcionar(Integer.parseInt(c[i]));
                }
                mList.add(mega);

            }
        } catch (Exception e) {
        }

        //Split nas linhas do CSV, salvando no objeto e carregando lista de objetos Quina
        try {
            while ((line = buffQuina.readLine()) != null) {

                Jogos quina = new Jogos();
                String[] d = line.split(","); //Array de String para separar cada linha do arquivo CSV
                quina.setConcurso(Integer.parseInt(d[0]));
                quina.setData(d[1]);
                for (int i = 2; i < 7; i++) {
                    quina.adcionar(Integer.parseInt(d[i]));
                }
                qList.add(quina);

            }

        } catch (Exception e) {
        }

        //Split nas linhas do CSV, salvando no objeto e carregando lista de objetos Lotofacil
        try {
            while ((line = buffLoto.readLine()) != null) {

                Jogos lotofacil = new Jogos();
                String[] e = line.split(","); //Array de String para separar cada linha do arquivo CSV
                lotofacil.setConcurso(Integer.parseInt(e[0]));
                lotofacil.setData(e[1]);
                for (int i = 2; i < 17; i++) {
                    lotofacil.adcionar(Integer.parseInt(e[i]));
                }
                fList.add(lotofacil);

            }

        } catch (Exception e) {
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int opc = 0, opc1 = 0;
        boolean booAux;

        do {
            System.out.println("*****JOGOS LOTERICOS*****");
            System.out.println("***SELECIONE UMAS DAS OPÇÕES***");
            System.out.println("");
            System.out.println("1..........Mega Sena");
            System.out.println("2..........Quina");
            System.out.println("3..........Lotofácil");
            System.out.println("9..........Sair");
            System.out.println("");

            opc = scn.nextInt();
            System.out.println("");

            //OPÇÃO MEGA SENA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (opc == 1) {

                Loterias jogoMega = LoteriasFactory.getLoterias(opc); //chamando factory para instanciar o objeto

                System.out.println("*****SELECIONE UMA DAS OPÇÕES*****");
                System.out.println("1..........5 números que mais sairam.");
                System.out.println("2..........5 números que menos sairam.");
                System.out.println("3..........Gerar números randomicos.");
                System.out.println("4..........Escolher minhas dezenas.");
                System.out.println("");

                opc1 = scn.nextInt();
                System.out.println("");

                //OPÇÃO 5 NUMEROS QUE MAIS SAIRAM
                if (opc1 == 1) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < mList.size(); j++) {
                        for (int i = 0; i < 6; i++) {
                            vet.add(x, mList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
                 
                    jogoMega.cincoMais(vet);
                }

                //OPÇÃO 5 NUMEROS QUE MENOS SAIRAM
                if (opc1 == 2) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < mList.size(); j++) {
                        for (int i = 0; i < 6; i++) {
                            vet.add(x, mList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
                  
                    jogoMega.cincoMenos(vet);

                }

                //OPÇÃO GERAR RANDOMICOS
                if (opc1 == 3) {
                    jogoMega.randomicos();
                }

                //OPÇÃO ESCOLHER E TESTAR DEZENAS
                if (opc1 == 4) {
                    int aux = 0;
                    System.out.println("JOGO SELECIONADO: MEGA SENA");
                    System.out.println("AS DEZENAS DEVEM ESTAR ENTRE 1 E 60");
                    System.out.println("");

                    for (int i = 0; i < 6; i++) {
                        System.out.println((i + 1) + "ª Dezena: ");
                        aux = scn.nextInt();
                        booAux = jogoMega.validacao(aux);
                        if (booAux) {
                            jogoMega.setDezenas(aux);
                        } else {
                            System.out.println("ERRO: SUAS DEZENAS DEVEM ESTAR ENTRE 1 E 60");
                            System.out.println("INFORME NOVAMENTE A DEZENA");
                            i--;
                        }

                    }

                    System.out.println(jogoMega.getDezenas());

                }
                int acerto = 0;
                for (int i = 0; i < mList.size(); i++) {
                    acerto = jogoMega.confere(jogoMega.getDezenas(), mList.get(i).getDezenas());

                    switch (acerto) {
                        case 4:
                            System.out.println("No concurso de número: " + mList.get(i).getConcurso());
                            System.out.println("Data: " + mList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou na QUADRA!!");
                            System.out.println("");
                            break;
                        case 5:
                            System.out.println("No concurso de número: " + mList.get(i).getConcurso());
                            System.out.println("Data: " + mList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou na QUINA!!");
                            System.out.println("");
                            break;
                        case 6:
                            System.out.println("No concurso de número: " + mList.get(i).getConcurso());
                            System.out.println("Data: " + mList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou na MEGA!!");
                            System.out.println("");
                            break;
                    }

                }

            }

            //OPÇÃO QUINA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (opc == 2) {

                Loterias jogoQuina = LoteriasFactory.getLoterias(opc); //chamando factory para instanciar o objeto

                System.out.println("*****SELECIONE UMA DAS OPÇÕES*****");
                System.out.println("1..........5 números que mais sairam.");
                System.out.println("2..........5 números que menos sairam.");
                System.out.println("3..........Gerar números randomicos.");
                System.out.println("4..........Escolher minhas dezenas.");
                System.out.println("");

                opc1 = scn.nextInt();
                System.out.println("");

                //OPÇÃO 5 NUMEROS QUE MAIS SAIRAM
                if (opc1 == 1) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < qList.size(); j++) {
                        for (int i = 0; i < 5; i++) {
                            vet.add(x, qList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
              
                    jogoQuina.cincoMais(vet);
                }

                //OPÇÃO 5 NUMEROS QUE MENOS SAIRAM
                if (opc1 == 2) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < qList.size(); j++) {
                        for (int i = 0; i < 5; i++) {
                            vet.add(x, qList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
              
                    jogoQuina.cincoMenos(vet);

                }

                //OPÇÃO GERAR RANDOMICOS
                if (opc1 == 3) {
                    jogoQuina.randomicos();
                }

                //OPÇÃO ESCOLHER E TESTAR DEZENAS
                if (opc1 == 4) {
                    int aux = 0;
                    System.out.println("JOGO SELECIONADO: QUINA");
                    System.out.println("AS DEZENAS DEVEM ESTAR ENTRE 1 E 80");
                    System.out.println("");

                    for (int i = 0; i < 5; i++) {
                        System.out.println((i + 1) + "ª Dezena: ");
                        aux = scn.nextInt();
                        booAux = jogoQuina.validacao(aux);
                        if (booAux) {
                            jogoQuina.setDezenas(aux);
                        } else {
                            System.out.println("ERRO: SUAS DEZENAS DEVEM ESTAR ENTRE 1 E 80");
                            System.out.println("INFORME NOVAMENTE A DEZENA");
                            i--;
                        }

                    }

                    System.out.println(jogoQuina.getDezenas());

                }
                int acerto = 0;
                for (int i = 0; i < qList.size(); i++) {
                    acerto = jogoQuina.confere(jogoQuina.getDezenas(), qList.get(i).getDezenas());

                    switch (acerto) {
                        case 2:
                            System.out.println("No concurso de número: " + qList.get(i).getConcurso());
                            System.out.println("Data: " + qList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou no DUQUE!!");
                            System.out.println("");
                            break;
                        case 3:
                            System.out.println("No concurso de número: " + qList.get(i).getConcurso());
                            System.out.println("Data: " + qList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou no TERNO!!");
                            System.out.println("");
                            break;
                        case 4:
                            System.out.println("No concurso de número: " + qList.get(i).getConcurso());
                            System.out.println("Data: " + qList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou na QUADRA!!");
                            System.out.println("");
                            break;
                        case 5:
                            System.out.println("No concurso de número: " + qList.get(i).getConcurso());
                            System.out.println("Data: " + qList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e ganhou na QUINA!!");
                            System.out.println("");
                            break;

                    }

                }
            }

            //OPÇÃO LOTOFACIL/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (opc == 3) {

                Loterias jogoLoto = LoteriasFactory.getLoterias(opc); //chamando factory para instanciar o objeto

                System.out.println("*****SELECIONE UMA DAS OPÇÕES*****");
                System.out.println("1..........5 números que mais sairam.");
                System.out.println("2..........5 números que menos sairam.");
                System.out.println("3..........Gerar números randomicos.");
                System.out.println("4..........Escolher minhas dezenas.");
                System.out.println("");

                opc1 = scn.nextInt();
                System.out.println("");

                //OPÇÃO 5 NUMEROS QUE MAIS SAIRAM
                if (opc1 == 1) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < fList.size(); j++) {
                        for (int i = 0; i < 15; i++) {
                            vet.add(x, fList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
                
                    jogoLoto.cincoMais(vet);
                }

                //OPÇÃO 5 NUMEROS QUE MENOS SAIRAM
                if (opc1 == 2) {
                    ArrayList<Integer> vet = new ArrayList();
                    int x = 0;
                    for (int j = 0; j < fList.size(); j++) {
                        for (int i = 0; i < 15; i++) {
                            vet.add(x, fList.get(j).getDezenas().get(i));
                            x++;
                        }
                    }
               
                    jogoLoto.cincoMenos(vet);
                }

                //OPÇÃO GERAR RANDOMICOS
                if (opc1 == 3) {
                    jogoLoto.randomicos();
                }

                //OPÇÃO ESCOLHER E TESTAR DEZENAS
                if (opc1 == 4) {
                    int aux = 0;
                    System.out.println("JOGO SELECIONADO: LOTOFÁCIL");
                    System.out.println("AS DEZENAS DEVEM ESTAR ENTRE 1 E 25");
                    System.out.println("");

                    for (int i = 0; i < 15; i++) {
                        System.out.println((i + 1) + "ª Dezena: ");
                        aux = scn.nextInt();
                        booAux = jogoLoto.validacao(aux);
                        if (booAux) {
                            jogoLoto.setDezenas(aux);
                        } else {
                            System.out.println("ERRO: SUAS DEZENAS DEVEM ESTAR ENTRE 1 E 25");
                            System.out.println("INFORME NOVAMENTE A DEZENA");
                            i--;
                        }

                    }

                    System.out.println(jogoLoto.getDezenas());

                }
                int acerto = 0;
                for (int i = 0; i < fList.size(); i++) {
                    acerto = jogoLoto.confere(jogoLoto.getDezenas(), fList.get(i).getDezenas());

                    switch (acerto) {
                        case 11:
                            System.out.println("No concurso de número: " + fList.get(i).getConcurso());
                            System.out.println("Data: " + fList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e GANHOU!!");
                            System.out.println("");
                            break;
                        case 12:
                            System.out.println("No concurso de número: " + fList.get(i).getConcurso());
                            System.out.println("Data: " + fList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e GANHOU!!");
                            System.out.println("");
                            break;
                        case 13:
                            System.out.println("No concurso de número: " + fList.get(i).getConcurso());
                            System.out.println("Data: " + fList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e GANHOU!!");
                            System.out.println("");
                            break;
                        case 14:
                            System.out.println("No concurso de número: " + fList.get(i).getConcurso());
                            System.out.println("Data: " + fList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e GANHOU!!");
                            System.out.println("");
                            break;
                        case 15:
                            System.out.println("No concurso de número: " + fList.get(i).getConcurso());
                            System.out.println("Data: " + fList.get(i).getData());
                            System.out.println("Você acertou " + acerto + " dezenas, e GANHOU!!");
                            System.out.println("");
                            break;

                    }

                }
            }

            if (opc == 9) {
                System.out.println("*****JOGO FINALIZADO*****");
            }

        } while (opc != 9);

    }

}
