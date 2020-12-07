
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KillB
 */
public class BlockT extends Piece {


    public BlockT() {

        image = new ImageIcon("icons//BlockT.png").getImage();

    }

    public void setBody() {

        piece[0][X][0] = pieceCenter[X];
        piece[0][Y][0] = pieceCenter[Y];

        piece[0][X][1] = pieceCenter[X] - TetrisPanel.UNIT_SIZE;
        piece[0][Y][1] = pieceCenter[Y];

        piece[0][X][2] = pieceCenter[X] + TetrisPanel.UNIT_SIZE;
        piece[0][Y][2] = pieceCenter[Y];

        piece[0][X][3] = pieceCenter[X];
        piece[0][Y][3] = pieceCenter[Y] - TetrisPanel.UNIT_SIZE;


        piece[1][X][0] = pieceCenter[X];
        piece[1][Y][0] = pieceCenter[Y];

        piece[1][X][1] = pieceCenter[X];
        piece[1][Y][1] = pieceCenter[Y] - TetrisPanel.UNIT_SIZE;

        piece[1][X][2] = pieceCenter[X];
        piece[1][Y][2] = pieceCenter[Y] + TetrisPanel.UNIT_SIZE;

        piece[1][X][3] = pieceCenter[X] + TetrisPanel.UNIT_SIZE;
        piece[1][Y][3] = pieceCenter[Y];


        piece[2][X][0] = pieceCenter[X];
        piece[2][Y][0] = pieceCenter[Y];

        piece[2][X][1] = pieceCenter[X] + TetrisPanel.UNIT_SIZE;
        piece[2][Y][1] = pieceCenter[Y];

        piece[2][X][2] = pieceCenter[X] - TetrisPanel.UNIT_SIZE;
        piece[2][Y][2] = pieceCenter[Y];

        piece[2][X][3] = pieceCenter[X];
        piece[2][Y][3] = pieceCenter[Y] + TetrisPanel.UNIT_SIZE;


        piece[3][X][0] = pieceCenter[X];
        piece[3][Y][0] = pieceCenter[Y];

        piece[3][X][1] = pieceCenter[X];
        piece[3][Y][1] = pieceCenter[Y] + TetrisPanel.UNIT_SIZE;

        piece[3][X][2] = pieceCenter[X];
        piece[3][Y][2] = pieceCenter[Y] - TetrisPanel.UNIT_SIZE;

        piece[3][X][3] = pieceCenter[X] - TetrisPanel.UNIT_SIZE;
        piece[3][Y][3] = pieceCenter[Y];


    }

}

