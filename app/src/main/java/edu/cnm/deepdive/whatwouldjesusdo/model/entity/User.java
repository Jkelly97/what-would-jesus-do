package edu.cnm.deepdive.whatwouldjesusdo.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import io.reactivex.annotations.NonNull;


  @Entity(
      indices = {
    @Index(value = {"oauth"}, unique = true)
})
  public class User {


  @NonNull
  @ColumnInfo(name = "user_id")
  private long Id;

  @NonNull
  private String oauth;

  @NonNull
  private String password;

    public long getId() {
      return Id;
    }

    @NonNull
    public String getOauth() {
      return oauth;
    }

    public void setOauth(@NonNull String oauth) {
      this.oauth = oauth;
    }

    @NonNull
    public String getPassword() {
      return password;
    }

    public void setPassword(@NonNull String password) {
      this.password = password;
    }
  }
