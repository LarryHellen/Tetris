import java.util.ArrayList;
import java.awt.Color;

public class PurpleT extends Block{
  int rotationCount;
  
  public PurpleT(int[] startCoordinate, GameManager gm){
    super(new ArrayList<int[]>(),Color.pink,gm);
    
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]+1};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]-2};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0]+1,startCoordinate[1]+1};
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


    //Pivot = 0
    
    boolean possible = false;
    
    if(rotationCount == 0){
      newCoordinates.get(1)[0] += 1;
      newCoordinates.get(1)[1] -= 1;
      newCoordinates.get(2)[0] -= 1;
      newCoordinates.get(2)[1] += 1;
      newCoordinates.get(3)[0] -= 1;
      newCoordinates.get(3)[1] -= 1;

      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 1){
      newCoordinates.get(1)[0] -= 1;
      newCoordinates.get(1)[1] -= 1;
      newCoordinates.get(2)[0] += 1;
      newCoordinates.get(2)[1] += 1;
      newCoordinates.get(3)[0] -= 1;
      newCoordinates.get(3)[1] += 1;
      
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 2){
      newCoordinates.get(1)[0] -= 1;
      newCoordinates.get(1)[1] += 1;
      newCoordinates.get(2)[0] += 1;
      newCoordinates.get(2)[1] -= 1;
      newCoordinates.get(3)[0] += 1;
      newCoordinates.get(3)[1] += 1;

      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount++;
      }
    } else if(rotationCount == 3){
      newCoordinates.get(1)[0] += 1;
      newCoordinates.get(1)[1] += 1;
      newCoordinates.get(2)[0] -= 1;
      newCoordinates.get(2)[1] -= 1;
      newCoordinates.get(3)[0] += 1;
      newCoordinates.get(3)[1] -= 1;
      
      possible = super.innerRotate(newCoordinates);
      if(possible){
        rotationCount = 0;
      }
    }
    return possible;
  }
}