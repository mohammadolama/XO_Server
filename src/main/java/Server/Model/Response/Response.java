package Server.Model.Response;

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
        @JsonSubTypes.Type(value = LoginSignupResponse.class, name = "login"),
        @JsonSubTypes.Type(value = ReplayResponse.class, name = "replay"),
        @JsonSubTypes.Type(value = InfoResponse.class, name = "info"),
        @JsonSubTypes.Type(value = ScoreBoardResponse.class, name = "score"),
        @JsonSubTypes.Type(value = GameResponse.class, name = "game"),
        @JsonSubTypes.Type(value = PlayResponse.class, name = "play"),
})
public interface Response {
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object);
}