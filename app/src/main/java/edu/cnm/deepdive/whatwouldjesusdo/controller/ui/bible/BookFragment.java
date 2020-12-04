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
import edu.cnm.deepdive.whatwouldjesusdo.databinding.FragmentBibleBinding;
import edu.cnm.deepdive.whatwouldjesusdo.databinding.FragmentBookBinding;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto;
import edu.cnm.deepdive.whatwouldjesusdo.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * A fragment representing the Bible itself. It applies the layout to the main activity resulting
 * in the bible screen in the application {@link edu.cnm.deepdive.whatwouldjesusdo.controller.MainActivity}.
 */
public class BookFragment extends Fragment {

  private MainViewModel viewModel;
  private FragmentBookBinding binding;


  /**
   * This method enables the search function.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
       ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentBookBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  /**
   * This method develops the search bar function.
   * @param view
   * @param savedInstanceState
   */
  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getBooks().observe(getViewLifecycleOwner(), (books) -> {
      ArrayAdapter<BookDto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, books);
      binding.chapters.setAdapter(adapter);
    });
    viewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) ->
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show());
  }
}