package Craps;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Craps extends JApplet implements ActionListener {

	final int WON = 0, LOST = 1, CONTINUE = 2;
    
    boolean firstRoll = true;
    int sumOfDiece = 0;
    int myPoint = 0;
    int money = 100;
    int gameStatus = CONTINUE;
    
    JLabel diece1Label, diece2Label, sumLabel, pointLabel, playerMoneyLabel;
    JTextField diece1Field, diece2Field, sumField, pointField, playerMoneyTextF;
    JButton rollButton;
    
    @Override
    public void init() {
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        
        playerMoneyLabel = new JLabel("Players Money is");
        container.add(playerMoneyLabel);
        playerMoneyTextF = new JTextField(10);
        playerMoneyTextF.setEditable(false);
        playerMoneyTextF.setText(Integer.toString(money));
        container.add(playerMoneyTextF);
        
        diece1Label = new JLabel("Diece 1");
        container.add(diece1Label);
        diece1Field = new JTextField(10);
        diece1Field.setEditable(false);
        container.add(diece1Field);
        
        diece2Label = new JLabel("Diece 2");
        container.add(diece2Label);
        diece2Field = new JTextField(10);
        diece2Field.setEditable(false);
        container.add(diece2Field);
        
        sumLabel = new JLabel("Sum is");
        container.add(sumLabel);
        sumField = new JTextField(10);
        sumField.setEditable(false);
        container.add(sumField);
        
        pointLabel = new JLabel("Point is");
        container.add(pointLabel);
        pointField = new JTextField(10);
        pointField.setEditable(false);
        container.add(pointField);
        
        rollButton = new JButton("Roll Diece");
        rollButton.addActionListener(this);
        container.add(rollButton);
    }
    
    //ActionListener implement

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // first roll of diece
        if(firstRoll) {
            sumOfDiece = rollDiece();
            
            switch (sumOfDiece) {
                
                // win on first roll
                case 7: case 11:
                    gameStatus = WON;
                    pointField.setText("");
                    playerWin();
                    break;
                    
                // lose on first roll
                case 2: case 3: case 12:
                    gameStatus = LOST;
                    pointField.setText("");
                    playerLose();
                    break;
                    
                default:
                    gameStatus = CONTINUE;
                    myPoint = sumOfDiece;
                    pointField.setText(Integer.toString(myPoint));
                    firstRoll = false;
                    break;
            }
        }
        
        else {
            sumOfDiece = rollDiece();
            
            if (sumOfDiece == myPoint) {
                gameStatus = WON;
                playerWin();
            } else if (sumOfDiece == 7){
                gameStatus = LOST;
                playerLose();
            } else {
                // do nothing
            }
        }
        
        displayMessage();
        
    }
    
    public int rollDiece() {
        int diece1, diece2, sum;
        
        diece1 = 1 + (int)(Math.random() * 6);
        diece2 = 1 + (int)(Math.random() * 6);
        
        sum = diece1 + diece2;
        
        diece1Field.setText(Integer.toString(diece1));
        diece2Field.setText(Integer.toString(diece2));
        sumField.setText(Integer.toString(sum));
        
        return sum;
    }
    
    public void displayMessage() {
        
        if (gameStatus == CONTINUE) {
            showStatus("Roll Again.");
            firstRoll = false;
        } else if (gameStatus == WON) {
            showStatus("Player wins. " + "Click roll Diece to Play again");
            firstRoll = true;
        } else {
            showStatus("Player Loses. " + "Click roll Diece to Play again");
            firstRoll = true;
        }
        
        
    }
    
    public boolean isPlayerAvailable() {
        
        if (money <= 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public void playerWin() {
        if (isPlayerAvailable()) {
            this.money += 10;
            playerMoneyTextF.setText(Integer.toString(this.money));
        } else {
            playerMoneyTextF.setText("You should have enough money");
        }
    }
    
    public void playerLose() {
        if (isPlayerAvailable()) {
            this.money -= 10;
            playerMoneyTextF.setText(Integer.toString(this.money));
        } else {
            playerMoneyTextF.setText("You should have enough money");
        }
    }   
}


