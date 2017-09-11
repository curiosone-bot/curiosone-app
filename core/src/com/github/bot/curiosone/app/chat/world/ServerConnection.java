package com.github.bot.curiosone.app.chat.world;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class ServerConnection{

  private URL url;
  private HttpURLConnection con;
  private DataOutputStream wr;


  public ServerConnection() throws IOException {
  url=new URL("https://curiosone-api.herokuapp.com");



  }

  public String getAnswer(String richiesta) throws IOException {
    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    conn.setReadTimeout(10000);
    conn.setConnectTimeout(15000);
    conn.setRequestMethod("POST");
    conn.setDoInput(true);
    conn.setDoOutput(true);
    //Uri.Builder builder = new Uri.Builder();
  return "";
  }


}
