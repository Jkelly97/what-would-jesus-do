package edu.cnm.deepdive.whatwouldjesusdo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.whatwouldjesusdo.BuildConfig;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.BookDto.BooksResponse;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.ChapterDto;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.PassageResponse;
import edu.cnm.deepdive.whatwouldjesusdo.model.dto.SearchResponse;
import io.reactivex.Single;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WWJDService {

  @GET("search")
  Single<SearchResponse> search(@Header("Authorization") String authHeader, @Header("api-key")
      String apiKey, @Query("query") String query);

  @GET("books")
  Single<BooksResponse> getBooks(@Header("Authorization") String authHeader, @Header("api-key")
      String apiKey);

  @GET("books/{bookId}/chapters")
  Single<List<ChapterDto>> getChapters(@Header("Authorization") String authHeader, @Header("api-key")
      String apiKey, @Path("bookId") String bookId);

  @GET("passages/{passageId}")
  Single<PassageResponse> getPassage(@Header("Authorization") String authHeader, @Header("api-key")
      String apiKey, @Path("passageId") String passageId);

  static WWJDService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final WWJDService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WWJDService.class);
    }
  }
}
