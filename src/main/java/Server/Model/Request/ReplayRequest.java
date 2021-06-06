package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import Server.Model.Response.ReplayResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("replay")
public class ReplayRequest implements Request{
    public ReplayRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {

        try {
            ArrayList<String[][]> replayes =clientHandler.player.replayes;
            System.out.println(replayes.size());
            ReplayResponse replayResponse = new ReplayResponse(replayes);
            String s = objectMapper.writeValueAsString(replayResponse);
            outputStream.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
