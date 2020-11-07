package edu.cnm.deepdive.whatwouldjesusdo.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;
@Entity(
    indices = {
        @Index(value = {"book", "verse"})
    },
    foreignKeys = {
      @ForeignKey(entity = User.class,
          parentColumns = {"user_id"}, childColumns = {"user_id"},
          onDelete = ForeignKey.CASCADE)
    }
)
public class Passage {

  @PrimaryKey
  @ColumnInfo(name = "passage_id", index = true)
  private long id;

  @ColumnInfo(index = true)
  private String book;


  private int chapter;

  @ColumnInfo(index = true)
  private int verse;

  @NonNull
  @ColumnInfo(name = "user_id")
  private long userId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBook() {
    return book;
  }

  public void setBook(String book) {
    this.book = book;
  }

  public int getChapter() {
    return chapter;
  }

  public void setChapter(int chapter) {
    this.chapter = chapter;
  }

  public int getVerse() {
    return verse;
  }

  public void setVerse(int verse) {
    this.verse = verse;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
