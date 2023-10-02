package com.example.memoryfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.memoryfragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.state.observe(this,state->{
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      if (state == MainViewModel.SHOW_MEMORY){
        fragmentTransaction.replace(R.id.layout,MemoryFragment.newInstance());
      } else if (state == MainViewModel.SHOW_TIC_TAC_TOE) {
        fragmentTransaction.replace(R.id.layout,TicTacToeFragment.newInstance());
      } else if (state == MainViewModel.SHOW_WELCOME_SCREEN) {
        fragmentTransaction.replace(R.id.layout, WelcomeScreenFragment.newInstance());
      }
      fragmentTransaction.commit();
    });
  }
}