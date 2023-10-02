package com.example.memoryfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
  public static final  int SHOW_MEMORY = 1;
  public static final  int SHOW_TIC_TAC_TOE = 2;
  public static final  int SHOW_WELCOME_SCREEN = 3;
  private MutableLiveData<Integer> _state = new MutableLiveData<>(SHOW_WELCOME_SCREEN);
  public LiveData<Integer> state =_state;

  public void showMemory(){
    _state.postValue(SHOW_MEMORY);
  }
  public void showTicTacToe(){
    _state.postValue(SHOW_TIC_TAC_TOE);
  }
  public void showWelcomeScreen(){
    _state.postValue(SHOW_WELCOME_SCREEN);
  }
}
