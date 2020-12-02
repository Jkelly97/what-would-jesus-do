package edu.cnm.deepdive.whatwouldjesusdo.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.cnm.deepdive.whatwouldjesusdo.service.MainRepository;

public class MainViewModel extends AndroidViewModel {

  public final MainRepository mainRepository;


  public MainViewModel(@NonNull Application application) {
    super(application);
    mainRepository = new MainRepository(application);
    mainRepository.search("loved")
        .subscribe(
            (verses) -> {
              Log.d(getClass().getSimpleName(), verses.get(0).getText());
            },
            (throwable) -> {
              Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
            }
        );
 }
}
