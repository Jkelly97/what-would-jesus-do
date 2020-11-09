package edu.cnm.deepdive.whatwouldjesusdo.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.PassageDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.UserDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import edu.cnm.deepdive.whatwouldjesusdo.model.pojo.PassageWithUser;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.xml.validation.SchemaFactoryLoader;

public class MainRepository {

  public final Context context;
  private final UserDao userDao;
  private final PassageDao passageDao;

  public MainRepository(Context context) {
    this.context = context;
    userDao = WhatWouldJesusDoDatabase.getInstance().getUserDao();
    passageDao = WhatWouldJesusDoDatabase.getInstance().getPassageDao();
  }

  public Single<User> getOrCreate(String oauth) {
    return userDao.select(oauth)
        .switchIfEmpty(
            Single.fromCallable(() -> {
              User user = new User();
              user.setOauth(oauth);
              return user;
            })
                .flatMap((user) ->
                    userDao.insert(user)
                        .map((id) -> {
                          user.setId(id);
                          return user;
                        })
                )
        )
        .subscribeOn(Schedulers.io());
  }

  public Completable remove(User user) {
    return userDao.delete(user)
        .ignoreElement()
        .subscribeOn(Schedulers.io());
  }

  public LiveData<User> getUser(long id) {
    return userDao.select(id);
  }

  public LiveData<List<Passage>> listPassages(User user) {
    return passageDao.selectForUser(user.getId());
  }

  public LiveData<PassageWithUser> getPassage(long id) {
    return passageDao.select(id);
  }

  public Completable save(Passage passage) {
    return (
        (passage.getId() == 0)
            ? passageDao.insert(passage)
            .doAfterSuccess(passage::setId)
            .ignoreElement()
            : passageDao.update(passage)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  public Completable remove(Passage passage) {
    return (
        (passage.getId() == 0)
            ? Completable.complete()
            : passageDao.delete(passage)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }
}
