package edu.cnm.deepdive.whatwouldjesusdo.controller.ui.bible;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.databinding.FragmentBibleBinding;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse.SearchData.Verse;
import edu.cnm.deepdive.whatwouldjesusdo.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * A fragment representing the Bible itself.
 */
public class BibleFragment extends Fragment {

  private MainViewModel viewModel;
  private FragmentBibleBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
       ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentBibleBinding.inflate(inflater, container, false);
    binding.searchButton.setOnClickListener((v) ->
        viewModel.search(binding.searchText.getText().toString().trim()));
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getResults().observe(getViewLifecycleOwner(), (verses) -> {
      ArrayAdapter<Verse> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, verses);
      binding.searchResults.setAdapter(adapter);
    });
    viewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) ->
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show());
  }
}