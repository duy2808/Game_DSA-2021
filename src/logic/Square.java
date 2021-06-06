/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author admin
 */
public class Square {
  private boolean isOpen;
  private boolean hasMine;
  private int numMineAround;
  private boolean isTarget;
 
  public Square() {
    isOpen = false;
    hasMine = false;
    isTarget = false;
  }
 
  public boolean isOpen() {
    return isOpen;
  }
  public void setOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }
  public boolean isHasMine() {
    return hasMine;
  }
  public void setHasMine(boolean hasMine) {
    this.hasMine = hasMine;
  }
  public int getNumMineAround() {
    return numMineAround;
  }
  public void setNumMineAround(int numMineAround) {
    this.numMineAround = numMineAround;
  }
  public boolean isTarget() {
    return isTarget;
  }
  public void setTarget(boolean isTarget) {
    this.isTarget = isTarget;
  }
}
