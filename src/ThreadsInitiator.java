import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadsInitiator {
    private GameBoard gameBoard;
    private Pacman pacman;

    private static ArrayList<Thread> threads = new ArrayList<Thread>();
    public ThreadsInitiator(GameBoard gameBoard, Pacman pacman) {
        this.gameBoard = gameBoard;
        this.pacman = pacman;
    }

    public void startThreads() {
        Thread movePacmanThread = new Thread(() -> {
            try {
            while (true) {

                    Thread.sleep(180);

                    synchronized (this) {
                        pacman.getModel().move();
                        if(pacman.getModel().getLives()<0){
                            gameBoard.gameOver();
                        }
                        pacman.getView().setDirection(pacman.getModel().getDirection());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        movePacmanThread.start();
        threads.add(movePacmanThread);

        for(Opponent o: Opponent.getOpponents().values()){
            Thread moveOpponentsThread = new Thread(()->{
                try{

                    while(true){
                        Thread.sleep(180);
                        synchronized (this) {
                            o.getModel().move();
                        }
                    }
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
            moveOpponentsThread.start();
            threads.add(moveOpponentsThread);
        }


        Thread endOfLevelListener = new Thread(()->{
            try{
                while(true) {
                    Thread.sleep(1000);

                    if (ScorePoint.getScorePoints().size()==0 && PowerUp.getPowerUpMap().size()==0) {
                        gameBoard.reset();
                    }
                }
            }catch(InterruptedException err){
                err.printStackTrace();
            }
        });

        endOfLevelListener.start();
        threads.add(endOfLevelListener);

        Thread refreshGameBoardThread = new Thread(() -> {
            try {
            while (true) {

                Thread.sleep(33);
                    synchronized (this) {
                        gameBoard.getModel().refreshBoard();
                        gameBoard.getView().repaint();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        refreshGameBoardThread.start();
        threads.add(refreshGameBoardThread);


        Thread gameTimerThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);

                    synchronized (this) {
                        pacman.getModel().setTimeInGame(pacman.getModel().getTimeInGame()+1);
                        pacman.getModel().setPoweredUpSecondsLeft(pacman.getModel().getPoweredUpSecondsLeft()-1);
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threads.add(gameTimerThread);
        gameTimerThread.start();

        Thread trackScorePoints = new Thread(() -> {
            try {
                while (true) {

                    Thread.sleep(100);
                    synchronized (this) {
                        gameBoard.getScoreCounterView().setText("Score: " + pacman.getModel().getPoints() + "\n Lives: " + pacman.getModel().getLives() + "\n Time: " + pacman.getModel().getTimeInGame());
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads.add(trackScorePoints);
        trackScorePoints.start();

        Thread trackDeathsThread = new Thread(() -> {
            try {
                while (true) {

                    Thread.sleep(10);
                    synchronized (this) {
                        Point position = pacman.getModel().getPosition();
                        if(Opponent.isOpponent(position)) {
                            System.out.println("opp");
                            if(pacman.getModel().getPoweredUpSecondsLeft()<1 || !Opponent.getOpponents().get(position).getModel().isEatable())pacman.getModel().takeLife();
                            else {
                                Opponent opp = Opponent.getOpponents().remove(position);
                                opp.getModel().setPosition(opp.getModel().getBasePosition());
                                opp.getView().setColor(opp.getView().getBaseColor());
                                opp.getModel().setEatable(false);
                                pacman.getModel().setPoints(pacman.getModel().getPoints()+100);
                               Opponent.getOpponents().put(opp.getModel().getBasePosition(), opp);
                            }

                        }
                        if(pacman.getModel().getPoweredUpSecondsLeft()<1){
                            for(Opponent o: Opponent.getOpponents().values()){
                               if(o!=null)o.getView().setColor(o.getView().getBaseColor());
                                if(o!=null)o.getModel().setEatable(false);
                            }
                        }

                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads.add(trackDeathsThread);
        trackDeathsThread.start();

    }


    public static ArrayList<Thread> getThreads() {
        return threads;
    }
}
