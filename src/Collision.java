import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class Collision {

    private CollisionModel model;

    private CollisionView view;

    private static Map<Point,Collision> collisionMap = new HashMap<>();

    Collision(Point position) {
        model = new CollisionModel(position);
        view = new CollisionView();

        collisionMap.put(model.getPosition(),this);
    }

    public static boolean isCollision(Point p){
        if(collisionMap.containsKey(p))return true;
        else return false;
    }

    public CollisionModel getModel() {
        return model;
    }

    public CollisionView getView() {
        return view;
    }

    public static Map<Point, Collision> getCollisionMap() {
        return collisionMap;
    }


    public static void bulkCollisionCreate(Point[] points){
        for(Point p : points){
            new Collision(p);
        }
    }

    public static boolean isCollisionAround(Point p){

        Point[] pointsToCheck = new Point[]{p,new Point(p.x-1,p.y-1), new Point(p.x,p.y-1),new Point(p.x+1,p.y-1), new Point(p.x+1,p.y), new Point (p.x+1,p.y+1), new Point (p.x,p.y+1), new Point (p.x-1,p.y+1), new Point(p.x-1,p.y) };

        for(Point pointToCheck: pointsToCheck){
            if(Collision.isCollision(pointToCheck))return true;
        }
        return false;
    }

}