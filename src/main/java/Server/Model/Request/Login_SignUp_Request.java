package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import Server.Controller.JsonReaders;
import Server.Model.Player;
import Server.Model.Response.LoginSignupResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("login")
public class Login_SignUp_Request implements Request{
    private String username;
    private String password;
    private String type;

    public Login_SignUp_Request(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Login_SignUp_Request() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {
        try {
            if (type.equalsIgnoreCase("signup")) {
                String res = clientHandler.loginLogic.SignUp(username, password);
                outputStream.println(objectMapper.writeValueAsString(new LoginSignupResponse("signup", res)));
            } else if (type.equalsIgnoreCase("login")) {

                String res = clientHandler.loginLogic.Login(username, password);
                if (res.equals("ok")) {
                    Player player = JsonReaders.PlayerJsonReader(username);
                    player.setOnline(true);
                    clientHandler.server.addOnlinePlayer(player);
                    clientHandler.player = player;
                    outputStream.println(objectMapper.writeValueAsString(new LoginSignupResponse("login" , res)));
                } else {
                    outputStream.println(objectMapper.writeValueAsString(new LoginSignupResponse("login" , res)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
