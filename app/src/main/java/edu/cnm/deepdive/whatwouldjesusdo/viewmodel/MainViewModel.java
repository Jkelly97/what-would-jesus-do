package edu.cnm.deepdive.whatwouldjesusdo.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse.SearchData.Verse;
import edu.cnm.deepdive.whatwouldjesusdo.service.MainRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MainRepository mainRepository;
  private final MutableLiveData<List<Verse>> results;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MainViewModel(@NonNull Application application) {
    super(application);
    mainRepository = new MainRepository(application);
    results = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();


  }

  public void search(String keyword) {
    pending.add(
        mainRepository.search(keyword)
            .subscribe(
                results::postValue,
                throwable::postValue
            )
    );
  }

  public LiveData<List<Verse>> getResults() {
    return results;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
