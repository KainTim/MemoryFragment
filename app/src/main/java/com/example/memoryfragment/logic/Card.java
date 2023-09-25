package com.example.memoryfragment.logic;

public class Card {
  private boolean shown;
  private boolean permashown;
  private String imgSource;

  public boolean isShown() {
    return shown;
  }

  public void setShown(boolean shown) {
    this.shown = shown;
  }

  public boolean isPermashown() {
    return permashown;
  }

  public void setPermashown(boolean permashown) {
    this.permashown = permashown;
  }

  public String getImgSource() {
    return imgSource;
  }

  public Card(String imgSource) {
    shown=false;
    permashown=false;
    this.imgSource = imgSource;
  }
}
