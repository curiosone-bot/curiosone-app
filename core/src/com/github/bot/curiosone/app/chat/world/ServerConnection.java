package com.github.bot.curiosone.app.chat.world;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;


public class ServerConnection{

  public static final String BASE_URL = "https://curiosone-bot.herokuapp.com";
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private OkHttpClient client;
  private ChatWorld world;


  public ServerConnection() throws IOException {
    client = new OkHttpClient().newBuilder()
      .connectTimeout(10,TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();

  }

  public String getAnswer(TalkRequestResponse message) throws IOException {
    Json json = new Json();
   // System.out.println(json.toJson(message));
    String url = BASE_URL + "/talk";
    RequestBody body = RequestBody.create(JSON, message.json());
    Request request = new Request.Builder()
      .url(url)
      .post(body)
      .build();
    try (Response response = this.client.newCall(request).execute()) {
      String data = response.body().string();
      world.lastBotMessage = json.fromJson(TalkRequestResponse.class, data);

    }
    catch(UnknownHostException e)
    {
      world.lastBotMessage.setMessage("error with server comunication, please check your connection and retry ");
    }
    catch (SerializationException e) {
      world.lastBotMessage.setMessage("error with server , please retry later");
    }
    if (world.lastBotMessage.getMessage().equals("error with server , please retry later")) {
      Random rnd = new Random();
      if (rnd.nextBoolean()) {
        world.lastBotMessage.setMessage("You wanna play?");
        System.out.print(world.getInserimento());
        world.getInserimento().setPlay(true);
      }
    }

    return world.lastBotMessage.getMessage();
  }

  public void setWorld(ChatWorld world) {
    this.world = world;
  }
}
