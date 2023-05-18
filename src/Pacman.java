import java.awt.*;

public class Pacman {

    private PacmanModel model;
    private PacmanView view;
    Pacman(int boardRows,int boardColumns) {
        model = new PacmanModel(boardRows,boardColumns);
        view = new PacmanView(model);


    }

    public PacmanModel getModel() {
        return model;
    }

    public PacmanView getView() {
        return view;
    }


}