
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KillB
 */
public class TetrisFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    TetrisFrame() {

        this.add(new TetrisPanel());
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}

