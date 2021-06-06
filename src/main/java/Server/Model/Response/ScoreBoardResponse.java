package Server.Model.Response;

import Server.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("score")
public class ScoreBoardResponse implements Response{
    private ArrayList<PlayerModel> list;
    private int index;

    public ScoreBoardResponse(ArrayList<PlayerModel> list, int index) {
        this.list = list;
        this.index = index;
    }

    public ScoreBoardResponse() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<PlayerModel> getList() {
        return list;
    }

    public void setList(ArrayList<PlayerModel> list) {
        this.list = list;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

    }
}
