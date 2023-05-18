import java.awt.*;
import java.util.Collection;
import java.util.Random;

public class PacmanModel {

    private Point previousPosition;
    private Point position;
    private Point basePosition;
    private int timeInGame = 0;

    private int lives = 1;

    private int hitsInTheCollision = 0;
    int poweredUpSecondsLeft = 0;


    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public void takeLife() {
      lives--;
      position = new Point(basePosition);
    }

    public int getTimeInGame() {
        return timeInGame;
    }

    public void setTimeInGame(int timeInGame) {
        this.timeInGame = timeInGame;
    }

    public int getPoweredUpSecondsLeft() {
        return poweredUpSecondsLeft;
    }

    public void setPoweredUpSecondsLeft(int poweredUpSecondsLeft) {
        this.poweredUpSecondsLeft = poweredUpSecondsLeft;
    }

    public Point getBasePosition() {
        return basePosition;
    }

    public void setBasePosition(Point basePosition) {
        this.basePosition = basePosition;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private int points;

    private Direction direction = Direction.RIGHT;

    public PacmanModel(int boardRows, int boardColumns) {
        position = new Point(boardColumns/2,boardRows/2);
        previousPosition = position;
        basePosition = position;

    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){
        previousPosition = position;
       Point newPosition = switch(direction){
            case UP:yield Move.moveUp(position);
            case DOWN:yield Move.moveDown(position);
            case LEFT:yield Move.moveLeft(position);
            case RIGHT:yield Move.moveRight(position);
        };


        if(!Collision.isCollision(newPosition)){
            position = newPosition;
            if(ScorePoint.isScorePoint(newPosition)){
                ScorePoint.getScorePoints().remove(newPosition);
                points+=10;
            }
            if(PowerUp.isPowerUp(position)){
                PowerUp.getPowerUpMap().remove(position);
                poweredUpSecondsLeft = 8;
                for(Opponent o:Opponent.getOpponents().values()){
                    o.getModel().setEatable(true);
                    o.getView().setColor(Color.blue);
                }

            }
            hitsInTheCollision = 0;
        }else{
            hitsInTheCollision++;
            if(hitsInTheCollision==3){
                setRandomDirection();
                hitsInTheCollision=0;
            }
        }

    }

    public void setRandomDirection(){
        direction = PacmanModel.Direction.values()[new Random().nextInt(PacmanModel.Direction.values().length)];
    }
    public Point getPreviousPosition() {
        return previousPosition;
    }

    public Point getPosition() {
        return position;
    }
}