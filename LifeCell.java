//A class representing a cell in a life matrix

public class LifeCell {
    private int cellRow;
    private int cellCol;
    private int alive; // 1 = alive, 0 = dead

    //constructor
    public LifeCell (int cellRow, int cellCol, int alive){
        this.cellRow = cellRow;
        this.cellCol = cellCol;
        this.alive = alive;
    }

    //get cell row
    public int getCellRow(){
        return this.cellRow;
    }

    public int getCellCol(){
        return this.cellCol;
    }

    //get live status
    public int getAlive(){
        return this.alive;
    }

    //set live status
    public void setAlive(int alive){
        this.alive = alive;
    }

    //print cell
    public void printCell(){
        System.out.print(this.getAlive()+"  ");
    }

}
