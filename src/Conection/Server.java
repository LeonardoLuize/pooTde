package Conection;

import Pong.Canvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket server;
    private Socket client;
    private PrintWriter out;
    private BufferedReader input;
    private Boolean isGameRunning;

    public Server(){
        isGameRunning = true;
    }

    public void createServer(int port){

        try{
            server = new ServerSocket(port);
            System.out.printf("Servidor iniciado na porta %d\n", port);

            do{
                client = server.accept();

                out = new PrintWriter(client.getOutputStream(), true);
                input = new BufferedReader(new InputStreamReader(client.getInputStream()));

                receiveData(input.readLine());

                Canvas pongScreen = new Canvas();
                pongScreen.initGame();

            }
            while(isGameRunning);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void stopGame(){
        isGameRunning = false;
    }

    public void receiveData(String data){
        System.out.println(data);

        if ("hello server".equals(data)) {
            out.println("hello client");
        }
        else {
            out.println("unrecognised greeting");
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.print("Digite a porta para rodar o servidor: ");
        int port = Integer.parseInt(input.nextLine());

        Server pongServer = new Server();
        pongServer.createServer(port);
    }

}