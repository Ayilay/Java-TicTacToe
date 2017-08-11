/**
 * GameController.java
 *
 * Contains the main thread and contains the state of the "game"
 */

import javax.swing.JComponent;

public class GameController
{
    public static final int N = 3;
    private char[][] grid;

    // Game state variables
    private char playerTurn = 'O'; // whoose turn it is (Orange or Blue)

    // Reference to the GUIManager
    private GUIManager guiManager;

    public GameController(GUIManager guiManager) {
        grid = new char[N][N];

        this.guiManager = guiManager;
        guiManager.updateGameControllerInstance(this);
    }

    public void start() {
        new Thread(new Runnable() {
            private final long updatePeriod = 1;
            private long lastMillis = 0;

            // The main loop
            public void run() {
                lastMillis = System.currentTimeMillis();
                while (true) {
                    // Controls the "fps" of the application
                    if (System.currentTimeMillis() - lastMillis > updatePeriod) {
                        if(getVictoryTeam() != 0) {
                            guiManager.setVictory(getVictoryTeam());
                        }

                        // Paint the graphics
                        guiManager.repaint();

                        lastMillis += updatePeriod;
                    }

                }
            }
        }).start();
    }

    public void squareClicked(int r, int c) {
        if (!squareIsEmpty(r, c)) return;

        grid[r][c] = playerTurn;
        playerTurn = (playerTurn == 'O' ? 'B' : 'O');

    }
    
    public boolean squareIsOrange(int r, int c) {
        return grid[r][c] == 'O';
    }

    public boolean squareIsBlue(int r, int c) {
        return grid[r][c] == 'B';
    }

    public boolean squareIsEmpty(int r, int c) {
        return grid[r][c] == 0;
    }

    // Checks for victory and returns the victory team
    private char getVictoryTeam() {
        if (GUIManager.DEBUG) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] == 0)
                        System.out.print(" ");
                    else
                        System.out.print(grid[r][c]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }

        // check rows
        if (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2]) return grid[0][0];
        if (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2]) return grid[1][0];
        if (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2]) return grid[2][0];

        // check cols
        if (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]) return grid[0][0];
        if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]) return grid[0][1];
        if (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]) return grid[0][2];

        // check diags
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return grid[0][0];
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) return grid[0][2];

        // check if grid is full
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                if (grid[r][c] == 0)
                    return 0;

        // Draw
        return 'D';
    }
}
