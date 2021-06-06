package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("ocupy")
public class OcupySpotRequest implements Request{
    private String index;

    public OcupySpotRequest(String index) {
        this.index = index;
    }

    public OcupySpotRequest() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {
            int i = Integer.parseInt(index) / 10;
            int j = Integer.parseInt(index) % 10;
            managers.OccupySpot(clientHandler.player, i, j);
    }
}
