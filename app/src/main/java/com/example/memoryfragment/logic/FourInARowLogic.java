package com.example.memoryfragment.logic;

import android.util.Log;

public class FourInARowLogic {
  private boolean isPlayer1 = true;
  private int[][] playField;

  public FourInARowLogic(int sizeX, int sizeY) {
    playField = new int[sizeX][sizeY];
  }

  public int[] getLastPlace() {
    return lastPlace;
  }
  public boolean isFull(){
    for (int[] row : playField) {
        for (int num : row) {
            if (num == 0) return false;
        }
    }

    Log.d("GameLogic","Is Full");
    return true;
  }
  private final int[] lastPlace = new int[2];
  public int[] spaceClicked(int row, int column) {
    Log.d("FourInARow",String.format("%d,%d",row,column));
    for (int i = 0; i<playField.length;i++){
      if (playField[i][column]==0) row = i;
      Log.d("FourInARow",String.format("%d,%d",i,column));
    }

    lastPlace[0] =row;
    lastPlace[1] =column;
    playField[row][column] = isPlayer1?1:2;
    Log.d("GameLogic",String.format("set Row %d Column %d to Player %d",row,column,isPlayer1?1:2));
    Log.d("GameLogic",String.format("checkWin %d",checkWin()));
    isPlayer1 = !isPlayer1;
    return new int[]{row,column};
  }
  public int checkWin(){
    int tmpResults = 0;
    tmpResults = checkHorizontal(playField);
    if (tmpResults!=0) return tmpResults;
    tmpResults = checkVertical();
    if (tmpResults!=0) return tmpResults;
    tmpResults = checkBothDiagonals();
    if (tmpResults!=0) return tmpResults;
    return 0;
  }

  private int checkDiagonal(int[][] playField) {
    int equalcount = 0;
    for (int i = 1; i < playField.length; i++) {
      if (playField[i-1][i-1]==playField[i][i]&&playField[i][i]!=0){
        Log.i("GameLogic", String.format("Equal r1: %d c1: %d r2: %d c2: %d",i-1,i-1,i,i));
        equalcount++;
      }else{
        Log.i("GameLogic", String.format("Not Equal r1: %d c1: %d r2: %d c2: %d",i-1,i-1,i,i));
        break;
      }
    }
    if (equalcount>= playField.length-1){
      Log.i("GameLogic", String.format("win for Player %d",playField[0][0]));
      return playField[0][0];
    }
    return 0;
  }

  public int checkHorizontal(int[][] playField){
    for (int i = 0; i < playField.length; i++) {
      int equalcount = 0;
      for (int j = 1; j < playField[i].length; j++) {
        if (playField[i][j-1]==playField[i][j]&&playField[i][j]!=0){
          Log.i("GameLogic", String.format("Equal r1: %d c1: %d r2: %d c2: %d",i,j-1,i,j));
          equalcount++;
        }else {
          Log.i("GameLogic", String.format("Not Equal r1: %d c1: %d r2: %d c2: %d",i,j-1,i,j));
          break;
        }
      }
      if (equalcount>= playField[0].length-1){
        Log.i("GameLogic", String.format("win for Player %d",playField[i][0]));
        return playField[i][0];
      }
    }
    return 0;
  }
  public int checkVertical(){
    int[][] tmpfield = new int[playField.length][playField[0].length];
    for (int i = 0; i < playField.length; i++) {
      for (int j = 0; j < playField[0].length; j++) {
        tmpfield[j][i] = playField[i][j];
      }
    }
    return checkHorizontal(tmpfield);
  }
  public int checkBothDiagonals(){
    int[][] tmpfield = new int[playField.length][playField[0].length];
    for (int i = 0; i < playField.length; i++) {
      for (int j = 0; j < playField[0].length; j++) {
        tmpfield[i][playField.length-1-j] = playField[i][j];
        Log.e("GameLogic", String.format("regfield x: %d y: %d v: %d swapfield: x: %d y: %d v: %d",i,j,playField[i][j],j,i,tmpfield[j][i]));
      }
    }

    Log.e("GameLogic", String.format("regDiag: %d swapdiag: %d",checkDiagonal(playField),checkDiagonal(tmpfield)));
    int tmpresult = 0;
    tmpresult = checkDiagonal(playField);
    if (tmpresult!=0) return tmpresult;
    tmpresult = checkDiagonal(tmpfield);
    return tmpresult;
  }
  public boolean isPlayer1() {
    return isPlayer1;
  }

  public void resetBoard() {
    isPlayer1 = true;
    playField = new int[playField.length][playField[0].length];

  }
}
