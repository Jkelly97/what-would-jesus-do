package edu.cnm.deepdive.whatwouldjesusdo.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.whatwouldjesusdo.BuildConfig;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.PassageDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dao.UserDao;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto.BooksResponse;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.ChapterDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.PassageDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse.SearchData.VerseDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import edu.cnm.deepdive.whatwouldjesusdo.model.pojo.PassageWithUser;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

  public Single<List<VerseDto>> search(String query) {
    return serviceProxy.search(BuildConfig.AUTHORIZATION_HEADER, context.getString(R.string.api_key), query)
        .map((response) -> response.getData().getVerses())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<BookDto>> getBooks() {
    AtomicInteger counter = new AtomicInteger();
    return serviceProxy.getBooks(BuildConfig.AUTHORIZATION_HEADER, context.getString(R.string.api_key))
        .map(BooksResponse::getData)
        .map((books) -> books.stream()
            .peek((bookDto) -> bookDto.setOrder(counter.incrementAndGet()))
            .collect(Collectors.toList())
        )
        .subscribeOn(Schedulers.io());
  }

  public Single<List<ChapterDto>> getChapters(BookDto book) {
    AtomicInteger counter = new AtomicInteger();
    return serviceProxy.getChapters(BuildConfig.AUTHORIZATION_HEADER, context.getString(R.string.api_key), book.getId())
        .map((chapters) -> chapters.stream()
            .peek((chapterDto) -> chapterDto.setOrder(counter.incrementAndGet()))
            .peek((chapter) -> book.getChapters().add(chapter))
            .collect(Collectors.toList())
        )
        .subscribeOn(Schedulers.io());
  }

  public Single<PassageDto> getPassage(ChapterDto chapter) {
    return serviceProxy.getPassage(BuildConfig.AUTHORIZATION_HEADER, context.getString(R.string.api_key), chapter.getId())
        .map(passageResponse -> {
          chapter.setPassage(passageResponse.getData().getContent());
          return passageResponse.getData();
        })
        .subscribeOn(Schedulers.io());
  }
}
