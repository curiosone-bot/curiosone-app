package com.github.bot.curiosone.app.chat.world;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.badlogic.gdx.utils.Json;
import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;


public class ServerConnection{

  public static final String BASE_URL = "https://curiosone-api.herokuapp.com";
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private HttpURLConnection con;
  private DataOutputStream wr;
  private OkHttpClient client;


  public ServerConnection() throws IOException {
     client = new OkHttpClient();
  }

  public String getAnswer(TalkRequestResponse message) throws IOException {
    Json json = new Json();
    String url = BASE_URL + "/talk";

    RequestBody body = RequestBody.create(JSON, json.toJson(message).toString());
    Request request = new Request.Builder()
      .url(url)
      .post(body)
      .build();
    try (Response response = this.client.newCall(request).execute()) {
      String data = response.body().string();
      return json.fromJson(TalkRequestResponse.class, data).getMessage();

    }
    catch (Exception e){
      System.out.println(e);
      return "";
    }
  }

}
