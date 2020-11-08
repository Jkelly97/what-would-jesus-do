package edu.cnm.deepdive.whatwouldjesusdo.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import java.util.List;

public class PassageWithUser extends Passage {

  @Relation(
      entity = User.class,
      entityColumn = "user_id",
      parentColumn = "user_id"
  )
  private User user;

// TODO List of user tying to passage; preload table with passages

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
