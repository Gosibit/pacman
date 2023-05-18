import java.awt.*;

public class Move {

    public static Point moveUp(Point position) {
        return new Point(position.x,position.y-1);
    }

    public static Point moveDown(Point position) {
        return new Point(position.x, position.y+1);
    }

    public static Point moveLeft(Point position) {
        return new Point(position.x-1, position.y);
    }

    public static Point moveRight(Point position) {
        return new Point(position.x+1,position.y);
    }

}
