package edu.cnm.deepdive.whatwouldjesusdo.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.whatwouldjesusdo.BuildConfig;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.PassageDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.UserDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse.SearchData.Verse;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import edu.cnm.deepdive.whatwouldjesusdo.model.pojo.PassageWithUser;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainRepository {

  public final Context context;
  private final UserDao userDao;
  private final PassageDao passageDao;
  private final WWJDService serviceProxy;

  public MainRepository(Context context) {
    this.context = context;
    userDao = WhatWouldJesusDoDatabase.getInstance().getUserDao();
    passageDao = WhatWouldJesusDoDatabase.getInstance().getPassageDao();
    serviceProxy = WWJDService.getInstance();
  }

  public Single<User> getOrCreate(@NonNull GoogleSignInAccount account) {
    return userDao.select(account)
        .switchIfEmpty(
            Single.fromCallable(() -> {
              User user = new User();
              user.setDisplayName(account.getDisplayName());
              user.setOauth(account.getId());
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

//  public Single<bible> kingJamesVersion(String ) {
//    return serviceProxy.bible(BuildConfig.)
//  }

  public Single<List<Verse>> search(String query) {
    return serviceProxy.search(BuildConfig.AUTHORIZATION_HEADER, context.getString(R.string.api_key), query)
        .map((response) -> response.getData().getVerses())
        .subscribeOn(Schedulers.io());
  }
}
