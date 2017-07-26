package com.pshah.ghostGame.models;

public class GhostGameModel {
  private boolean computerWin;
  private boolean computerBluffing;
  
  public GhostGameModel(){
    computerWin = false;
    computerBluffing = false;
  }
  
  public boolean isComputerWin() {
    return computerWin;
  }
  
  public void setComputerWin(boolean computerWin) {
    this.computerWin = computerWin;
  }
  
  public boolean isComputerBluffing() {
    return computerBluffing;
  }
  
  public void setComputerBluffing(boolean computerBluffing) {
    this.computerBluffing = computerBluffing;
  }

}
