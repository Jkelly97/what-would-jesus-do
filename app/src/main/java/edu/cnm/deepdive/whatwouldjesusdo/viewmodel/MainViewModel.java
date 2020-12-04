package edu.cnm.deepdive.whatwouldjesusdo.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.ChapterDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.PassageDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse.SearchData.VerseDto;
import edu.cnm.deepdive.whatwouldjesusdo.service.MainRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MainRepository mainRepository;
  private final MutableLiveData<List<VerseDto>> results;
  private final MutableLiveData<List<BookDto>> books;
  private final MutableLiveData<List<ChapterDto>> chapters;
  private final MutableLiveData<PassageDto> text;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MainViewModel(@NonNull Application application) {
    super(application);
    mainRepository = new MainRepository(application);
    results = new MutableLiveData<>();
    books = new MutableLiveData<>();
    chapters = new MutableLiveData<>();
    text = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadBooks();

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

  public LiveData<List<VerseDto>> getResults() {
    return results;
  }

  public LiveData<List<BookDto>> getBooks() {
    return books;
  }

  public LiveData<List<ChapterDto>> getChapters() {
    return chapters;
  }

  public LiveData<PassageDto> getText() {
    return text;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  private void loadBooks() {
    pending.add(
        mainRepository.getBooks()
            .subscribe(
                books::postValue,
                throwable::postValue
            )
    );
  }


  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
