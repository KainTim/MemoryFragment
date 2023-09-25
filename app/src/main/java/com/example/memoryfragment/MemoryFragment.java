package com.example.memoryfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memoryfragment.databinding.FragmentBlankBinding;
import com.example.memoryfragment.logic.MemoryLogic;

import java.lang.reflect.Field;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoryFragment extends Fragment implements View.OnClickListener {

  private FragmentBlankBinding binding;
  private ImageView[][] cards;
  MemoryLogic logic;
  TextView title;
  public MemoryFragment() {
    // Required empty public constructor
  }

  public static MemoryFragment newInstance() {
    MemoryFragment fragment = new MemoryFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentBlankBinding.inflate(inflater,container,false);
// Initialize Card Array
    cards =  new ImageView[4][4];
    cards[0][0] = binding.imageView2;
    cards[0][1] = binding.imageView3;
    cards[0][2] = binding.imageView4;
    cards[0][3] = binding.imageView5;
    cards[1][0] = binding.imageView6;
    cards[1][1] = binding.imageView7;
    cards[1][2] = binding.imageView8;
    cards[1][3] = binding.imageView9;
    cards[2][0] = binding.imageView10;
    cards[2][1] = binding.imageView11;
    cards[2][2] = binding.imageView12;
    cards[2][3] = binding.imageView13;
    cards[3][0] = binding.imageView14;
    cards[3][1] = binding.imageView15;
    cards[3][2] = binding.imageView16;
    cards[3][3] = binding.imageView17;
    for (ImageView[] row : cards) {
        for (ImageView card : row) {
            card.setOnClickListener(this);
            card.setImageResource(R.drawable.ic_launcher_background);
        }
    }
    title = binding.textView;
    logic = new MemoryLogic();
    title.setText(String.format("Score: %d",logic.getScore()));
    return binding.getRoot();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onClick(View v) {
    if (v.getId()==cards[0][0].getId()){
      showCard(0,0);

    }
    if (v.getId()==cards[0][1].getId()){
      showCard(0,1);

    }
    if (v.getId()==cards[0][2].getId()){
      showCard(0,2);

    }
    if (v.getId()==cards[0][3].getId()){
      showCard(0,3);

    }
    if (v.getId()==cards[1][0].getId()){
      showCard(1,0);

    }
    if (v.getId()==cards[1][1].getId()){
      showCard(1,1);

    }
    if (v.getId()==cards[1][2].getId()){
      showCard(1,2);

    }
    if (v.getId()==cards[1][3].getId()){
      showCard(1,3);

    }
    if (v.getId()==cards[2][0].getId()){
      showCard(2,0);

    }
    if (v.getId()==cards[2][1].getId()){
      showCard(2,1);

    }
    if (v.getId()==cards[2][2].getId()){
      showCard(2,2);

    }
    if (v.getId()==cards[2][3].getId()){
      showCard(2,3);

    }
    if (v.getId()==cards[3][0].getId()){
      showCard(3,0);

    }
    if (v.getId()==cards[3][1].getId()){
      showCard(3,1);

    }
    if (v.getId()==cards[3][2].getId()){
      showCard(3,2);

    }
    if (v.getId()==cards[3][3].getId()){
      showCard(3,3);

    }
  }
//https://stackoverflow.com/questions/15874117/how-to-set-delay-in-android
  private void showCard(int x, int y) {
    //int result;
    //int[] otherShownCard;
    //if ((result=logic.showCard(x,y))==-1){
    //  otherShownCard = logic.getOtherShownCard(x, y);
    //  timer(x,y,otherShownCard);
    //  otherShownCard = logic.getOtherShownCard(x, y);
    //}
    //cards[x][y].setOnClickListener(null);
    //int drawableId = getDrawableId(x,y);
    //cards[x][y].setImageResource(drawableId);
    //if(result<=2) return;
    //Log.d("UI","got to Here1");
    //Log.d("UI",String.format("%d",result));
    //otherShownCard = logic.getOtherShownCard(x, y);
    //if (logic.isPair(x,y,otherShownCard[0],otherShownCard[1])){
    //  logic.permaShowCard(x, y);
    //  logic.permaShowCard(otherShownCard[0],otherShownCard[1]);
    //  return;
    //}
    //timer(x, y, otherShownCard);
    if (logic.getShownCount()>=2){
      return;
    }
    boolean isPair = false;
    logic.showCard(x, y);
    title.setText(String.format("Score: %d",logic.getScore()));
    cards[x][y].setOnClickListener(null);
    int drawableId = getDrawableId(x,y);
    cards[x][y].setImageResource(drawableId);
    int[] otherShownCard = logic.getOtherShownCard(x, y);
    if (logic.getShownCount()==2){
      if (logic.isPair(x,y, otherShownCard[0], otherShownCard[1])){
        isPair = true;
        logic.permaShowCard(x, y);
        logic.permaShowCard(otherShownCard[0],otherShownCard[1]);
        title.setText(String.format("Score: %d",logic.getScore()));
      }
      if (!isPair){
        timer(x,y, otherShownCard);
      }
    }
    if (logic.isDone()){
      title.setText(R.string.win);
    }

  }

  private void timer(int x, int y, int[] otherShownCard) {
    CountDownTimer timer = new CountDownTimer(750, 1000) {
      public void onFinish() {
        Log.d("UI","got to Here2");
        cards[otherShownCard[0]][otherShownCard[1]].setImageResource(R.drawable.ic_launcher_background);
        setOnClickListener(otherShownCard[0], otherShownCard[1]);
        logic.hideCard(otherShownCard[0], otherShownCard[1]);

        Log.d("UI","hid other Card");
        cards[x][y].setImageResource(R.drawable.ic_launcher_background);
        setOnClickListener(x, y);
        logic.hideCard(x, y);
        Log.d("UI","hid this card");
      }

      public void onTick(long millisUntilFinished) {
      }
    }.start();
  }


  //https://stackoverflow.com/questions/54735793/how-to-get-the-id-of-an-image-from-a-string-array
  private int getDrawableId(int x, int y) {
    try {
      Class res = R.drawable.class;
      Field field = res.getField(logic.getImageResourceStringFromCoordinate(x,y));
      int drawableId = field.getInt(null);
      return drawableId;
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  public void setOnClickListener(int x,int y){
    cards[x][y].setOnClickListener(this);
  }
}