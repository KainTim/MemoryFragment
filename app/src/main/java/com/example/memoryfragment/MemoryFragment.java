package com.example.memoryfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.memoryfragment.databinding.FragmentBlankBinding;
import com.example.memoryfragment.logic.MemoryLogic;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoryFragment extends Fragment implements View.OnClickListener {

  private FragmentBlankBinding binding;
  private ImageView[][] cards;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MemoryFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   * @return A new instance of fragment BlankFragment.
   */
  // TODO: Rename and change types and number of parameters
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
        }
    }
    MemoryLogic logic = new MemoryLogic();
    return binding.getRoot();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onClick(View v) {

  }
}