/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author admin
 */
import logic.Square;
 
public interface ITrans {
  Square[][] getListSquare();
 
  void play(int x, int y);
  void target(int x, int y);
  void restart();
}