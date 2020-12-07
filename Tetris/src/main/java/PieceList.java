
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KillB
 */
public class PieceList {

    private ArrayList<Piece> onePiece;
    private Piece piece;
    private Piece nextPiece;
    private int nextPieceNumb = -1;
    private int numb;

    private int nextChoords[][] = new int[2][4];

    Random random;

    public PieceList() {

        onePiece = new ArrayList<Piece>();
        random = new Random();

        onePiece.add(new BlockL());
        onePiece.add(new BlockJ());
        onePiece.add(new BlockI());
        onePiece.add(new BlockT());
        onePiece.add(new BlockZ());
        onePiece.add(new BlockO());
        onePiece.add(new BlockS());

    }

    public Piece newPiece() {

        if (nextPieceNumb < 0) {
            nextPieceNumb = random.nextInt(7);
        }

        piece = onePiece.get(nextPieceNumb);
        while ((numb = random.nextInt(7)) == nextPieceNumb) {
            nextPieceNumb = numb;
        }
        nextPieceNumb = numb;
        ;

        return piece;
    }

    public Piece nextPiece() {

        nextPiece = onePiece.get(nextPieceNumb);
        nextPiece.reset();
        int choords[][][] = nextPiece.getCoordinates();
        for (int i = 0; i < 4; i++) {
            nextChoords[0][i] = choords[nextPiece.getRotation()][0][i];
            nextChoords[1][i] = choords[nextPiece.getRotation()][1][i];
        }

        return nextPiece;
    }


    public int[][] nextPieceCoords() {

        return nextChoords;
    }


}

