package Server.Model.Request;

import Server.Controller.ClientHandler;
import Server.Controller.GameManager;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Login_SignUp_Request.class, name = "login"),
        @JsonSubTypes.Type(value = ReplayRequest.class, name = "replay"),
        @JsonSubTypes.Type(value = InfoRequest.class, name = "info"),
        @JsonSubTypes.Type(value = ScoreBoardRequest.class, name = "score"),
        @JsonSubTypes.Type(value = PlayRequest.class, name = "play"),
        @JsonSubTypes.Type(value = OcupySpotRequest.class, name = "ocupy"),
})public interface Request {
    void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, GameManager managers);
}