import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel{

    private GameBoardView view;
    private int rows,columns;
    private GameBoardModel model;
    private GameOverView gameOverView;

    private MenuView menuView;
    Pacman pacman;

    private ScoreCounterView scoreCounterView;

    public GameBoard(int rows, int columns, Pacman pacman, MenuView menuView) {

        this.pacman = pacman;
        this.rows = rows;
        this.columns = columns;
        this.menuView = menuView;

        Maze.setPacman(pacman);
        Maze.generate(rows,columns);

        new Opponent(new Point(1,1),Color.PINK);
        new Opponent(new Point(columns-2,1),Color.RED);
        new Opponent(new Point(1,rows-2),Color.cyan);
        new Opponent(new Point(columns-2,rows-2),Color.ORANGE);

        reset();

        scoreCounterView = new ScoreCounterView();
        add(scoreCounterView);

        model = new GameBoardModel(rows, columns, pacman);
        gameOverView = new GameOverView(pacman,menuView);
        view = new GameBoardView(model);

        add(view);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustCellSizes(view,pacman);
            }
        });

        PacmanModel pacmanModel = pacman.getModel();

        view.addFocusListener(  new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                requestFocusInWindow();
            }
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {


                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                    forceQuit();
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        pacmanModel.setDirection(PacmanModel.Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        pacmanModel.setDirection(PacmanModel.Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        pacmanModel.setDirection(PacmanModel.Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        pacmanModel.setDirection(PacmanModel.Direction.RIGHT);
                        break;
                }
            }
        });


        adjustCellSizes(view,pacman);
        setVisible(true);
    }

    private void adjustCellSizes(GameBoardView view, Pacman pacman) {
        int rowCount = view.getRowCount();
        int columnCount = view.getColumnCount();

        Dimension tableSize = view.getParent().getSize();
        int cellSize = Math.min(tableSize.width / columnCount, tableSize.height / rowCount);

        if (cellSize < 1) {
            cellSize = 1;
        }

        view.setRowHeight(cellSize);

        for (int i = 0; i < columnCount; i++) {
            TableColumn tableColumn = view.getColumnModel().getColumn(i);
            tableColumn.setPreferredWidth(cellSize);
        }

        Dimension newTableSize = new Dimension(cellSize * columnCount, cellSize * rowCount);
        view.setPreferredSize(newTableSize);
    }
    public void gameOver(){
           killThreads();
           showGameOverView();
    }

    public void killThreads(){
        for(Thread t: ThreadsInitiator.getThreads()){
            t.interrupt();
        }
        Opponent.getOpponents().clear();
        Collision.getCollisionMap().clear();
    }

    public void reset(){
        Map<Point,Opponent> newOpponents = new HashMap<>();
        for(Opponent o: Opponent.getOpponents().values()){
            o.getModel().setPosition(o.getModel().getBasePosition());
            o.getModel().setEatable(false);
            o.getView().setColor(o.getView().getBaseColor());
            newOpponents.put(o.getModel().getBasePosition(), o);
        }

        Opponent.setOpponents(newOpponents);
        PowerUp.getPowerUpMap().clear();
        ScorePoint.getScorePoints().clear();

        pacman.getModel().setPosition(pacman.getModel().getBasePosition());

        new PowerUp(new Point(1,1));
        new PowerUp(new Point(columns-2,1));
        new PowerUp(new Point(1,rows-2));
        new PowerUp(new Point(columns-2,rows-2));

        for(int x = 0; x<columns; x++){
            for(int y = 0; y<rows; y++){
                Point actualPoint = new Point(x,y);
                if(!PowerUp.isPowerUp(actualPoint) && !Collision.isCollision(actualPoint) && new Random().nextInt(4)==3){
                  new ScorePoint(actualPoint);
                }
            }
        }

    }
    public void forceQuit(){
        killThreads();
        view.setVisible(false);
        scoreCounterView.setVisible(false);
        menuView.setVisible(true);
    }
    public void showGameOverView() {
        view.setVisible(false);
        scoreCounterView.setVisible(false);
        add(gameOverView);

    }
    public GameBoardView getView() {
        return view;
    }

    public GameBoardModel getModel() {
        return model;
    }

    public JLabel getScoreCounterView() {
        return scoreCounterView;
    }

    public GameOverView getGameOverView() {
        return gameOverView;
    }
}