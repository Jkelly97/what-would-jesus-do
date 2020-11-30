package edu.cnm.deepdive.whatwouldjesusdo.controller.ui.bible;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BibleViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public BibleViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is Bible fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}