package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;



/**
 *
 * @author Marih Bianchini
 */
public class Servidor {

    public static void main(String[] args) {
        ArrayList<PrintStream> Users = new ArrayList<>();
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
            while(true){
                socket = server.accept();
                Users.add(new PrintStream(socket.getOutputStream()));
                Msg mensagem = new Msg(socket,Users);
                              }
                     }
        catch(IOException e){
            e.printStackTrace();
                               }
                   }
    }
    
        