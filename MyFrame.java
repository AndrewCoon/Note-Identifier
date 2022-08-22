import javax.swing.JFrame;
import javax.swing.*;

public class MyFrame extends JFrame {
     MyFrame(int frameVert, int frameHor, String frameName) {
          ImageIcon icon = new ImageIcon("icons/musicaIcon.jfif");

          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setTitle(frameName);
          this.setSize(frameHor, frameVert);
          this.setLocationRelativeTo(null);
          this.setVisible(true);
          this.setLayout(null);
          this.setIconImage(icon.getImage());
     }
}