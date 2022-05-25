import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameManager{
  JFrame frame;
  JPanel[][] panelCoords;
  Block currentBlock;
  InputManager im;
  int[] startCoordinate;
  boolean gameOver;
  ArrayList<Block> blockList;
  int maxRows;
  int maxCols;
  int score;
  int maxScore;

  public GameManager(JFrame frame)
  {
    this.frame = frame;
    maxRows = 10;
    maxCols = 10;
    panelCoords = new JPanel[maxRows][maxCols];
    im = new InputManager(this);
    startCoordinate = new int[]{0,maxCols/2-1};
    gameOver = false;
    blockList = new ArrayList<Block>();
    score = 0;
    maxScore = 0;
  }

  public void start(){
    frame.setSize(400,400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    GridLayout grid = new GridLayout(maxRows,maxCols,1,1);
    frame.setLayout(grid);
    frame.getContentPane().setBackground(Color.black);

    for(int i = 0; i < maxRows; i++){
      for(int j = 0; j < maxCols; j++){
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        frame.add(panel);
        panelCoords[i][j] = panel;
      }
    }
    frame.addKeyListener(im);
    frame.setVisible(true);

    updateScore(0);
    
    generateBlock(true);

    Runnable downTick = new Runnable(){
      public void run(){
        if(!gameOver){
          currentBlock.reward--;
          if(!currentBlock.moveDown()){
            generateBlock();
          }
        }
      }
    };

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(downTick, 0, 1, TimeUnit.SECONDS);
    
  }

  public void generateBlock(boolean first){
    
    if(anyFullRows()){
      clearRows();
    }
    
    Block oldBlock = currentBlock;
    int r = (int)(Math.random() * 7);
    int[] tempStart = new int[2];
    for(int i = 0; i < 2; i++){
      tempStart[i] = startCoordinate[i];
    }
    switch(r){
      case 0:
        currentBlock = new BlueJ(tempStart,this);
        break;
      case 1:
        currentBlock = new CyanI(tempStart,this);
        break;
      case 2:
        currentBlock = new GreenS(tempStart,this);
        break;
      case 3:
        currentBlock = new OrangeL(tempStart,this);
        break;
      case 4:
        currentBlock = new PurpleT(tempStart,this);
        break;
      case 5:
        currentBlock = new RedZ(tempStart,this);
        break;
      case 6:
        currentBlock = new YellowO(tempStart,this);
        break;
    }
    if(!first){
      for(int[] oldCoord : oldBlock.coordinateList){
        for(int[] newCoord : currentBlock.coordinateList){
          if(oldCoord[0] == newCoord[0] && oldCoord[1] == newCoord[1]){
            gameOver = true;
            break;
          }
        }
      }
      if(!gameOver){
        updateScore(oldBlock.reward / 2);
      }
    }

  }

  public void generateBlock(){
    generateBlock(false);
  }

  public boolean isRowFull(JPanel[] row){
    boolean isFull = true;
    for(JPanel panel : row){
      if(panel.getBackground() == Color.lightGray){
        isFull = false;
        break;
      }
    }
    return isFull;
  }

  public boolean anyFullRows(){
    for(JPanel[] panel : panelCoords){
      if(isRowFull(panel)){
        return true;
      }
    }
    return false;
  }

  public void emptyRow(JPanel[] row){
    for(JPanel panel : row){
      panel.setBackground(Color.lightGray);
    }
  }

  //Precondition: -1 < rowStartIndex < maxRows - 1
  public void moveColoredPanelsDown(int rowStartIndex){
    if(rowStartIndex >= maxRows - 1){
      throw new IllegalArgumentException("rowStartIndex is " + rowStartIndex + ". Must be less than " + maxRows);
    }
    if(rowStartIndex <= -1){
      throw new IllegalArgumentException("rowStartIndex is " + rowStartIndex + ". Must be greater than -1");
    }

    for(int rowIndex = rowStartIndex; rowIndex >= 0; rowIndex--){
      for(int colIndex = 0; colIndex < maxCols; colIndex++){
        int counter = 0;
        while(panelCoords[rowIndex+counter][colIndex].getBackground() != Color.lightGray && panelCoords[rowIndex+1+counter][colIndex].getBackground() == Color.lightGray){
          panelCoords[rowIndex+1+counter][colIndex].setBackground(panelCoords[rowIndex+counter][colIndex].getBackground());
          panelCoords[rowIndex+counter][colIndex].setBackground(Color.lightGray);
          counter++;
          if(rowIndex + counter == maxRows - 1){
            break;
          }
        }
      }
    }
  }

  public void clearRows(){
    while(anyFullRows()){
      for(int rowIndex = maxRows-1; rowIndex > 0; rowIndex--){
        JPanel[] row = panelCoords[rowIndex];
        while(isRowFull(row)){
          emptyRow(row);
          updateScore(10);
          moveColoredPanelsDown(rowIndex - 1);
        }
      }
    }
  }

  public void resetGame(){
    while(blockList.size() != 0){
      blockList.remove(0);
    }
    for(JPanel[] row : panelCoords){
      for(JPanel panel : row){
        panel.setBackground(Color.lightGray);
      }
    }
    score = 0;
    updateScore(0);
    gameOver = false;
    generateBlock(true);
  }

  public void updateScore(int points){
    score += points;
    if(score > maxScore){
      maxScore = score;
    }
    frame.setTitle("Score: " + score + ", Max Score: " + maxScore);
  }
}