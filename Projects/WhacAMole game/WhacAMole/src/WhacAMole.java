import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhacAMole {
        int boardWidth= 600;
        int boardHeight= 650;

        JFrame frame = new JFrame("Mario: whac a mole");

        JLabel textLable = new JLabel();
        JPanel textPanel = new JPanel();
        JPanel boardPanel= new JPanel();

        JButton[] board = new JButton[9];
        ImageIcon plantIcon;
        ImageIcon pickachuIcon;

        JButton currPickachuTile;
        JButton currPlantTile;

        Random random = new Random();
        Timer setMoleTime;
        Timer setPlantTimer;
        int score=0;

        WhacAMole(){
            frame.setVisible(true);
            frame.setSize(boardWidth, boardHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            textLable.setFont(new Font("Arial", Font.PLAIN, 50));
            textLable.setHorizontalAlignment(JLabel.CENTER);
            textLable.setOpaque(true);
            textLable.setText("Score: 0");

            textPanel.setLayout(new BorderLayout());
            textPanel.add(textLable);
            frame.add(textPanel, BorderLayout.NORTH);

            boardPanel.setLayout(new GridLayout(3, 3));
           // boardPanel.setBackground(Color.blue);
            frame.add(boardPanel);
            
            //plantIcon = new ImageIcon(getClass().getResource("D:\\WhacAMole\\src\\pickachu.jpg"));

            Image plantImg= new ImageIcon(getClass().getResource("./plant.jpg")).getImage();
            plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

            Image pickachuImage= new ImageIcon(getClass().getResource("./pickachu.jpg")).getImage();
           pickachuIcon = new ImageIcon(pickachuImage.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            for(int i=0;i<9;i++){
                JButton tile= new JButton();
                board[i]=tile;
                boardPanel.add(tile);
                tile.setFocusable(false);
                // tile.setIcon(pickachuIcon);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton tile= (JButton) e.getSource();
                        if(tile == currPickachuTile){
                            score+=10;
                            textLable.setText("score: "+Integer.toString(score));
                        }
                        else if(tile == currPlantTile){
                            textLable.setText("Game Over: "+ Integer.toString(score));
                            setMoleTime.stop();
                            setPlantTimer.stop();
                            for(int i=0;i<9;i++){
                                board[i].setEnabled(false);
                            }
                        }
                    }
                });

            }

            setMoleTime = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(currPickachuTile != null){
                        currPickachuTile.setIcon(null);
                        currPickachuTile = null;
                    }
                    int num = random.nextInt(9);
                    JButton tile = board[num];

                    if(currPlantTile==tile) return;

                    currPickachuTile = tile;
                    currPickachuTile.setIcon(pickachuIcon);
                }
            });

            setPlantTimer = new Timer(1500, new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if( currPlantTile!= null){
                        currPlantTile.setIcon(null);
                        currPlantTile = null;
                    }

                    int num = random.nextInt(9);
                    JButton tile= board[num];
                    
                    if (currPickachuTile == tile) return;
                    currPlantTile= tile;
                    currPlantTile.setIcon(plantIcon);
                }
            });
            setMoleTime.start();
            setPlantTimer.start();
            frame.setVisible(true);
        }
    }
