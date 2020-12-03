package edu.cnm.deepdive.whatwouldjesusdo.model.dto;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.List;

public class SearchResponse {

  @Expose
  private SearchData data;

  public SearchData getData() {
    return data;
  }

  public void setData(SearchData data) {
    this.data = data;
  }

  public static class SearchData {

    @Expose
    private List<Verse> verses;

    public List<Verse> getVerses() {
      return verses;
    }

    public void setVerses(
        List<Verse> verses) {
      this.verses = verses;
    }

    public static class Verse {

      @Expose
      private String id;

      @Expose
      private String orgId;

      @Expose
      private String bookId;

      @Expose
      private String bibleId;

      @Expose
      private String chapterId;

      @Expose
      private String reference;

      @Expose
      private String text;

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getOrgId() {
        return orgId;
      }

      public void setOrgId(String orgId) {
        this.orgId = orgId;
      }

      public String getBookId() {
        return bookId;
      }

      public void setBookId(String bookId) {
        this.bookId = bookId;
      }

      public String getBibleId() {
        return bibleId;
      }

      public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
      }

      public String getChapterId() {
        return chapterId;
      }

      public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
      }

      public String getReference() {
        return reference;
      }

      public void setReference(String reference) {
        this.reference = reference;
      }

      public String getText() {
        return text;
      }

      public void setText(String text) {
        this.text = text;
      }

      @NonNull
      @Override
      public String toString() {
        return reference + "\n" + text;
      }
    }
  }
}
