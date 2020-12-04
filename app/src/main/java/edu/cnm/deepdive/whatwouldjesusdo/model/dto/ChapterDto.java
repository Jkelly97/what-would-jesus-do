package edu.cnm.deepdive.whatwouldjesusdo.model.dto;

import com.google.gson.annotations.Expose;

public class ChapterDto {

  @Expose
  private String id;

  @Expose
  private String number;

  @Expose
  private String reference;

  private String passage;
  private int order;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getPassage() {
    return passage;
  }

  public void setPassage(String passage) {
    this.passage = passage;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
