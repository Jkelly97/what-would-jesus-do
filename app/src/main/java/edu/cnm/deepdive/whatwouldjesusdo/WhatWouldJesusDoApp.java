package edu.cnm.deepdive.whatwouldjesusdo;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class  WhatWouldJesusDoApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
