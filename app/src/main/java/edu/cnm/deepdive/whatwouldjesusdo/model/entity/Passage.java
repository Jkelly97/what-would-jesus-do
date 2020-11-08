package edu.cnm.deepdive.whatwouldjesusdo.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;
@Entity(
    indices = {
        @Index(value = {"book","chapter", "starting_verse", "ending_verse"})
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

  private String book;

  private int chapter;

  @ColumnInfo(name = "starting_verse")
  private int startingVerse;

  @ColumnInfo(name = "ending_verse")
  private int endingVerse;

  @ColumnInfo(name = "user_id", index = true)
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

  public int getStartingVerse() {
    return startingVerse;
  }

  public void setStartingVerse(int startingVerse) {
    this.startingVerse = startingVerse;
  }

  public int getEndingVerse() {
    return endingVerse;
  }

  public void setEndingVerse(int endingVerse) {
    this.endingVerse = endingVerse;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
