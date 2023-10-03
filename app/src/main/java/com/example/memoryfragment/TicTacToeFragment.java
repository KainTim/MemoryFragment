package com.example.memoryfragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.memoryfragment.databinding.FragmentTicTacToeBinding;
import com.example.memoryfragment.logic.TicTacToeLogic;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {
  public boolean returnHome = false;
  private MainViewModel viewModel;
  private FragmentTicTacToeBinding binding;
  private TextView tvTitle;
  private Button btOK;
  private TicTacToeLogic logic;
  private Button startButton;
  private Button[][] board = new Button[3][3];
  public TicTacToeFragment() {
    // Required empty public constructor
  }
  public static TicTacToeFragment newInstance() {
    TicTacToeFragment fragment = new TicTacToeFragment();
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
    // Inflate the layout for this fragment
    binding = FragmentTicTacToeBinding.inflate(inflater,container,false);
    tvTitle = binding.tvTitle;
    //Set up board
    viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    board[0][0] = (binding.button2);
    board[0][1] = (binding.button3);
    board[0][2] = (binding.button4);
    board[1][0] = (binding.button5);
    board[1][1] = (binding.button6);
    board[1][2] = (binding.button7);
    board[2][0] = (binding.button8);
    board[2][1] = (binding.button9);
    board[2][2] = (binding.button10);
    for(Button[] row :board) {
      for (Button button : row) {
        button.setOnClickListener(this);
        button.setBackgroundColor(Color.rgb(90, 90, 90));
        button.setText("");
        button.setTextSize(50);
      }
    }
    startButton = binding.start;
    startButton.setOnClickListener(this);

    startButton.setBackgroundColor(Color.rgb(241, 81, 82));
    //Initialize Logic
    logic = new TicTacToeLogic(3,3);
    tvTitle.setText(logic.isPlayer1()?R.string.player1 : R.string.player2);
    return binding.getRoot();
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.start) {
      if (returnHome){
        viewModel.showWelcomeScreen();
      }
      logic = new TicTacToeLogic(3,3);      tvTitle.setText(logic.isPlayer1()?R.string.player1 : R.string.player2);
      resetBoard();
      return;
    }

    if (v.getId() == R.id.button2) {
      //Put corresponding Symbol in Button
      board[0][0].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      //Send coordinates to logic
      logic.spaceClicked(0, 0);
      //Set Title to the player that is now playing
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      //disable button
      board[0][0].setEnabled(false);
    }
    if (v.getId() == R.id.button3) {
      board[0][1].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(0, 1);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[0][1].setEnabled(false);
    }
    if (v.getId() == R.id.button4) {
      board[0][2].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(0, 2);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[0][2].setEnabled(false);
    }
    if (v.getId() == R.id.button5) {
      board[1][0].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(1, 0);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[1][0].setEnabled(false);
    }
    if (v.getId() == R.id.button6) {
      board[1][1].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(1, 1);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[1][1].setEnabled(false);
    }
    if (v.getId() == R.id.button7) {
      board[1][2].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(1, 2);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[1][2].setEnabled(false);
    }
    if (v.getId() == R.id.button8) {
      board[2][0].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(2, 0);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[2][0].setEnabled(false);
    }
    if (v.getId() == R.id.button9) {
      board[2][1].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(2, 1);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[2][1].setEnabled(false);
    }
    if (v.getId() == R.id.button10) {
      board[2][2].setText(logic.isPlayer1() ? R.string.player1sym : R.string.player2sym);
      logic.spaceClicked(2, 2);
      tvTitle.setText(logic.isPlayer1() ? R.string.player1 : R.string.player2);
      board[2][2].setEnabled(false);
    }
    int[] lastPlace = logic.getLastPlace();
    board[lastPlace[0]][lastPlace[1]].setBackgroundColor(logic.isPlayer1() ? Color.rgb(0, 180, 216) : Color.rgb(156, 252, 151));
    board[lastPlace[0]][lastPlace[1]].setTextColor(Color.WHITE);

    if (logic.isFull()) {
      startButton.setText(R.string.returnHome);
      returnHome = true;
      tvTitle.setText(R.string.tie);
      tvTitle.setTextColor(Color.rgb(241, 81, 82));
    }

    if (logic.checkWin() == 0) return;
    if (logic.checkWin() == 1) {
      tvTitle.setText(R.string.player1win);
      tvTitle.setTextColor(Color.rgb(156, 252, 151));
    }
    if (logic.checkWin() == 2) {
      tvTitle.setText(R.string.player2win);
      tvTitle.setTextColor(Color.rgb(0, 180, 216));
    }
    startButton.setText(R.string.returnHome);
    returnHome = true;
    for (Button[] row : board) {
      for (Button button : row) {
        button.setEnabled(false);
      }
    }
  }
  public void resetBoard(){
    for (Button[] row : board) {
      for (Button button : row) {
        button.setEnabled(true);
        button.setBackgroundColor(Color.rgb(30, 85, 92));
        button.setText("");
      }
    }
    logic.resetBoard();
    tvTitle.setTextColor(Color.BLACK);
    startButton.setText(R.string.reset);
  }
}