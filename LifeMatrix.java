//a class that defines the life game matrix and contains methods for determining birth, death, existence for each cell

import java.security.SecureRandom;


public class LifeMatrix {
    private static final SecureRandom randomNumbers = new SecureRandom(); //random initialization of first matrix
    private int row;
    private int col;
    private LifeCell[][] lifeMatrix;

    //constructor instantiates a new matrix
    public LifeMatrix(int row, int col){
        this.row = row;
        this.col = col;
        this.lifeMatrix = new LifeCell[row][col];
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void getSize(){
        int row = this.getRow();
        int col = this.getCol();
        System.out.println(row + " rows * " + col + " columns");
    }

    //return life status for a specific cell in lifeMatrix
    public int getCellLife(int row, int col){
        return lifeMatrix[row][col].getAlive();
    }

    public void setCellStatus(int row, int col, int alive){
        LifeCell cell = new LifeCell(row, col, alive);
        lifeMatrix[row][col] = cell;
    }

    //create first matrix with random life
    public void createLife(){
        for (int row = 0; row < this.getRow(); row++){
            for (int col = 0; col < this.getCol(); col++){
                int life = randomNumbers.nextInt(2); //randomly assign 0 or 1
                LifeCell cell = new LifeCell(row, col, life);
                lifeMatrix[row][col] = cell;
            }
        }
    }

    //determines if cell's neighbor is valid in matrix
    public boolean isValidNeighbor(int row, int col){
        boolean valid = true;
        if (row < 0 || row >= this.getRow()){
            valid = false;
        }
        else if (col < 0 || col >= this.getCol()){
            valid = false;
        }
        return valid;
    }

    //returns number of cell's living neighbors
    public int livingNeighbors(LifeCell cell){
        int living = 0;
        for (int row = cell.getCellRow()-1; row <= cell.getCellRow() +1; row++){
            for (int col = cell.getCellCol() -1; col <= cell.getCellCol() + 1; col++){
                if (isValidNeighbor(row, col)){
                    int alive = this.getCellLife(row,col);
                    LifeCell neighbor = new LifeCell(row, col, alive);
                    living += neighbor.getAlive();
                }
            }
        }
        return (living - cell.getAlive()); //subtract life status of cell because it's contained in the loop
    }

    //sets life status for next generation of cell based on Conway's genetic laws
    public int nextGenLife(LifeCell cell){
        int living = livingNeighbors(cell);
        int nextLife;
        if (cell.getAlive() == 0 && living == 3){
            nextLife = 1;
        }
        else if (cell.getAlive() == 1 && living < 2){
            nextLife = 0;
        }
        else if (cell.getAlive() == 1 && living > 3){
            nextLife = 0;
        }
        else if (cell.getAlive() == 1 && (living == 2 || living == 3)){
            nextLife = 1;
        }
        else {
            nextLife = cell.getAlive();
        }
        return nextLife;
    }

    //create next generation lifeMatrix
    public LifeMatrix newGenMatrix(){
        LifeMatrix newGenMatrix = new LifeMatrix(this.getRow(), this.getCol());
        for (int newGenRow = 0; newGenRow < this.getRow(); newGenRow++){
            for (int newGenCol = 0; newGenCol < this.getCol(); newGenCol++){
                LifeCell cell = new LifeCell(newGenRow, newGenCol, this.getCellLife(newGenRow, newGenCol));
                int newLife = nextGenLife(cell);
                newGenMatrix.setCellStatus(newGenRow, newGenCol, newLife);
            }
        }
        return newGenMatrix;
    }

    //print as matrix
    public void printMatrix(){
        for (int r = 0; r < this.getRow(); r++){
            for (int c = 0; c < this.getCol(); c++){
                LifeCell cell = new LifeCell(r,c, this.getCellLife(r,c));
                cell.printCell();
                if (c == this.getCol()-1){
                    System.out.println();
                }
            }
        }
    }
}
