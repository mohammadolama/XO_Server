package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import Server.Model.Response.InfoResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("info")
public class InfoRequest implements Request {

    public InfoRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {
        try {
            InfoResponse infoResponse = new InfoResponse(clientHandler.player);
            String s = objectMapper.writeValueAsString(infoResponse);
            outputStream.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
