//A game simulation based on John Conway's living organism model

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class LifeGame {
    public static void main(String[] args){
        int input = JOptionPane.showConfirmDialog(null, "Let's create life!");
        LifeMatrix lifeMatrix = new LifeMatrix(10, 10); //all functions consider that matrix size can be changed, can use Scanner class to input desired row/col sizes
        lifeMatrix.createLife();


        while (input == 0){
            int row = lifeMatrix.getRow();
            int col = lifeMatrix.getCol();
            Matrix matrix = new Matrix(row, col);
            JFrame window = new JFrame();
            JPanel panel = new JPanel();
            window.setSize((row+2)*50, (col+2)*50);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.add(matrix);
            window.setVisible(true);
            for (int r = 0; r < row; r++){
                for (int c = 0; c < col; c++){
                    if(lifeMatrix.getCellLife(r, c) == 1){
                        matrix.fillCell(r, c);
                    }
                }
            }
            lifeMatrix = lifeMatrix.newGenMatrix();
            input = JOptionPane.showConfirmDialog(null, "Would you like to see the next generation?");
        }
    }
}
