import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ScorePoint {
    private ScorePointView view;
    private ScorePointModel model;
    private static Map<Point,ScorePoint> ScorePoints = new HashMap<Point,ScorePoint>();

    ScorePoint(Point p){
         view = new ScorePointView();
         model = new ScorePointModel(p);

         ScorePoints.put(p,this);
    }

    public static boolean isScorePoint(Point p){
        return ScorePoints.containsKey(p);
    }

    public ScorePointView getView() {
        return view;
    }

    public ScorePointModel getModel() {
        return model;
    }

    public static Map<Point, ScorePoint> getScorePoints() {
        return ScorePoints;
    }
}
