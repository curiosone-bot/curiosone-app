package com.github.bot.curiosone.app.chat.world;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.IOException;

public class ServerConnection {
  
  public String getAnswer(String richiesta)
  {
    SocketHints sh= new SocketHints();
    sh.connectTimeout=10000;
    Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "server", 1337, sh);
    try {
      socket.getOutputStream().write(richiesta.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return socket.getInputStream().toString();
  }

}
