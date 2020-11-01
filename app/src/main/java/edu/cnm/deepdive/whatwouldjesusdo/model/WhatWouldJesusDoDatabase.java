package edu.cnm.deepdive.whatwouldjesusdo.model;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;

@Database(entities = {User.class, Passage.class}, version = 1, exportSchema = true)
public abstract class WhatWouldJesusDoDatabase extends RoomDatabase {



}
