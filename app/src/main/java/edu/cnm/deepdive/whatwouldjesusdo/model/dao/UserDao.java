
package edu.cnm.deepdive.whatwouldjesusdo.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import edu.cnm.deepdive.whatwouldjesusdo.model.pojo.UserWithPassages;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserDao {

 @Insert(onConflict = OnConflictStrategy.IGNORE)
 Single<Long> insert(User user);

 @Insert
 Single<List<Long>> insert(User... users);

 @Insert
 Single<List<Long>> insert(Collection<User> users);

 @Update
 Single<Integer> update(User user);

 @Update
 Single<Integer> update(User... users);

 @Update
 Single<Integer> update(Collection<User> users);

 @Delete
 Single<Integer> delete(User user);

 @Delete
 Single<Integer> delete(User... users);

 @Delete
 Single<Integer> delete(Collection<User> users);

 @Query("SELECT * FROM User WHERE user_id = :id")
 LiveData<User> select(long id);

 @Query("SELECT * FROM User WHERE oauth = :oauth")
 default Maybe<User> select(GoogleSignInAccount oauth) {
  return null;
 }

}