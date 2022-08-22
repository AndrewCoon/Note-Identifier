import java.io.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.lang.Object;

//creates main and start menu
public class main extends Object implements ActionListener, Serializable {
     private JPanel panel = new JPanel();
     private JLabel scoreLabel = new JLabel();
     // private String buttonText;
     public boolean gameHasStarted = false;

     public JButton aButton = new JButton("A");
     public JButton bButton = new JButton("B");
     public JButton cButton = new JButton("C");
     public JButton dButton = new JButton("D");
     public JButton eButton = new JButton("E");
     public JButton fButton = new JButton("F");
     public JButton gButton = new JButton("G");

     public JLabel aLabel = new JLabel();
     public JLabel bLabel = new JLabel();
     public JLabel cLabel = new JLabel();
     public JLabel dLabel = new JLabel();
     public JLabel eLabel = new JLabel();
     public JLabel fLabel = new JLabel();
     public JLabel gLabel = new JLabel();

     public MyFrame frame = new MyFrame(1080, 620, "Note Identifier");

     public String colour;

     private JLabel startLabel = new JLabel();
     private static String startButtonLabel = "Start";

     String noteName;
     int note;

     int xOffset = 250;
     int labelOffset = 500;
     int yOffset = 100;
     int buttonWidth = 300;

     String correctAnswer = noteName;

     String answer = null;
     int score = 0;

     boolean gameOver = true;

     JButton startButton = new JButton(startButtonLabel);

     boolean firstPress = true;
     boolean submit = false;

     int highScore = 0;

     // The GUI

     public static void main(String[] args) {
          new main();
     }

     public void load() {
          try {
               FileInputStream fis = new FileInputStream("save.dat");
               BufferedInputStream bis = new BufferedInputStream(fis);
               ObjectInputStream ois = new ObjectInputStream(bis);

               DataStorage dStorage = (DataStorage) ois.readObject();

               highScore = dStorage.highScore;

          } catch (IOException ex) {
               ex.printStackTrace();
          } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
          }
     }

     public main() {
          load();
          frameSetup();
          panelSetup();

          panel.add(scoreLabel).setBounds(xOffset + 488, yOffset - 75, buttonWidth, 30);
          scoreLabel.setSize(700, 70);
          scoreLabel.setFont(new Font("Serif", Font.BOLD, 60));

          scoreLabel.setText(String.valueOf(highScore));

          startButton.addActionListener(this);
          aButton.addActionListener(this);
          bButton.addActionListener(this);
          cButton.addActionListener(this);
          dButton.addActionListener(this);
          eButton.addActionListener(this);
          fButton.addActionListener(this);
          gButton.addActionListener(this);

          panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 133, 400));
          panel.setLayout(null);
          panel.add(startButton).setSize(new Dimension(buttonWidth, 30));
          panel.add(startLabel);

          startButton.setFocusable(false);

          startButton.setBounds(xOffset + 350, yOffset, buttonWidth, 30);
          startButton.setFont(new Font("Serif", Font.BOLD, 20));

     }

     public void frameSetup() {
          frame.add(panel, BorderLayout.CENTER);
          frame.pack();
     }

     public void panelSetup() {
          panel.setBounds(0, 0, 1920, 1080);
     }

     // called on button press
     @Override
     public void actionPerformed(ActionEvent e) {
          if (e.getSource() == startButton) {
               if (firstPress) {
                    changeStart();
                    addNoteButtons();
                    firstPress = false;
                    scoreLabel.setText(String.valueOf(score));
                    print("First Press");
               } else if (!firstPress) {
                    if (!submit) {
                         try {
                              start();
                         } catch (UnsupportedAudioFileException f) {
                              System.out.println(f);
                         } catch (IOException f) {
                              System.out.println(f);
                         } catch (LineUnavailableException f) {
                              System.out.println(f);
                         }
                         startButtonLabel = "Submit";
                         startButton.setText("Submit");
                         submit = true;
                    } else if (noteName == answer) {
                         score++;
                         System.out.println(String.valueOf(score));
                         startButtonLabel = "Play Note";
                         startButton.setText(startButtonLabel);
                         submit = false;
                         scoreLabel.setText(String.valueOf(score));
                         answer = null;
                    } else {
                         print(answer);
                         if (answer != null) {
                              gameOver = true;
                              print("Game Over");
                              removeButtons();
                              submit = false;
                         }
                    }
               }
          } else if (e.getSource() == aButton) {
               answer = "A";
               colour = "A";
               print(answer);
          } else if (e.getSource() == bButton) {
               answer = "B";
               colour = "B";
               print(answer);
          } else if (e.getSource() == cButton) {
               answer = "C";
               colour = "C";
               print(answer);
          } else if (e.getSource() == dButton) {
               answer = "D";
               colour = "D";
               print(answer);
          } else if (e.getSource() == eButton) {
               answer = "E";
               colour = "E";
               print(answer);
          } else if (e.getSource() == fButton) {
               answer = "F";
               answer = "F";
               print(answer);
          } else if (e.getSource() == gButton) {
               answer = "G";
               colour = "G";
               print(answer);
          }
     }

     /*
      * call something in game that changes the GUI to the ingame interface
      * learn how to play audio
      * add the answer buttons
      * Increase score for correct answers
      * END GAME for incorrect answers
      * add timer after score = 5 or smthn
      * increase diffuculties with larger range of notes
      * even harder difficulties with exact octive numbers
      * learn how to compile game like a normal game
      * compile game like normal game
      * GET BETTER AT MUSIC BY EAR
      * maybe make similar thing for sheet music, Who knows?
      */

     public void changeStart() {
          if (!gameHasStarted) {
               startButtonLabel = "Play Note";
               startButton.setText(startButtonLabel);
               gameHasStarted = true;
          }
     }

     public void addNoteButtons() {
          panel.add(aLabel).setSize(buttonWidth, 30);
          panel.add(bLabel).setSize(buttonWidth, 30);
          panel.add(cLabel).setSize(buttonWidth, 30);
          panel.add(dLabel).setSize(buttonWidth, 30);
          panel.add(eLabel).setSize(buttonWidth, 30);
          panel.add(fLabel).setSize(buttonWidth, 30);
          panel.add(gLabel).setSize(buttonWidth, 30);

          aLabel.setBounds(labelOffset + xOffset + 25, 60, buttonWidth, 30);
          bLabel.setBounds(labelOffset + xOffset + 350, 60, buttonWidth, 30);
          cLabel.setBounds(labelOffset + xOffset + 675, 60, buttonWidth, 30);
          dLabel.setBounds(labelOffset + xOffset + 25 - 100, 120, buttonWidth, 30);
          eLabel.setBounds(labelOffset + xOffset + 350 - 100, 120, buttonWidth, 30);
          fLabel.setBounds(labelOffset + xOffset + 675 - 100, 120, buttonWidth, 30);
          gLabel.setBounds(labelOffset + xOffset + 1000 - 100, 120, buttonWidth, 30);

          panel.add(aButton).setBounds(xOffset + 25, yOffset + 60, buttonWidth, 30);
          panel.add(bButton).setBounds(xOffset + 350, yOffset + 60, buttonWidth, 30);
          panel.add(cButton).setBounds(xOffset + 675, yOffset + 60, buttonWidth, 30);
          panel.add(dButton).setBounds(xOffset + 25 - 137, yOffset + 120, buttonWidth, 30);
          panel.add(eButton).setBounds(xOffset + 350 - 137, yOffset + 120, buttonWidth, 30);
          panel.add(fButton).setBounds(xOffset + 675 - 137, yOffset + 120, buttonWidth, 30);
          panel.add(gButton).setBounds(xOffset + 1000 - 137, yOffset + 120, buttonWidth, 30);

          aButton.setFont(new Font("Serif", Font.BOLD, 20));
          bButton.setFont(new Font("Serif", Font.BOLD, 20));
          cButton.setFont(new Font("Serif", Font.BOLD, 20));
          dButton.setFont(new Font("Serif", Font.BOLD, 20));
          eButton.setFont(new Font("Serif", Font.BOLD, 20));
          fButton.setFont(new Font("Serif", Font.BOLD, 20));
          gButton.setFont(new Font("Serif", Font.BOLD, 20));

          panel.add(scoreLabel).setBounds(xOffset + -100, yOffset - 75, buttonWidth, 30);
          scoreLabel.setFont(new Font("Serif", Font.BOLD, 40));

     }

     public void removeButtons() {
          panel.remove(aButton);
          panel.remove(bButton);
          panel.remove(cButton);
          panel.remove(dButton);
          panel.remove(eButton);
          panel.remove(fButton);
          panel.remove(gButton);

          startButtonLabel = "Start";
          startButton.setText(startButtonLabel);

          panel.revalidate();
          panel.repaint();

          gameHasStarted = false;
          firstPress = true;

          if (highScore < score) {
               highScore = score;
          }

          score = 0;
          answer = null;

          scoreLabel.setText(String.valueOf(highScore));

          try {
               FileOutputStream fos = new FileOutputStream("save.dat");
               BufferedOutputStream bos = new BufferedOutputStream(fos);
               ObjectOutputStream oos = new ObjectOutputStream(bos);

               DataStorage dStorage = new DataStorage();

               dStorage.highScore = highScore;

               oos.writeObject(dStorage);
               oos.close();

          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     public void setCorrectAnswer() {
          System.out.println(noteName);
     }

     public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
          note = randNum(8);
          print(String.valueOf(note));

          if (note == 0) {
               noteName = "A";
          } else if (note == 1) {
               noteName = "B";
          } else if (note == 3) {
               noteName = "C";
          } else if (note == 4) {
               noteName = "D";
          } else if (note == 5) {
               noteName = "E";
          } else if (note == 6) {
               noteName = "F";
          } else if (note == 7) {
               noteName = "G";
          }

          System.out.println("Started Succesfuly");
          File file = new File("notes/" + noteName + "3.wav");
          AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
          Clip clip = AudioSystem.getClip();

          clip.open(audioStream);

          clip.start();

          print("Playing Sound: " + noteName);

     }

     public void playNote() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
          try {
               start();
          } catch (UnsupportedAudioFileException e) {
               System.out.println(e);
          } catch (IOException e) {
               System.out.println(e);
          } catch (LineUnavailableException e) {
               System.out.println(e);
          }

          print("Playing Sound: " + noteName);
     }

     public int randNum(int limit) {
          Random rand = new Random();
          int upperbound = limit;
          int int_random = rand.nextInt(upperbound);
          return int_random;
     }

     public void print(String text) {
          System.out.println(text);
     }


     public void changeButtonColour() {
          while (gameHasStarted) {
               if (colour == "A") {
                    change("A");
               } else if (colour == "A") {
                    change("B");
               } else if (colour == "A") {
                    change("C");
               } else if (colour == "A") {
                    change("D");
               } else if (colour == "A") {
                    change("E");
               } else if (colour == "A") {
                    change("F");
               } else if (colour == "A") {
                    change("G");
               }
          }
     }

     public void change(String b) {

     }
}
