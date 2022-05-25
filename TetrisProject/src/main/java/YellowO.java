import java.util.ArrayList;
import java.awt.Color;

public class YellowO extends Block{
  public YellowO(int[] startCoordinate, GameManager gm){
    super(new ArrayList<int[]>(),Color.yellow,gm);
    
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]+1};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0]+1,startCoordinate[1]};
    coordinateList.add(startCoordinate);
    startCoordinate = new int[]{startCoordinate[0],startCoordinate[1]-1};
    coordinateList.add(startCoordinate);
    
    updateColors();
  }

  public boolean rotate(){
    return true;
  }
}