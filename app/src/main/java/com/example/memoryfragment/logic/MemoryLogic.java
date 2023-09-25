package com.example.memoryfragment.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MemoryLogic {
  private int score;
  private Card[][] board = new Card[4][4];
  private Map<String,Integer> imageStringsMap;
  private List<String> imageString;

  {
    imageStringsMap = new  HashMap<>();
    imageStringsMap.put("img",0);
    imageStringsMap.put("img2",0);
    imageStringsMap.put("mug",0);
    imageStringsMap.put("img_1",0);
    imageStringsMap.put("img_7",0);
    imageStringsMap.put("img_4",0);
    imageStringsMap.put("img_5",0);
    imageStringsMap.put("img_6",0);
    imageString = new ArrayList<>();
    imageString.add("img");
    imageString.add("img2");
    imageString.add("mug");
    imageString.add("img_1");
    imageString.add("img_7");
    imageString.add("img_4");
    imageString.add("img_5");
    imageString.add("img_6");
  }

  public MemoryLogic() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = generateCard();
      }
    }
    score = 0;
  }

  private Card generateCard(){
    Random random = new Random();
    Card card;
    while (true){
      int randInt = random.nextInt(imageString.size());
      switch (imageStringsMap.get(imageString.get(randInt))) {
        case 0:
          card = new Card(imageString.get(randInt));
          imageStringsMap.put(imageString.get(randInt),1);
          return card;
        case 1:
          card = new Card(imageString.get(randInt));
          imageStringsMap.put(imageString.get(randInt),2);
          return card;
      }
    }
  }
}
