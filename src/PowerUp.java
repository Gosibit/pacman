import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PowerUp {
    PowerUpView view;
    PowerUpModel model;
    private static Map<Point,PowerUp> PowerUpMap = new HashMap<Point,PowerUp>();

    PowerUp(Point position){
        model = new PowerUpModel(position);
        view = new PowerUpView();

        PowerUpMap.put(position,this);
    }

    public PowerUpView getView() {
        return view;
    }

    public static boolean isPowerUp(Point p){
        return PowerUpMap.containsKey(p);
    }

    public PowerUpModel getModel() {
        return model;
    }

    public static Map<Point, PowerUp> getPowerUpMap() {
        return PowerUpMap;
    }
}
