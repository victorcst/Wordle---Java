import java.io.*;
import java.util.*;


public class Main {
    public static Scanner ler = new Scanner(System.in);
    public static final String COR_RESET = "\u001B[0m";
    public static final String COR_AMARELO = "\u001B[33m";
    public static final String COR_VERDE = "\u001B[32m";
    public static void main(String[] args) throws IOException {
        long tempoInicio = System.currentTimeMillis();
        int tentativas = 0;
        System.out.println("Termo-ATP: Digite uma palavra de 5 letras:");
        String palavraFinal = sortear();
        char[] resposta = new char[5];
        for (int i = 0; i < 5; i++ ) resposta[i] = palavraFinal.charAt(i);
        char[] recebe = new char[5];        
        boolean feito = false;
        
        while (!feito){
            tentativas++;
            String tentativaUsuario = ler.nextLine().toLowerCase();
            while (tentativaUsuario.length() < 5) {
                tentativaUsuario = ler.nextLine().toLowerCase();
            }
            for (int i = 0; i < 5; i++ ) {
                resposta[i] = palavraFinal.charAt(i);
                recebe[i] = tentativaUsuario.charAt(i);
            }
            if (PrintarPalavraComCor(recebe, resposta)) feito = true;
        }

        System.out.println("Você encontrou a resposta em " + ((System.currentTimeMillis() - tempoInicio) / 1000) + " segundos e com " + tentativas + " tentativas.");
    }

    public static boolean PrintarPalavraComCor(char[] palavraUsuario, char[] palavraCerta) {
        boolean correto = true;
        char[] respostaTemp = palavraCerta;
        int[] corParaLetra = new int[5];

        for (int i = 0; i < 5; i++) { // Verifica por qualquer letra correta, posicao+letra(verde)
            if (palavraUsuario[i] == respostaTemp[i]) {
                respostaTemp[i] = '-';
                corParaLetra[i] = 2;
            } else correto = false;
        }

        for (int j = 0; j < 5; j++) { // Verifica por qualquer letra correta(amarelo)
            for (int k = 0; k < 5; k++){
                if (palavraUsuario[j] == respostaTemp[k] && corParaLetra[j] != 2) {
                    //Verifica se essa letra ainda não esta verde e se corresponde a alguma outra letra
                    corParaLetra[j] = 1;
                    respostaTemp[k] = '-';
                }
            }
        }

        for (int m = 0; m < 5; m++) {
            if (corParaLetra[m] == 0) System.out.print(palavraUsuario[m]);
            if (corParaLetra[m] == 1) System.out.print(COR_AMARELO + palavraUsuario[m] + COR_RESET);
            if (corParaLetra[m] == 2) System.out.print(COR_VERDE + palavraUsuario[m] + COR_RESET);
        }
        System.out.println("");
        return correto;
    }

    public static String sortear()throws IOException{
       Dicionario dic = new Dicionario(2326, "palavrasJogo.txt");
       Random rd = new Random();
       String sorteio;
       sorteio = dic.sortearPalavra(rd.nextInt(1000));
    return sorteio;
    }
}