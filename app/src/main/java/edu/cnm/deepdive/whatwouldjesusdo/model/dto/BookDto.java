package edu.cnm.deepdive.whatwouldjesusdo.model.dto;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.LinkedList;
import java.util.List;

public class BookDto {

  @Expose
  private String id;

  @Expose
  private String name;

  @Expose
  private String abbreviation;

  @Expose
  private String nameLong;

  private int order;

  private List<ChapterDto> chapters = new LinkedList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getNameLong() {
    return nameLong;
  }

  public void setNameLong(String nameLong) {
    this.nameLong = nameLong;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public List<ChapterDto> getChapters() {
    return chapters;
  }

  @NonNull
  @Override
  public String toString() {
    return name + "\n" + nameLong;
  }

  public static class BooksResponse {
    @Expose
    private List<BookDto> data;

    public List<BookDto> getData() {
      return data;
    }

    public void setData(List<BookDto> data) {
      this.data = data;
    }
  }
}
