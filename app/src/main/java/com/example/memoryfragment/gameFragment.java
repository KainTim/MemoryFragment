package com.example.memoryfragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.memoryfragment.databinding.FragmentGameBinding;
import com.example.memoryfragment.logic.FourInARowLogic;

public class gameFragment extends Fragment implements View.OnClickListener {
  private FragmentGameBinding binding;
  private Button[][] buttons = new Button[4][4];
  private FourInARowLogic logic;
  private TextView title;
  private Button startButton;
  private boolean returnHome;
  private MainViewModel viewModel;

  public gameFragment() {
    // Required empty public constructor
  }

  public static gameFragment newInstance() {
    gameFragment fragment = new gameFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    binding = FragmentGameBinding.inflate(inflater,container,false);
    buttons[0][0] = binding.button2;
    buttons[0][1] = binding.button3;
    buttons[0][2] = binding.button4;
    buttons[0][3] = binding.button5;

    buttons[1][0] = binding.button6;
    buttons[1][1] = binding.button7;
    buttons[1][2] = binding.button8;
    buttons[1][3] = binding.button9;

    buttons[2][0] = binding.button10;
    buttons[2][1] = binding.button11;
    buttons[2][2] = binding.button12;
    buttons[2][3] = binding.button13;

    buttons[3][0] = binding.button14;
    buttons[3][1] = binding.button15;
    buttons[3][2] = binding.button16;
    buttons[3][3] = binding.button17;

    title= binding.tvTitle;
    startButton = binding.start;
    startButton.setOnClickListener(this);
    resetBoard();
    return binding.getRoot();
  }

  @Override
  public void onClick(View v) {
    if (v.getId()==R.id.button2){
      clickedButton(0,0);

    } else if (v.getId()==R.id.button3) {
      clickedButton(0,1);

    }else if (v.getId()==R.id.button4) {
      clickedButton(0,2);

    }else if (v.getId()==R.id.button5) {
      clickedButton(0,3);

    }

    else if (v.getId()==R.id.button6) {
      clickedButton(1,0);

    }else if (v.getId()==R.id.button7) {
      clickedButton(1,1);

    }else if (v.getId()==R.id.button8) {
      clickedButton(1,2);

    }else if (v.getId()==R.id.button9) {
      clickedButton(1,3);

    }

    else if (v.getId()==R.id.button10) {
      clickedButton(2,0);

    }else if (v.getId()==R.id.button11) {
      clickedButton(2,1);

    }else if (v.getId()==R.id.button12) {
      clickedButton(2,2);

    }else if (v.getId()==R.id.button13) {
      clickedButton(2,3);

    }

    else if (v.getId()==R.id.button14) {
      clickedButton(3,0);

    }else if (v.getId()==R.id.button15) {
      clickedButton(3,1);

    }else if (v.getId()==R.id.button16) {
      clickedButton(3,2);

    }else if (v.getId()==R.id.button17) {
      clickedButton(3,3);

    }else if (v.getId()==startButton.getId()) {
      if (returnHome){
        resetBoard();
        viewModel.showWelcomeScreen();
      }
      else{
        resetBoard();
      }
    }
  }
  public void clickedButton(int x, int y){
    logic.spaceClicked(x,y);
    if (logic.isPlayer1()){
      buttons[x][y].setBackgroundColor(Color.RED);
      buttons[x][y].setText(R.string.player1sym);
      title.setText(R.string.player1);
    }else{
      buttons[x][y].setBackgroundColor(Color.BLUE);
      buttons[x][y].setText(R.string.player2sym);
      title.setText(R.string.player2);
    }
    buttons[x][y].setEnabled(false);
    checkWin(x,y);
  }

  private void checkWin(int x, int y) {
    int checkWin = logic.checkWin();
    if (checkWin==0){
      if (logic.isFull()){
        title.setText(R.string.tie);
        title.setTextColor(Color.WHITE);
      }else return;
    } else if (checkWin == 1) {
      title.setText(R.string.player1win);
      title.setTextColor(Color.BLUE);
    } else if (checkWin == 2) {
      title.setText(R.string.player2win);
      title.setTextColor(Color.RED);
    }
    startButton.setText(R.string.returnHome);
    returnHome = true;
    for (Button[] row : buttons) {
      for (Button button : row) {
        button.setOnClickListener(null);
      }
    }
  }

  public void resetBoard(){
    returnHome = false;
    logic= new FourInARowLogic(4,4);
    for (Button[] row : buttons) {
      for (Button button : row) {
        button.setOnClickListener(this);
        button.setText("");
        button.setEnabled(true);
        button.setBackgroundColor(Color.GRAY);
      }
    }
    title.setText(logic.isPlayer1()? R.string.player1:R.string.player2);
    logic.resetBoard();
  }
}