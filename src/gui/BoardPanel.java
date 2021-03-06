/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author admin
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import Gui.ICommon;
import Gui.ITrans;
import logic.Board;
import logic.Square;
 
public class BoardPanel extends JPanel implements ICommon {
  private static final long serialVersionUID = -6403941308246651773L;
  private Label[][] lbSquare;
  private ITrans listener;
  private int numSquareClosed;
 
  public BoardPanel() {
    initComp();
    addComp();
    addEvent();
  }
 
  @Override
  public void initComp() {
    setLayout(new GridLayout(Board.NUM_ROWS, Board.NUM_COLUMNS));
  }
 
  @Override
  public void addComp() {
    Border border = BorderFactory.createLineBorder(Color.gray, 1);
    lbSquare = new Label[Board.NUM_ROWS][Board.NUM_COLUMNS];
    for (int i = 0; i < lbSquare.length; i++) {
      for (int j = 0; j < lbSquare[0].length; j++) {
        lbSquare[i][j] = new Label();
        lbSquare[i][j].setOpaque(true);
        //lbSquare[i][j].setBackground(new Color(242, 242, 242));
        lbSquare[i][j].setBackground(new Color(25, 25, 25));
        lbSquare[i][j].setBorder(border);
        lbSquare[i][j].setHorizontalAlignment(JLabel.CENTER);
        lbSquare[i][j].setVerticalAlignment(JLabel.CENTER);
        add(lbSquare[i][j]);
      }
    }
  }
 
  @Override
  public void addEvent() {
    for (int i = 0; i < lbSquare.length; i++) {
      for (int j = 0; j < lbSquare[0].length; j++) {
        lbSquare[i][j].x = i;
        lbSquare[i][j].y = j;
        lbSquare[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            Label label = (Label) e.getComponent();
            if (e.getButton() == MouseEvent.BUTTON1) {
              listener.play(label.x, label.y);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
              listener.target(label.x, label.y);
            }
          }
        });
      }
    }
  }
 
  public void addListener(ITrans event) {
    listener = event;
  }
 
  // c???p nh???t hi???n th???
  public void updateBoard() {
    Font font = new Font("VNI", Font.PLAIN, 20);
    numSquareClosed = 0;
    Square[][] listSquare = listener.getListSquare();
    for (int i = 0; i < listSquare.length; i++) {
      for (int j = 0; j < listSquare[0].length; j++) {
        lbSquare[i][j].setFont(font);
        if (!listSquare[i][j].isOpen()) {
          //lbSquare[i][j].setBackground(new Color(242, 242, 242));
          lbSquare[i][j].setBackground(new Color(25, 25, 25));
          //lbSquare[i][j].setForeground(Color.black);
          lbSquare[i][j].setForeground(Color.gray);
          numSquareClosed++;
          if (!listSquare[i][j].isTarget()) {
            lbSquare[i][j].setText("");
          } else {
            lbSquare[i][j].setText("\uD83D\uDEA9"); // ki tu 'flag'
            //lbSquare[i][j].setBackground(new Color(150, 150, 150)); // m???i th??m
          }
        } else {
          if (listSquare[i][j].isHasMine()) {
            lbSquare[i][j].setText("\uD83D\uDCA3"); // ki tu 'bomb'
            //lbSquare[i][j].setBackground(new Color(250, 150, 250)); // m???i th??m
          } else {
            int numMineAround = listSquare[i][j].getNumMineAround();
            if (numMineAround == 0) {
              lbSquare[i][j].setText("");
            } else {
              lbSquare[i][j].setText(numMineAround + "");
              // ?????t m??u s??? cho d??? nh??n
              switch (numMineAround) {
              case 1:
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 2:
                //lbSquare[i][j].setForeground(new Color(255, 0, 0));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 3:
                //lbSquare[i][j].setForeground(new Color(0, 204, 0));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 4:
                //lbSquare[i][j].setForeground(new Color(102, 0, 255));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 5:
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 6:
                //lbSquare[i][j].setForeground(new Color(255, 0, 0));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 7:
                //lbSquare[i][j].setForeground(new Color(0, 204, 0));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              case 8:
                //lbSquare[i][j].setForeground(new Color(102, 0, 255));
                lbSquare[i][j].setForeground(new Color(128, 128, 128));
                break;
              }
            }
          }
          //lbSquare[i][j].setBackground(Color.white);
          lbSquare[i][j].setBackground(Color.black);
        }
      }
    }
  }
 
  // t???o class con ????? m??? r???ng thu???c t??nh cho l???p JLabel
  // gi??p l??u ???????c ch??? s??? h??ng, c???t c???a label ???? ??? trong GridLayout
  // v?? ko th??? truy???n gi?? tr??? i, j v??o b??n trong ph????ng th???c addMouseListener
  private class Label extends JLabel {
    private static final long serialVersionUID = 6099893043079770073L;
    private int x;
    private int y;
  }
   
  public int getNumSquareClosed() {
    return numSquareClosed;
  }
}