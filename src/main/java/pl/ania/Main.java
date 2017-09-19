package pl.ania;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try (
            ServerSocket serverSocket = new ServerSocket(222);
            Socket accept = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(accept.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(accept.getInputStream()))

        ){
            System.out.println("polaczylem sie z " + accept.getRemoteSocketAddress());
            Menu menu = new Menu(out, in);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

