package Conection;

import Pong.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Boolean isGameRunning;
    private Socket server;
    private OutputStream out;
    private BufferedReader input;
    private PrintWriter pw;

    public Client(){
        isGameRunning = true;
    }

    public void connectPlayer(String url, int port) throws InterruptedException {

        try{
            server = new Socket(url, port);

            Canvas pongScreen = new Canvas();
            Board board = pongScreen.getBoard();
            Player p1 = board.getPlayer1();
            Enemy p2 = board.getPlayer2();
            Bola ball = board.getBola();

            pongScreen.initGame();
            p1.loadPlayer();
            ball.loadImage();
            p2.loadPlayer();

            do {
                out = server.getOutputStream();
                input = new BufferedReader(new InputStreamReader(server.getInputStream()));

                pw = new PrintWriter(out, true);

                String response = input.readLine();
               //System.out.println(response);

                if(response != null ){
                    String[] data = response.split(";");

                    if(data[0].equals("player")){
                        board.setyP2(Integer.parseInt(data[1]));
                    }else if(data[0].equals("bola")){
                        ball.setX(Integer.parseInt(data[1]));
                        ball.setY(Integer.parseInt(data[2]));
                    }
                }

            }while(isGameRunning);

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("Digite a url que deseja conectar: ");
        String url = input.nextLine();

        System.out.print("Digite a porta que deseja conectar: ");
        int port = Integer.parseInt(input.nextLine());

        new Client().connectPlayer(url, port);
    }
}
