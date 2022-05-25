import java.util.ArrayList;
import java.awt.Color;

//cyan I, yellow O, purple T, green S, blue J, red Z and orange L.

public class Block{
  ArrayList<int[]> coordinateList;
  Color blockColor;
  GameManager gm;
  int reward;

  public Block(ArrayList<int[]> coordinateList, Color blockColor, GameManager gm){
    this.coordinateList = coordinateList;
    this.blockColor = blockColor;
    this.gm = gm;
    reward = 10;

    gm.blockList.add(this);
  }

  public boolean moveDown(){
    return generalMovement(0,1);
  }

  public boolean moveRight(){
    return generalMovement(1,1);
  }

  public boolean moveLeft(){
    return generalMovement(1,-1);
  }

  public boolean generalMovement(int rowsOrCols, int magnitude){
    //Create a new array of modified coordinates
    ArrayList<int[]> newCoordinates = new ArrayList<int[]>();
    for(int[] currentPos : coordinateList){
      int[] newCoord = new int[2];
      for(int i = 0; i < 2; i++){
        newCoord[i] = currentPos[i];
        if(i == 1){
          newCoord[rowsOrCols] += magnitude;
        }
      }
      newCoordinates.add(newCoord);
    }
    if(validMovement(newCoordinates)){
      //Set the old panels to gray
      for(int[] oldPos : coordinateList){
        gm.panelCoords[oldPos[0]][oldPos[1]].setBackground(Color.lightGray);
      }
      //Replace the old coordiantes with the new coordinates
      coordinateList = newCoordinates;
      updateColors();
      return true;
    }
    return false;
  }

  public boolean innerRotate(ArrayList<int[]> newCoordinates){
    if(validMovement(newCoordinates)){
      for(int[] oldPos : coordinateList){
        gm.panelCoords[oldPos[0]][oldPos[1]].setBackground(Color.lightGray);
      }
      coordinateList = newCoordinates;
      updateColors();
      return true;
    } return false;
  }

  public boolean rotate(){
    return false;
  }

  public void updateColors(){
    for(int i = 0; i < coordinateList.size(); i++){
      int[] currentPos = coordinateList.get(i);
      gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(blockColor);
    }
  }

  public boolean validMovement(ArrayList<int[]> newCoordinates){
    for(int[] newPos : newCoordinates){
      //Check if the any of the new coordinates are outside the bounds of the play board
      if(newPos[0] > gm.maxRows - 1 || newPos[0] < 0 || newPos[1] < 0 || newPos[1] > gm.maxCols - 1){
        return false;
      }
      boolean notSame = true;
      for(int[] testPos : coordinateList){
        if(newPos[0] == testPos[0] && newPos[1] == testPos[1]){
          notSame = false;
        }
      }
      //Check if the panel that the new coordinate is in is already occupied, and not by a panel that is a part of this block
      if(gm.panelCoords[newPos[0]][newPos[1]].getBackground() != Color.lightGray && notSame){
        return false;
      }
    }
    return true;
  }
}