import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleJavaServer {

    public static void main(String[] args) 	{
        try {
            ServerSocket s = new ServerSocket(9999);
            String str;
            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    str = new String(line);
                    str = Execucao(str);
                    o.write(str.getBytes());
                    str = new String(line);
                } while ( !str.trim().equals("sair") );
                c.close();
            }
        }
        catch (Exception err){
            System.err.println(err);
        }
    }
    public static String Execucao(String str) {
        Float n1, n2, total = null;
        int controle = 0;

        str = str.replaceAll("\\s+", "");

        if (str.indexOf("+") >= 0) {
            controle = controle + 1;
            String[] str2 = str.split("\\+");
            n1 = Float.parseFloat(str2[0]);
            n2 = Float.parseFloat(str2[1]);
            total = n1 + n2;
        }

        if (str.indexOf("-") >= 0) {
            controle = controle + 1;
            String[] str2 = str.split("-");
            n1 = Float.parseFloat(str2[0]);
            n2 = Float.parseFloat(str2[1]);
            total = n1 - n2;
        }

        if (str.indexOf("*") >= 0) {
            controle = controle + 1;
            String[] str2 = str.split("\\*");
            n1 = Float.parseFloat(str2[0]);
            n2 = Float.parseFloat(str2[1]);
            total = n1 * n2;
        }

        if (str.indexOf("/") >= 0) {
            controle = controle + 1;
            String[] str2 = str.split("/");
            n1 = Float.parseFloat(str2[0]);
            n2 = Float.parseFloat(str2[1]);
            total = n1 / n2;
        }

        str = String.valueOf(total);

        if (controle == 0) {
            str = "sair";
        }

        return str;
    }
}
