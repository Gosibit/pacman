import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameOverView extends JList {

    private JTextField nicknameField;
    private MenuView menuView;
    private Pacman pacman;
    public GameOverView(Pacman pacman, MenuView menuView) {
        this.pacman = pacman;

        this.menuView = menuView;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 200));

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        setVisible(true);
        add(gameOverLabel, BorderLayout.CENTER);

        JPanel saveScorePanel = new JPanel();
        saveScorePanel.setLayout(new FlowLayout());

        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameField = new JTextField(20);
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNickname();
            }
        });

        saveScorePanel.add(nicknameLabel);
        saveScorePanel.add(nicknameField);
        saveScorePanel.add(saveButton);

        add(saveScorePanel, BorderLayout.SOUTH);
    }

    private void saveNickname() {
        String nickname = nicknameField.getText();
        if (!nickname.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
                writer.write(nickname+":"+pacman.getModel().getPoints()+";");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setVisible(false);
            menuView.setVisible(true);
        }
    }
}