import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuView extends JPanel {

    HighScoresView hsv;
    GameOverView gov;
    MenuView(MyFrame frame){

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        JButton newGameButton = new JButton("New Game");
        JButton highScoresButton = new JButton("High Scores");
        JButton exitButton = new JButton("Exit");

       buttonsPanel.setBorder(BorderFactory.createEmptyBorder(60, 0,0,0));

        JTextField rowsTextField = new JTextField(10);
        JTextField columnsTextField = new JTextField(10);

        buttonsPanel.add(new JLabel("Rows: "));
        buttonsPanel.add(rowsTextField);
        buttonsPanel.add(new JLabel("Columns: "));
        buttonsPanel.add(columnsTextField);

       //https://stackoverflow.com/questions/5344823/how-can-i-listen-for-key-presses-within-java-swing-across-all-components
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                          setVisible(true);
                          if(hsv!=null)hsv.setVisible(false);
                          if(gov!=null)gov.setVisible(false);
                        }
                        return false;
                    }
                });


        newGameButton.addActionListener(e -> {
            int rows = Integer.parseInt(rowsTextField.getText());
            int columns = Integer.parseInt(columnsTextField.getText());;

            if(rows>=10 && rows<=100 && columns >= 10 && columns<=100 ){
                Pacman pacman = new Pacman(rows,columns);
                GameBoard gameBoard = new GameBoard(rows,columns,pacman,this);

                setVisible(false);
                frame.getContentPane().add(gameBoard);

                gameBoard.grabFocus();

                gov = gameBoard.getGameOverView();

                ThreadsInitiator threadsInitiator = new ThreadsInitiator(gameBoard,pacman);
                threadsInitiator.startThreads();
            }

        });
        highScoresButton.addActionListener(e -> {
            setVisible(false);
            for(Component c: frame.getContentPane().getComponents()){
                if(c instanceof GameBoard){
                    frame.getContentPane().remove(c);}
            }
            hsv = new HighScoresView(this);
            frame.getContentPane().add(hsv);
        });
        exitButton.addActionListener(e -> System.exit(1));

        add(newGameButton);
        add(highScoresButton);
        add(exitButton);

        add(buttonsPanel);
        setVisible(true);

    }



}
