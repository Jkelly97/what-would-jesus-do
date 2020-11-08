package edu.cnm.deepdive.whatwouldjesusdo;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.whatwouldjesusdo.service.WhatWouldJesusDoDatabase;
import io.reactivex.schedulers.Schedulers;

public class  WhatWouldJesusDoApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    WhatWouldJesusDoDatabase.setContext(this);
    WhatWouldJesusDoDatabase.getInstance().getUserDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
