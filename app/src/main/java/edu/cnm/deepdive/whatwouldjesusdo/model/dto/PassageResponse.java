package edu.cnm.deepdive.whatwouldjesusdo.model.dto;

import com.google.gson.annotations.Expose;

public class PassageResponse {

  @Expose
  private PassageDto data;

  public PassageDto getData() {
    return data;
  }

  public void setData(PassageDto data) {
    this.data = data;
  }
}
