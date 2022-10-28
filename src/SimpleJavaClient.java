import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class SimpleJavaClient {

    public static void main(String[] args) 	{
        try {
            Socket s = new Socket("127.0.0.1", 9999);
            InputStream i = s.getInputStream();
            OutputStream o = s.getOutputStream();
            String str;
            String verificar;
            do {
                byte[] line = new byte[100];
                System.out.println("\nDigite uma expressão com no máximo 2 operadores ou digite 'sair' para sair da calculadora: ");
                System.in.read(line);
                verificar = new String(line);

                if(verificar.indexOf("+") >= 0 || verificar.indexOf("-") >= 0 || verificar.indexOf("*") >= 0 || verificar.indexOf("/") >= 0) {
                    o.write(line);
                    Arrays.fill(line, (byte) 0);
                    i.read(line);
                    str = new String(line);
                    if ( str.trim().equals("sair") ){
                        System.out.println("Saindo da calculadora!");
                    }else {
                        System.out.println(str.trim());
                    }
                }else {
                    System.out.println("ERRO!!! Algarismo incorreto.");
                }
            } while ( !verificar.trim().equals("sair") );
            s.close();
        }
        catch (Exception err) {
            System.err.println(err);
        }
    }

}
