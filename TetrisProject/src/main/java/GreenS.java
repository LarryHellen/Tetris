import java.util.ArrayList;
import java.awt.Color;

public class GreenS extends Block{
  int rotationCount;
  
  public GreenS(int[] startCoordinate, GameManager gm){
    super(new ArrayList<int[]>(),Color.green,gm);
    
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]+1};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0]+1,startCoordinate[1]-1};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]-1};
    coordinateList.add(startCoordinate);

    rotationCount = 0;
    
    updateColors();
  }

  public boolean rotate(){
    ArrayList<int[]> newCoordinates = new ArrayList<int[]>();
    for(int[] oldCoordArray : super.coordinateList){
      int[] newCoordArray = new int[2];
      for(int i = 0; i < 2; i++){
        newCoordArray[i] = oldCoordArray[i];
      }
      newCoordinates.add(newCoordArray);
    }

    boolean possible = false;

    //Pivot = 2
    
    if(rotationCount == 0){ 
      newCoordinates.get(0)[0] += 1;
      newCoordinates.get(0)[1] += 1;
      newCoordinates.get(1)[0] += 2;
      newCoordinates.get(3)[0] -= 1;
      newCoordinates.get(3)[1] += 1;
        
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 1) {
      newCoordinates.get(0)[0] += 1;
      newCoordinates.get(0)[1] -= 1;
      newCoordinates.get(1)[1] -= 2;
      newCoordinates.get(3)[0] += 1;
      newCoordinates.get(3)[1] += 1;
      
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 2){
      newCoordinates.get(0)[0] -= 1;
      newCoordinates.get(0)[1] -= 1;
      newCoordinates.get(1)[0] -= 2;
      newCoordinates.get(3)[0] += 1;
      newCoordinates.get(3)[1] -= 1;
        
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 3){
      newCoordinates.get(0)[0] -= 1;
      newCoordinates.get(0)[1] += 1;
      newCoordinates.get(1)[1] += 2;
      newCoordinates.get(3)[0] -= 1;
      newCoordinates.get(3)[1] -= 1;
      
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount = 0;
      }
    }
    
    return possible;
  }
}