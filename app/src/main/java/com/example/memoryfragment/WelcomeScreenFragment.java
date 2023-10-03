package com.example.memoryfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memoryfragment.databinding.FragmentBlankBinding;
import com.example.memoryfragment.databinding.FragmentWelcomeScreenBinding;

public class WelcomeScreenFragment extends Fragment implements View.OnClickListener {
  private MainViewModel viewModel;
  private @NonNull FragmentWelcomeScreenBinding binding;
  public WelcomeScreenFragment() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static WelcomeScreenFragment newInstance() {
    WelcomeScreenFragment fragment = new WelcomeScreenFragment();
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
    binding = FragmentWelcomeScreenBinding.inflate(inflater,container,false);
    viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    binding.button2.setOnClickListener(this);
    binding.button3.setOnClickListener(this);
    binding.button4.setOnClickListener(this);
    return binding.getRoot();
  }

  @Override
  public void onClick(View v) {
    if (v.getId()==binding.button2.getId()){
      viewModel.showTicTacToe();
    }else if (v.getId()==binding.button3.getId()){
      viewModel.showMemory();
    }else if (v.getId()==binding.button4.getId()){
      viewModel.Show4InARow();
    }
  }
}