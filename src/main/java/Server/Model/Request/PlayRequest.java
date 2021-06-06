package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import Server.Model.Triplet;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("play")
public class PlayRequest implements Request{

    public PlayRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers) {
            clientHandler.server.putInWaitList(new Triplet(clientHandler.player, inputStream, outputStream, clientHandler));

    }
}
