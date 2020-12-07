
import java.awt.Color;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KillB
 */
public abstract class Piece {

    final static int X = 0;
    final static int Y = 1;

    protected int piece[][][] = new int[4][2][4];   // piece[ pieceRotation ][X or Y] [1 - 2 - 3 - 4]
    protected int pieceCenter[] = new int[2];       // pieceCenter[X or Y]
    protected int pieceRotation = 0;
    protected int nextMove[][] = new int[2][4];
    protected int nextRotation = 0;

    protected Color pieceColor;
    protected Image image;

    Piece() {
        reset();
    }

    public void reset() {
        pieceCenter[X] = TetrisPanel.CENTER;
        pieceCenter[Y] = TetrisPanel.UNIT_SIZE;

        pieceRotation = 0;
        setBody();
    }

    public void moveDown() {

        for (int i = 0; i < 4; i++) {
            nextMove[X][i] = piece[pieceRotation][X][i];
            nextMove[Y][i] = piece[pieceRotation][Y][i] + TetrisPanel.UNIT_SIZE;

            if (nextMove[X][i] < 0 || nextMove[X][i] > TetrisPanel.GAME_WIDTH - TetrisPanel.UNIT_SIZE) {
                return;
            } else if (TetrisPanel.imageBoard[(nextMove[X][i] / TetrisPanel.UNIT_SIZE)][(nextMove[Y][i] / TetrisPanel.UNIT_SIZE)] != null) {
                TetrisPanel.stayBot = true;
                return;
            }
        }

        pieceCenter[Y] += TetrisPanel.UNIT_SIZE;
        setBody();

    }

    public void moveRight() {

        for (int i = 0; i < 4; i++) {
            nextMove[X][i] = piece[pieceRotation][X][i] + TetrisPanel.UNIT_SIZE;
            ;
            nextMove[Y][i] = piece[pieceRotation][Y][i];

            if (nextMove[X][i] < 0 || nextMove[X][i] > TetrisPanel.GAME_WIDTH - TetrisPanel.UNIT_SIZE) {
                return;
            } else if (TetrisPanel.imageBoard[(nextMove[X][i] / TetrisPanel.UNIT_SIZE)][(nextMove[Y][i] / TetrisPanel.UNIT_SIZE)] != null) {
                return;
            }
        }

        pieceCenter[X] += TetrisPanel.UNIT_SIZE;
        setBody();


    }

    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            nextMove[X][i] = piece[pieceRotation][X][i] - TetrisPanel.UNIT_SIZE;
            ;
            nextMove[Y][i] = piece[pieceRotation][Y][i];

            if (nextMove[X][i] < 0 || nextMove[X][i] > TetrisPanel.GAME_WIDTH - TetrisPanel.UNIT_SIZE) {
                return;
            } else if (TetrisPanel.imageBoard[(nextMove[X][i] / TetrisPanel.UNIT_SIZE)][(nextMove[Y][i] / TetrisPanel.UNIT_SIZE)] != null) {
                return;
            }
        }

        pieceCenter[X] -= TetrisPanel.UNIT_SIZE;
        setBody();

    }

    public int[][][] getCoordinates() {
        return piece;
    }

    public Image getImage() {
        return image;
    }

    public void setBody() {

    }

    public int getRotation() {
        return pieceRotation;
    }

    public void rotate() {
        nextRotation = pieceRotation + 1;
        nextRotation = nextRotation % 4;


        for (int i = 0; i < 4; i++) {
            nextMove[X][i] = piece[nextRotation][X][i];
            nextMove[Y][i] = piece[nextRotation][Y][i];

            if (nextMove[X][i] < 0 || nextMove[X][i] > TetrisPanel.GAME_WIDTH - TetrisPanel.UNIT_SIZE) {
                return;
            } else if (TetrisPanel.imageBoard[(nextMove[X][i] / TetrisPanel.UNIT_SIZE)][(nextMove[Y][i] / TetrisPanel.UNIT_SIZE)] != null) {
                return;
            }
        }

        pieceRotation = nextRotation;

    }


}

