package edu.cnm.deepdive.whatwouldjesusdo.model.dto;

import com.google.gson.annotations.Expose;

public class PassageDto {

  @Expose
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
