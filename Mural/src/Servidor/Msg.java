package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;


/**
 *
 * @author Marih Bianchini
 */
public class Msg {
    
      private Socket s;
    private ArrayList<PrintStream> Users;

    public Msg(Socket s, ArrayList<PrintStream> Users) {
        this.s = s;
        this.Users = Users;

        Thread();
    }

    private void Thread() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String mensagem = "";
                try {
                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {
                        enviarMensagem(mensagem);
                        System.out.println(s);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        t.start();

    }

    private void enviarMensagem(String mensagem) {

        for (int a = 0; a < Users.size(); a++) {

            Users.get(a).println(mensagem);
            Users.get(a).flush();

        }

    }
}
