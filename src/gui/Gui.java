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
import Gui.ITrans;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import logic.Board;
import logic.Square;
 
public class Gui extends JFrame implements ICommon, ITrans {
  private static final long serialVersionUID = -5479701518838741039L;
  private static final String TITLE = "MineSweeper";
  public static final int FRAME_WIDTH = 730;
  public static final int FRAME_HEIGHT = 600;
  private BoardPanel boardPanel;
  private ControlPanel controlPanel;
  private Board board;
 
  public Gui() {
    board = new Board();
    initComp();
    addComp();
    addEvent();
  }
 
  
  public void initComp() {
    setTitle(TITLE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setLayout(null);
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  
  public void addComp() {
    boardPanel = new BoardPanel();
    boardPanel.setBounds(10, 60, 700, 500);
    add(boardPanel);
    boardPanel.addListener((ITrans) this);
 
    controlPanel = new ControlPanel();
    controlPanel.setBounds(10, 0, 700, 60);
    add(controlPanel);
    controlPanel.addListener(this);
  }
 
  
  public void addEvent() {
    WindowListener wd = new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int kq = JOptionPane.showConfirmDialog(Gui.this, "Bạn có muốn thoát không?",
         "Thông báo", JOptionPane.YES_NO_OPTION);
        if (kq == JOptionPane.YES_OPTION) {
          dispose();
        }
      }
    };
    addWindowListener(wd);
  }
 
   public Square[][] getListSquare() {
    return board.getListSquare();
  }

  public void play(int x, int y) {
    boolean check = board.play(x, y);
    if (!check) { board.showAllSquares(); }
    boardPanel.updateBoard();
    // cập nhật số ô chưa mở vào controlPanel
    int numSquareClosed = boardPanel.getNumSquareClosed();
    controlPanel.updateStatus(numSquareClosed);
  }
 
 
  public void target(int x, int y) {
    board.target(x, y);
    boardPanel.updateBoard();
  }
 
  
  public void restart() {
    board = new Board();
    boardPanel.updateBoard();
  }
}
