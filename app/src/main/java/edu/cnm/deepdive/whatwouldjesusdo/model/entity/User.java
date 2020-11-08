package edu.cnm.deepdive.whatwouldjesusdo.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;


@Entity(
    indices = {
        @Index(value = {"oauth"}, unique = true)
    })
public class User {

  @PrimaryKey
  @ColumnInfo(name = "user_id")
  private long id;

  @NonNull
  private String oauth;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getOauth() {
    return oauth;
  }

  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

}
