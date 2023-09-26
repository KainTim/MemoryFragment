package com.example.memoryfragment.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MemoryLogic {
  private Card[][] board = new Card[4][4];
  private Map<String, Integer> imageStringsMap;
  private List<String> imageString;


  public MemoryLogic() {
    resetLogic();
  }

  private Card generateCard() {
    Random random = new Random();
    Collections.shuffle(imageString);
    Card card;
    while (true) {
      int randInt = random.nextInt(imageString.size());
      switch (imageStringsMap.get(imageString.get(randInt))) {
        case 0:
          card = new Card(imageString.get(randInt));
          imageStringsMap.put(imageString.get(randInt), 1);
          return card;
        case 1:
          card = new Card(imageString.get(randInt));
          imageStringsMap.put(imageString.get(randInt), 2);
          return card;
      }
    }
  }

  public String getImageResourceStringFromCoordinate(int x, int y) {
    return board[x][y].getImgSource();
  }

  public void showCard(int x, int y) {
    board[x][y].setShown(true);
    Log.d("Logic", String.format("%d",getShownCount()));
  }

  public void hideCard(int x, int y) {
    board[x][y].setShown(false);
  }
  public int getShownCount(){
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if(board[i][j].isShown()) count++;
      }
    }
    return count;
  }
  public int[]getOtherShownCard(int x, int y){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if(board[i][j].isShown()&&(i!=x||j!=y)) return new int[]{i,j};
      }
    }
    return null;
  }

  public boolean isPair(int x, int y, int x2, int y2) {
    if (board[x][y].getImgSource().equals(board[x2][y2].getImgSource())&&board[x][y].isShown()&&board[x2][y2].isShown()){
      if (x==x2&&y==y2){
        return false;
      }
      Log.d("Logic", String.format("had a pair, %d %d %d %d",x,y,x2,y2));
      return true;
    }
    else return false;
  }
  public void permaShowCard(int x,int y){
    board[x][y].setPermashown(true);
    board[x][y].setShown(false);
  }
  public boolean isDone(){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (!board[i][j].isPermashown()) return false;
      }
    }
    return true;
  }
  public int getScore(){
    int score = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j].isPermashown()) score++;
      }
    }return score;
  }
  public void resetLogic(){
    imageStringsMap = new HashMap<>();
    imageStringsMap.put("img_13", 0);//notWendt
    imageStringsMap.put("img_9", 0);
    imageStringsMap.put("img_12", 0);
    imageStringsMap.put("img_11", 0);
    imageStringsMap.put("img_7", 0);
    imageStringsMap.put("img_10", 0);
    imageStringsMap.put("img_6", 0);
    imageStringsMap.put("img_8", 0);
    imageString = new ArrayList<>();
    imageString.add("img_13");
    imageString.add("img_9");
    imageString.add("img_12");
    imageString.add("img_11");
    imageString.add("img_7");
    imageString.add("img_10");
    imageString.add("img_6");
    imageString.add("img_8");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = generateCard();
      }
    }
  }
}
