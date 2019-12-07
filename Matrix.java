//class that defines a 10*10 matrix, with each living cell filled in and each dead cell empty
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class Matrix extends JPanel {
    private int row;
    private int col;
    private List<Point> fillCells;

    //constructor
    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
        fillCells = new ArrayList<>();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(Point fillCell : fillCells){
            int cellX = 50 + (fillCell.x * 50);
            int cellY = 50 + (fillCell.y * 50);
            g.setColor(Color.yellow);
            g.fillRect(cellX, cellY, 50, 50);
        }
        g.setColor(Color.black);
        g.drawRect(50, 50, (row * 50), (col * 50));
        for (int r = 50; r <= (row * 50); r += 50){
            g.drawLine(r, 50, r, (col+1) * 50);
        }
        for (int c = 50; c <= (col * 50); c += 50){
            g.drawLine(50, c, (row+1) * 50, c);
        }

    }

    public void fillCell(int row, int col){
        fillCells.add(new Point(col, row));
        repaint();
    }
}
