package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import Server.Model.PlayerModel;
import Server.Model.Response.ScoreBoardResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("score")
public class ScoreBoardRequest  implements Request{
    private int index;   // 1 = scorepanel  2=myframe

    public ScoreBoardRequest(int index) {
        this.index = index;
    }

    public ScoreBoardRequest() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {
        try {
            ArrayList<PlayerModel> playerModels = clientHandler.server.PlayerStatus();
            outputStream.println(objectMapper.writeValueAsString(new ScoreBoardResponse(playerModels, index)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
