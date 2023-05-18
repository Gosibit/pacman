import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Opponent {
    private OpponentModel model;
    private OpponentView view;

    private static Map<Point,Opponent> opponents = new HashMap<>();

    Opponent(Point position, Color color){
        model = new OpponentModel(position);
        view = new OpponentView(color);

        opponents.put(position,this);
    }

    public static boolean isOpponent(Point position){
        return opponents.containsKey(position);
    }

    public static void setOpponents(Map<Point, Opponent> opponents) {
        Opponent.opponents = opponents;
    }

    public OpponentModel getModel() {
        return model;
    }

    public OpponentView getView() {
        return view;
    }

    public static Map<Point, Opponent> getOpponents() {
        return opponents;
    }
}
