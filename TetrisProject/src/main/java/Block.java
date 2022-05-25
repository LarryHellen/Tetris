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
    for(int[] currentPos : coordinateList){
      if(currentPos[0]+1 > gm.maxRows-1){
        return false;
      }
      boolean notSame = true;
      for(int[] testPos : coordinateList){
        if(currentPos[0]+1 == testPos[0] && currentPos[1] == testPos[1])
          notSame = false;
      }
      if(gm.panelCoords[currentPos[0]+1][currentPos[1]].getBackground() != Color.lightGray && notSame){
        return false;
      }
    }
    ArrayList<int[]> testList = new ArrayList<int[]>();
    for(int[] coordinateArray : coordinateList){
      int[] testArray = new int[2];
      for(int i = 0; i < 2; i++){
        testArray[i] = coordinateArray[i];
      }
      testList.add(testArray);
    }
    for(int[] currentPos : coordinateList){
      boolean check = true;
      for(int[] testPos : testList){
        if(testPos[0] + 1 == currentPos[0] && testPos[1] == currentPos[1]){
          check = false;
          break;
        }
      }
      if(check){
        gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(Color.lightGray);
      }
      currentPos[0]++;
      gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(blockColor);
    }
    return true;
  }

  public boolean moveRight(){
    for(int[] currentPos : coordinateList){
      if(currentPos[1]+1 > gm.maxCols-1){
        return false;
      }
      boolean notSame = true;
      for(int[] testPos : coordinateList){
        if(currentPos[1]+1 == testPos[1] && currentPos[0] == testPos[0])
          notSame = false;
      }
      if(gm.panelCoords[currentPos[0]][currentPos[1]+1].getBackground() != Color.lightGray && notSame){
        return false;
      }
    }
    ArrayList<int[]> testList = new ArrayList<int[]>();
    for(int[] coordinateArray : coordinateList){
      int[] testArray = new int[2];
      for(int i = 0; i < 2; i++){
        testArray[i] = coordinateArray[i];
      }
      testList.add(testArray);
    }
    for(int[] currentPos : coordinateList){
      boolean check = true;
      for(int[] testPos : testList){
        if(testPos[1] + 1 == currentPos[1] && testPos[0] == currentPos[0]){
          check = false;
          break;
        }
      }
      if(check){
        gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(Color.lightGray);
      }
      currentPos[1]++;
      gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(blockColor);
    }
    return true;
  }

  public boolean moveLeft(){
    for(int[] currentPos : coordinateList){
      if(currentPos[1]-1 < 0){
        return false;
      }
      boolean notSame = true;
      for(int[] testPos : coordinateList){
        if(currentPos[1]-1 == testPos[1] && currentPos[0] == testPos[0])
          notSame = false;
      }
      if(gm.panelCoords[currentPos[0]][currentPos[1]-1].getBackground() != Color.lightGray && notSame){
        return false;
      }
    }
    ArrayList<int[]> testList = new ArrayList<int[]>();
    for(int[] coordinateArray : coordinateList){
      int[] testArray = new int[2];
      for(int i = 0; i < 2; i++){
        testArray[i] = coordinateArray[i];
      }
      testList.add(testArray);
    }
    for(int[] currentPos : coordinateList){
      boolean check = true;
      for(int[] testPos : testList){
        if(testPos[1] - 1 == currentPos[1] && testPos[0] == currentPos[0]){
          check = false;
          break;
        }
      }
      if(check){
        gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(Color.lightGray);
      }
      currentPos[1]--;
      gm.panelCoords[currentPos[0]][currentPos[1]].setBackground(blockColor);
    }
    return true;
  }

  public boolean innerRotate(ArrayList<int[]> newCoordinates){
    for(int[] newPos : newCoordinates){
      if(newPos[0] > gm.maxRows - 1 || newPos[0] < 0 || newPos[1] < 0 || newPos[1] > gm.maxCols - 1){
        return false;
      }
      boolean notSame = true;
      for(int[] testPos : coordinateList){
        if(newPos[0] == testPos[0] && newPos[1] == testPos[1]){
          notSame = false;
        }
      }
      if(gm.panelCoords[newPos[0]][newPos[1]].getBackground() != Color.lightGray && notSame){
        return false;
      }
    }
    for(int[] oldPos : coordinateList){
      gm.panelCoords[oldPos[0]][oldPos[1]].setBackground(Color.lightGray);
    }
    coordinateList = newCoordinates;
    updateColors();
    
    return true;
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
}