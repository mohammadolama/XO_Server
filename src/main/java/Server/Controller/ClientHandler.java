package Server.Controller;

import Server.Model.Message;
import Server.Model.Player;
import Server.Model.Request.Request;
import Server.Model.Response.GameResponse;
import Server.Model.Response.PlayResponse;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread {

    public final Server server;
    public GameManager gameManager;
    public final Socket socket;
    public Player player;
    public PrintWriter output;
    public Scanner input;
    public final LoginLogic loginLogic = LoginLogic.getInstance();
    public final ObjectMapper objectMapper;


    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            while (!isInterrupted()) {
                excuteReq(input.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void excuteReq(String string) {
        new Thread(() -> {
            try {
                Request request = objectMapper.readValue(string, Request.class);
                request.excute(input, output, this, objectMapper, gameManager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


//    @Override
//    public void run() {
//        try {
//            input = new Scanner(socket.getInputStream());
//            output = new PrintWriter(socket.getOutputStream(), true);
//            while (socket.isConnected()) {
//                Message message = getMessageOf(input.nextLine());
//                switch (message.getMessage()) {
//                    case "scoreboard":
//                        ScoreBoard(message);
//                        break;
//                    case "login":
//                        Login(message);
//                        break;
//                    case "account":
//                        SignUp(message);
//                        break;
//                    case "info":
//                        Info(message);
//                        break;
//                    case "play":
//                        putInList(message);
//                        break;
//                    case "index":
//                        occupyIndex(message);
//                        break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            server.setOffline(player);
//        }
//    }








    public void notifyClient(Message message) {
        try {
            GameResponse g = new GameResponse(message);
            String res = objectMapper.writeValueAsString(g);
            output.println(res);
            if (message.getValue()!=null){
                player.replayes.add(message.getValue());
                System.out.println(player.replayes.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
//
    public void notification(String friendly, String opponent, String opponentName) {
        try {
            PlayResponse p =new PlayResponse(friendly , opponent , opponentName);
            String s = objectMapper.writeValueAsString(p);
            output.println(s);
            player.replayes=new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message getMessageOf(String string) {
        Message message = null;
        try {
            message = objectMapper.readValue(string, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}
