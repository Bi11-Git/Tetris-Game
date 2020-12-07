
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KillB
 */
public class TetrisPanel extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static final int UNIT_SIZE = ((int) screenSize.getHeight() * 3) / 80;


    static final int SCREEN_HEIGHT = UNIT_SIZE * 20;
    static final int GAME_WIDTH = UNIT_SIZE * 10;
    static final int SCREEN_WIDTH = UNIT_SIZE * 15;
    static final int UNITS_HEIGHT = SCREEN_HEIGHT / UNIT_SIZE;
    static final int UNITS_WIDTH = GAME_WIDTH / UNIT_SIZE;
    static final int CENTER = GAME_WIDTH / 2;
    static final int NEWGAME_DELAY = 400;
    static Image imageBoard[][];


    Timer timer;

    private boolean running = false;
    private boolean pause = false;
    static boolean stayBot = false;
    private static int DELAY;
    private int coordinates[][][];
    private int nextCoord[][];
    private int score;
    private int level;
    private Image image;
    private PieceList pieceList;
    private Piece piece;
    private Piece nextPiece;


    TetrisPanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener((KeyListener) new myKeyAdapter());

        startGame();
    }

    public void startGame() {

        pause = true;
        DELAY = NEWGAME_DELAY;
        stayBot = false;
        pieceList = new PieceList();
        coordinates = new int[4][2][4];

        imageBoard = new Image[UNITS_WIDTH + 1][UNITS_HEIGHT + 1];

        for (int x = 0; x < UNITS_WIDTH; x++) {
            for (int y = 0; y < UNITS_HEIGHT + 1; y++) {
                if (y == UNITS_HEIGHT) {
                    imageBoard[x][y] = new ImageIcon("icons//I_Block.png").getImage();
                } else {
                    imageBoard[x][y] = null;
                }
            }
        }

        score = 0;

        piece = pieceList.newPiece();
        image = piece.getImage();

        nextPiece = pieceList.nextPiece();
        nextCoord = pieceList.nextPieceCoords();


        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            // Draw Line

            g.fillRect(GAME_WIDTH, 0, 10, SCREEN_HEIGHT);

            // Draw lines

            for (int i = 0; i < UNITS_HEIGHT; i++) {
                g.drawLine(0, i * UNIT_SIZE, GAME_WIDTH, i * UNIT_SIZE);

                if (i < UNITS_WIDTH) {
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                }

            }

            // Draw moving pieces

            coordinates = piece.getCoordinates();
            image = piece.getImage();
            int rotation = piece.getRotation();
            Graphics2D g2D = (Graphics2D) g;

            for (int i = 0; i < 4; i++) {

                g2D.drawImage(image, coordinates[rotation][Piece.X][i], coordinates[rotation][Piece.Y][i], UNIT_SIZE, UNIT_SIZE, null);

            }

            // Draw bottom pieces

            for (int x = 0; x < UNITS_WIDTH; x++) {
                for (int y = 0; y < UNITS_HEIGHT; y++) {

                    if (imageBoard[x][y] != null) {

                        image = imageBoard[x][y];
                        g2D.drawImage(image, x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, null);

                    }
                }
            }

            // Draw game score

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics1 = getFontMetrics(g.getFont());

            g.drawString("Score", ((SCREEN_WIDTH - GAME_WIDTH - metrics1.stringWidth("Score")) / 2) + GAME_WIDTH, g.getFont().getSize() + UNIT_SIZE);
            g.drawString("" + score, ((SCREEN_WIDTH - GAME_WIDTH - metrics1.stringWidth("" + score)) / 2) + GAME_WIDTH, g.getFont().getSize() + (2 * UNIT_SIZE));

            //Draw game level

            g.drawString("Level", ((SCREEN_WIDTH - GAME_WIDTH - metrics1.stringWidth("Level")) / 2) + GAME_WIDTH, g.getFont().getSize() + (13 * UNIT_SIZE));
            g.drawString("" + level, ((SCREEN_WIDTH - GAME_WIDTH - metrics1.stringWidth("" + level)) / 2) + GAME_WIDTH, g.getFont().getSize() + (14 * UNIT_SIZE));

            // Draw pause

            String pauseGame;
            if (pause) {
                pauseGame = "pause";
            } else {
                pauseGame = "start";
            }

            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics4 = getFontMetrics(g.getFont());
            g.drawString("Enter :", ((SCREEN_WIDTH - GAME_WIDTH - metrics4.stringWidth("Enter :")) / 2) + GAME_WIDTH, g.getFont().getSize() + (17 * UNIT_SIZE));
            g.drawString("" + pauseGame, ((SCREEN_WIDTH - GAME_WIDTH - metrics4.stringWidth("" + pauseGame)) / 2) + GAME_WIDTH, g.getFont().getSize() + (18 * UNIT_SIZE));
            // Draw next Piece

            image = nextPiece.getImage();

            nextCoord = pieceList.nextPieceCoords();

            for (int i = 0; i < 4; i++) {

                g2D.drawImage(image, nextCoord[Piece.X][i] + (7 * UNIT_SIZE), nextCoord[Piece.Y][i] + (8 * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE, null);

            }


        } else {
            gameOver(g);
        }
    }


    public void checkBottom() {

        if (stayBot) {
            int rotation = piece.getRotation();
            for (int i = 0; i < 4; i++) {
                image = piece.getImage();
                imageBoard[coordinates[rotation][Piece.X][i] / UNIT_SIZE][coordinates[rotation][Piece.Y][i] / UNIT_SIZE] = image;
            }

            int widthCounter = 0;


            for (int y = 0; y < UNITS_HEIGHT; y++) {
                widthCounter = 0;
                for (int x = 0; x < UNITS_WIDTH; x++) {
                    if (imageBoard[x][y] != null) {
                        widthCounter++;
                        if (widthCounter == UNITS_WIDTH) {
                            score++;
                            removeLayer(y);
                        }
                    }
                }
            }

            for (int x = 0; x < UNITS_WIDTH; x++) {
                if (imageBoard[x][1] != null) {

                    running = false;
                    timer.stop();

                }
            }

            if ((score / 10) > level) {
                level = score / 10;
                DELAY -= 50;
                timer.setDelay(DELAY);
            }

            piece = pieceList.newPiece();

            nextPiece = pieceList.nextPiece();

            stayBot = false;
        }
    }

    public void removeLayer(int layer) {
        for (int y = layer; y > 0; y--) {

            for (int x = 0; x < UNITS_WIDTH; x++) {
                imageBoard[x][y] = imageBoard[x][y - 1];
            }


        }

        repaint();
    }

    public void gameOver(Graphics g) {

        // Game over score

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());

        g.drawString("Score :", (SCREEN_WIDTH - metrics1.stringWidth("Score :")) / 2, g.getFont().getSize() + (2 * UNIT_SIZE));

        g.drawString("" + score, (SCREEN_WIDTH - metrics1.stringWidth("" + score)) / 2, g.getFont().getSize() + (3 * UNIT_SIZE));

        g.setFont(new Font("Ink Free", Font.BOLD, 45));
        FontMetrics metrics3 = getFontMetrics(g.getFont());

        g.drawString(" PRESS ENTER ", (SCREEN_WIDTH - metrics3.stringWidth(" PRESS ENTER ")) / 2, g.getFont().getSize() + (14 * UNIT_SIZE));


        // Game over text

        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());

        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

    }

    public void gameReset() {

        stayBot = false;
        pieceList = new PieceList();
        coordinates = new int[4][2][4];

        startGame();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && pause) {
            piece.moveDown();
            checkBottom();
        }

        repaint();


    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    if (pause) {
                        piece.rotate();
                        repaint();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (pause) {
                        piece.moveRight();
                        repaint();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (pause) {
                        piece.moveLeft();
                        repaint();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (pause) {
                        piece.moveDown();
                        repaint();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (!running) {
                        gameReset();
                        repaint();
                        break;
                    } else if (pause) {
                        pause = false;
                    } else {
                        pause = true;
                    }

            }


        }
    }
}
	
