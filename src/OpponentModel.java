import java.awt.*;
import java.util.Map;
import java.util.Random;

public class OpponentModel {
    private Point position;
    private Point previousPosition;
    private Point basePosition;
    private int movesInSameDirection;
    private boolean isEatable = false;


    private int maxMovesInSameDirection;
    private PacmanModel.Direction direction;

    OpponentModel(Point position){

        this.position = position;
        this.previousPosition = position;
        this.movesInSameDirection = 0;
        this.direction = PacmanModel.Direction.RIGHT;
        basePosition = position;
    }


    public void move(){
        previousPosition = position;
        Point newPosition = switch(direction){
            case UP:yield Move.moveUp(position);
            case DOWN:yield Move.moveDown(position);
            case LEFT:yield Move.moveLeft(position);
            case RIGHT:yield Move.moveRight(position);
        };

        if(!Collision.isCollision(newPosition) && !Opponent.isOpponent(newPosition)){

            Map<Point,Opponent> opponents = Opponent.getOpponents();
            Opponent o = opponents.get(position);

            opponents.put(newPosition,o);
            opponents.remove(position);

            position = newPosition;

        }else{
            setRandomDirection();
        }

        movesInSameDirection++;

        if(movesInSameDirection>maxMovesInSameDirection){
            setRandomDirection();
        }
    }

    public void setRandomDirection(){

        direction = PacmanModel.Direction.values()[new Random().nextInt(PacmanModel.Direction.values().length)];
        maxMovesInSameDirection = new Random().nextInt(5,10);
        movesInSameDirection = 0;
    }


    public Point getPosition() {
        return position;
    }

    public Point getPreviousPosition() {
        return previousPosition;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getBasePosition() {
        return basePosition;
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }
}
