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
}
