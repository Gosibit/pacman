import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class HighScoresView extends JPanel {

    private JList<String> scoresList;

    private DefaultListModel<String> listModel;

    HighScoresView(MenuView menuView){
        readScoresFromFile();

        setLayout(new BorderLayout());

        scoresList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(scoresList);

        JButton getBack = new JButton("Back");
        getBack.setSize(new Dimension(300,300));

        getBack.addActionListener(e->{
            setVisible(false);
            menuView.setVisible(true);
        });

        this.repaint();
        add(getBack, BorderLayout.NORTH);

        scoresList.setFont(new Font("Arial", Font.BOLD, 30));
        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(600, 600));
        setVisible(true);


    }

    List<PlayerScore> readScoresFromFile() {
        List<PlayerScore> scores = new ArrayList<>();
        try  {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String line = reader.readLine();

            listModel = new DefaultListModel<>();

            if(line!=null) {
                String[] parts = line.split(";");
                for (String part : parts) {
                    String[] scoreData = part.split(":");
                    if (scoreData.length == 2) {
                        String playerName = scoreData[0];
                        int score = Integer.parseInt(scoreData[1]);
                        PlayerScore playerScore = new PlayerScore(playerName, score);
                        scores.add(playerScore);
                    }
                }

                Collections.sort(scores);

                for (PlayerScore score : scores) {
                    listModel.addElement(score.getPlayerName() + ": " + score.getScore());
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

}

