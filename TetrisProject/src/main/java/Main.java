import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    GameManager gm = new GameManager(frame);
    gm.start();
  }
}