package edu.cnm.deepdive.whatwouldjesusdo.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.Passage;
import edu.cnm.deepdive.whatwouldjesusdo.model.entity.User;
import java.util.List;


public class UserWithPassages extends User {

  @Relation(
      entity = Passage.class,
      entityColumn = "user_id",
      parentColumn = "user_id"
  )
  private List<Passage> passages;

  public List<Passage> getPassages() {
    return passages;
  }

  public void setPassages(List<Passage> passages) {
    this.passages = passages;
  }
}
