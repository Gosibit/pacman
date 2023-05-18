import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoardModel extends AbstractTableModel {
    private Pacman pacman;
    private JLabel board[][];

    private int rows;
    private int columns;

    public GameBoardModel(int rows, int columns,Pacman pacman) {
        this.pacman = pacman;
        this.board = new JLabel[rows][columns];
        this.rows = rows;
        this.columns = columns;

        for(Collision c : Collision.getCollisionMap().values()) {
            board[c.getModel().getPosition().y][c.getModel().getPosition().x] = c.getView();
        }


    }


    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return columns;
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex];
    }

        public void refreshBoard(){
        int x = pacman.getModel().getPosition().x;
        int y = pacman.getModel().getPosition().y;
        int previousX = pacman.getModel().getPreviousPosition().x;
        int previousY = pacman.getModel().getPreviousPosition().y;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Point p = new Point(j,i);
                if(!Collision.isCollision(p) && !Opponent.isOpponent(p)){
                    board[i][j] = null;
                }
            }
        }
        for(PowerUp pu: PowerUp.getPowerUpMap().values()){
            int puX = pu.getModel().getPosition().x;
            int puY = pu.getModel().getPosition().y;

            board[puY][puX] = pu.getView();
        }
        for(ScorePoint sc: ScorePoint.getScorePoints().values()){
            board[sc.getModel().getPosition().y][sc.getModel().getPosition().x] = sc.getView();
        }

        for(Opponent o: Opponent.getOpponents().values()){
            if(o!=null)board[o.getModel().getPosition().y][o.getModel().getPosition().x] = o.getView();

        }


        board[previousY][previousX] = null;
            System.out.println(x + " " + y);
            System.out.println(pacman.getModel().getBasePosition());
        board[y][x] = pacman.getView();
    }
}
