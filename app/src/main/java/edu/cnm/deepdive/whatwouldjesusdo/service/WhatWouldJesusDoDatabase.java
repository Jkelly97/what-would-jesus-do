package edu.cnm.deepdive.whatwouldjesusdo.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.PassageDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.UserDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;

@Database(entities = {User.class, Passage.class}, version = 1, exportSchema = true)
public abstract class WhatWouldJesusDoDatabase extends RoomDatabase {

  private static final String DB_NAME = "whatwouldjesusdodatabase_db";

  private static Application context;

  public static void setContext(Application context) {
    WhatWouldJesusDoDatabase.context = context;
  }

  public static WhatWouldJesusDoDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract UserDao getUserDao();

  public abstract PassageDao getPassageDao();


  private static class InstanceHolder {

    private static final WhatWouldJesusDoDatabase INSTANCE =
        Room.databaseBuilder(context, WhatWouldJesusDoDatabase.class, DB_NAME)
//            .addCallback()
            .build();

  }

  // TODO explore creating a callback to preload the database
}
