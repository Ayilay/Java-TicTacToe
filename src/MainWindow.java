/**
 *  MainWindow.java
 *
 *  Contains the main method, and instantiates the main JFrame
 */

import javax.swing.JFrame;

public class MainWindow extends JFrame
{
    public static void main(String[] args) {
        new MainWindow();
    }

    public MainWindow() {
        GUIManager guiManager = new GUIManager();
        GameController gameController = new GameController(guiManager);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600 + 46); // On Linux for some reason 46 pixels get lost wtf
        setTitle("Tic Tac Toe");
        add(guiManager);
        setVisible(true);

        gameController.start();
    }
}
