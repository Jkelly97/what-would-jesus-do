package edu.cnm.deepdive.whatwouldjesusdo.controller.ui.bible;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * A fragment representing the Bible itself.
 */
public class BibleFragment extends Fragment {


  private static final String ARG_COLUMN_COUNT = "column-count";

  private int mColumnCount = 1;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
   * screen orientation changes).
   */
  public BibleFragment() {
  }


  public static BibleFragment newInstance(int columnCount) {
    BibleFragment fragment = new BibleFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_COLUMN_COUNT, columnCount);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
       ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_bible, container, false);
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
  }
}