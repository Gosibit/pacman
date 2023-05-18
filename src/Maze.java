import java.awt.*;
import java.util.Random;

class Maze {

    private static Pacman pacman;
   public static void generate(int rows, int columns){
               for(int i = 0; i<rows;i++){
            new Collision(new Point(0,i));
        }

        for(int i = 0; i<rows;i++){
            new Collision(new Point(columns-1,i));
        }

        for(int i = 0; i < columns; i++) {
            new Collision(new Point(i,0));
        }

        for(int i = 0; i < columns; i++) {
            new Collision(new Point(i,rows-1));
        }

    for (int y = 2; y< rows; y++)
       for(int x = 0;x < columns; x++){
           Point p = new Point(x,y);
           int randShape = new Random().nextInt(4);
    if(pacman.getModel().getBasePosition()!=p) {
        if (randShape == 0) rectangle(p);
        else if (randShape == 1) firstShape(p);
        else if (randShape == 2) secondShape(p);
        else if (randShape == 3) thirdShape(p);
//           else if(randShape==4)fourthShape(p); i don't like this shape
    }
       }

    }


    //12
    //43
    public static void rectangle(Point startPoint){

       Point secondPoint = Move.moveRight(startPoint);
       Point thirdPoint = new Point(startPoint.x+1, startPoint.y+1);
       Point fourthPoint = Move.moveDown(startPoint);


        Point[] points = new Point[]{startPoint, secondPoint, thirdPoint,fourthPoint};

        if(doesFit(points)){
            Collision.bulkCollisionCreate(points);
        }

    }

    //12
    //-3
    public static void firstShape(Point startPoint) {

        Point secondPoint = Move.moveRight(startPoint);

        Point thirdPoint = new Point(startPoint.x+1, startPoint.y+1);

        Point[] points = new Point[]{startPoint, secondPoint, thirdPoint};

        if(doesFit(points)){
            Collision.bulkCollisionCreate(points);
        }
    }

    //-1
    //32
    public static void secondShape(Point startPoint) {

        startPoint = Move.moveRight(startPoint);
        Point secondPoint = Move.moveDown(startPoint);
        Point thirdPoint = Move.moveRight(secondPoint);

        Point[] points = new Point[]{startPoint, secondPoint, thirdPoint};

        if(doesFit(points)){
            Collision.bulkCollisionCreate(points);
        }

    }
    //123
    public static void thirdShape(Point startPoint) {

        Point secondPoint = Move.moveRight(startPoint);
        Point thirdPoint = Move.moveRight(secondPoint);

        Point[] points = new Point[]{startPoint, secondPoint, thirdPoint};

        if(doesFit(points)){
            Collision.bulkCollisionCreate(points);
        }

    }


    //12
    //43
    //65
    public static void fourthShape(Point startPoint) {

        Point p2 = Move.moveRight(startPoint);
        Point p3 = Move.moveDown(p2);
        Point p4 = Move.moveLeft(p3);
        Point p5 = Move.moveDown(p3);
        Point p6 = Move.moveDown(p4);


        Point[] points = new Point[]{startPoint, p2,p3,p4,p5,p6};

        if(doesFit(points)){
            Collision.bulkCollisionCreate(points);
        }

    }

    public static boolean doesFit(Point[] points){

       Point pacmanBasePosition = pacman.getModel().getBasePosition();
       for(Point p:points){
           if(pacmanBasePosition.x==p.x && pacmanBasePosition.y == p.y)return false;
           if(Collision.isCollisionAround(p))return false;
       }
       return true;
    }

    public static void setPacman(Pacman pacman) {
        Maze.pacman = pacman;
    }


}