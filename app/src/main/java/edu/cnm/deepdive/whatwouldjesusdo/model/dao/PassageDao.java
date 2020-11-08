package edu.cnm.deepdive.whatwouldjesusdo.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import edu.cnm.deepdive.whatwouldjesusdo.model.pojo.PassageWithUser;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface PassageDao {

  @Insert
  Single<Long> insert(Passage passage);

  @Insert
  Single<List<Long>> insert(Passage... passages);

  @Insert
  Single<List<Long>> insert(Collection<Passage> passages);

  @Update
  Single<Integer> update(Passage passage);

  @Update
  Single<Integer> update(Passage... passages);

  @Update
  Single<Integer> update(Collection<Passage> passages);

  @Delete
  Single<Integer> delete(Passage passage);

  @Delete
  Single<Integer> delete(Passage... passages);

  @Delete
  Single<Integer> delete(Collection<Passage> passages);

  @Transaction
  @Query("SELECT * FROM Passage WHERE passage_id = :id")
  LiveData<PassageWithUser> select(long id);

  @Query("SELECT * FROM Passage WHERE user_id = :id"
      + " ORDER BY book ASC, chapter ASC, starting_verse ASC, ending_verse ASC")
  LiveData<List<Passage>> selectForUser(long id);

//  @Query("SELECT p.* FROM passage AS p INNER JOIN user AS u ON u.user_id = p.user_id")
}
