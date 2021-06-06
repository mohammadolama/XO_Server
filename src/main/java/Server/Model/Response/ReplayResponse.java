package Server.Model.Response;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("replay")
public class ReplayResponse implements Response{
    private ArrayList<String[][]> replays;

    public ReplayResponse(ArrayList<String[][]> replays) {
        this.replays = replays;
    }

    public ReplayResponse() {
    }

    public ArrayList<String[][]> getReplays() {
        return replays;
    }

    public void setReplays(ArrayList<String[][]> replays) {
        this.replays = replays;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

    }
}
