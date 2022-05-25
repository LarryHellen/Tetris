import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputManager implements KeyListener{

  GameManager gm;
  
  public InputManager(GameManager gm){
    this.gm = gm;
  }
  
  public void keyPressed(KeyEvent e){
    if(!gm.gameOver){
      if(e.getKeyCode() == KeyEvent.VK_DOWN){
        if(!gm.currentBlock.moveDown()){
          gm.generateBlock();
        }
      } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        gm.currentBlock.moveRight();
      } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        gm.currentBlock.moveLeft();
      } else if(e.getKeyCode() == KeyEvent.VK_UP){
        gm.currentBlock.rotate();  
      } else if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
        while(gm.currentBlock.moveDown()){
          
        }
        gm.generateBlock();
      }
    }
    if(e.getKeyCode() == KeyEvent.VK_R){
      gm.resetGame();
    }
  }
  
  public void keyTyped(KeyEvent e){
    
  }

  public void keyReleased(KeyEvent e){
    
  }
}