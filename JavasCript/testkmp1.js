 
    public static int[] eliminarElgo(int[] arreglo, int poscicion) {
    int[] nuevoArreglo = new int[arreglo.length - 1]; // arreglo nuevo con una posición menos
    for (int i = 0, j = 0; i < arreglo.length; i++) 
    {
        if (i != poscicion) {
            nuevoArreglo[j++] = arreglo[i];
        }
    }
    return nuevoArreglo; //mandas el nuevo arreglo
    }






// Import the necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Define a class that extends JFrame and implements ActionListener
public class SearchmineGUI extends JFrame implements ActionListener {

  // Declare some constants for the game parameters
  private static final int ROWS = 10; // Number of rows in the grid
  private static final int COLS = 10; // Number of columns in the grid
  private static final int MINES = 10; // Number of mines in the grid
  private static final int CELL_SIZE = 20; // Size of each cell in pixels
  private static final int GAP = 2; // Gap between cells in pixels
  private static final int MARGIN = 10; // Margin around the grid in pixels
  private static final Color BG_COLOR = Color.LIGHT_GRAY; // Background color of the frame
  private static final Color CELL_COLOR = Color.WHITE; // Default color of the cells
  private static final Color MINE_COLOR = Color.RED; // Color of the cells with mines
  private static final Color FLAG_COLOR = Color.GREEN; // Color of the cells with flags
  private static final Color TEXT_COLOR = Color.BLACK; // Color of the text in the cells
  private static final Font TEXT_FONT = new Font("Arial", Font.BOLD, 12); // Font of the text in the cells

  // Declare some variables for the game logic
  private JButton[][] buttons; // A 2D array of buttons for the grid
  private boolean[][] mines; // A 2D array of booleans for the mine locations
  private int[][] counts; // A 2D array of integers for the number of adjacent mines
  private boolean[][] flags; // A 2D array of booleans for the flag locations
  private boolean[][] revealed; // A 2D array of booleans for the revealed cells
  private int minesLeft; // The number of mines left to find
  private boolean gameOver; // A flag to indicate if the game is over

  // Define a constructor that takes the title of the frame as a parameter
  public SearchmineGUI(String title) {
    // Call the super constructor with the title
    super(title);

    // Set the size of the frame based on the game parameters
    setSize(COLS * (CELL_SIZE + GAP) + 2 * MARGIN, ROWS * (CELL_SIZE + GAP) + 2 * MARGIN);

    // Set the background color of the frame
    getContentPane().setBackground(BG_COLOR);

    // Set the layout of the frame to null
    setLayout(null);

    // Initialize the arrays for the game logic
    buttons = new JButton[ROWS][COLS];
    mines = new boolean[ROWS][COLS];
    counts = new int[ROWS][COLS];
    flags = new boolean[ROWS][COLS];
    revealed = new boolean[ROWS][COLS];

    // Initialize the variables for the game logic
    minesLeft = MINES;
    gameOver = false;

    // Create and add the buttons to the frame
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        // Create a new button with an empty text
        buttons[i][j] = new JButton("");

        // Set the size and location of the button based on the game parameters
        buttons[i][j].setBounds(MARGIN + j * (CELL_SIZE + GAP), MARGIN + i * (CELL_SIZE + GAP), CELL_SIZE, CELL_SIZE);

        // Set the color and font of the button
        buttons[i][j].setBackground(CELL_COLOR);
        buttons[i][j].setForeground(TEXT_COLOR);
        buttons[i][j].setFont(TEXT_FONT);

        // Add an action listener to the button
        buttons[i][j].addActionListener(this);

        // Add the button to the frame
        add(buttons[i][j]);
      }
    }

    // Generate the mines randomly
    generateMines();

    // Calculate the counts for each cell
    calculateCounts();
  }

  // Define a method that generates the mines randomly
  private void generateMines() {
    // Create a random object
    java.util.Random rand = new java.util.Random();

    // Loop until the desired number of mines are generated
    int count = 0;
    while (count < MINES) {
      // Generate a random row and column
      int row = rand.nextInt(ROWS);
      int col = rand.nextInt(COLS);

      // If the cell is not already a mine, make it a mine and increment the count
      if (!mines[row][col]) {
        mines[row][col] = true;
        count++;
      }
    }
  }

  // Define a method that calculates the counts for each cell
  private void calculateCounts() {
    // Loop through each cell
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        // If the cell is a mine, set the count to -1
        if (mines[i][j]) {
          counts[i][j] = -1;
        } else {
          // Otherwise, count the number of adjacent mines and set the count accordingly
          int count = 0;
          for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
              // Check the bounds and the mine status of the neighboring cell
              if (k >= 0 && k < ROWS && l >= 0 && l < COLS && mines[k][l]) {
                count++;
              }
            }
          }
          counts[i][j] = count;
        }
      }
    }
  }

  // Define a method that handles the button clicks
  public void actionPerformed(ActionEvent e) {
    // Get the source of the action
    Object source = e.getSource();

    // Loop through each button
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        // If the source is the current button
        if (source == buttons[i][j]) {
          // If the game is not over
          if (!gameOver) {
            // If the left mouse button is clicked
            if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
              // If the cell is not flagged, reveal it
              if (!flags[i][j]) {
                reveal(i, j);
              }
            }
            // If the right mouse button is clicked
            if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
              // If the cell is not revealed, toggle the flag
              if (!revealed[i][j]) {
                toggleFlag(i, j);
              }
            }
          }
          // Break the inner loop
          break;
        }
      }
    }
  }

  // Define a method that reveals a cell
  private void reveal(int i, int j) {
    // If the cell is a mine, end the game
    if (mines[i][j]) {
      endGame(false);
    } else {
      // Otherwise, reveal the cell and update the button text and color
      revealed[i][j] = true;
      buttons[i][j].setText(counts[i][j] == 0 ? "" : String.valueOf(counts[i][j]));
      buttons[i][j].setBackground(CELL_COLOR);

      // If the cell has zero adjacent mines, reveal the neighboring cells recursively
      if (counts[i][j] == 0) {
        for (int k = i - 1; k <= i + 1; k++) {
          for (int l = j - 1; l <= j + 1; l++) {
            // Check the bounds and the reveal status of the neighboring cell
            if (k >= 0 && k < ROWS && l >= 0 && l < COLS && !revealed[k][l]) {
              reveal(k, l);
            }
          }
        }
      }

      // Check if the game is won
      checkWin();
    }
  }

  // Define a method that toggles a flag
  private void toggleFlag(int i, int j) {
    // If the cell is already flagged, remove the flag and update the button text and color
    if (flags[i][j]) {
      flags[i][j] = false;
      buttons[i][j].setText("");
      buttons[i][j].setBackground(CELL_COLOR);
      minesLeft++;
    } else {
      // Otherwise, add the flag and update the button text and color
      flags[i][j] = true;
      buttons[i][j].setText("F");
      buttons[i][j].setBackground(FLAG_COLOR);
      minesLeft--;
    }
  }

  // Define a method that checks if the game is won
  private void checkWin() {
    // Loop through each cell
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        // If the cell is not a mine and not revealed, return
        if (!mines[i][j] && !revealed[i][j]) {
          return;
        }
      }
    }
    // If
/*
@Henko
*/ 
