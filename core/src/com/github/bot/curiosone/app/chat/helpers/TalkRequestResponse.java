package com.github.bot.curiosone.app.chat.helpers;

/**
 * Created by Luca Pierfederici on 11/09/2017.
 */

public class TalkRequestResponse {
  private String message;

  public TalkRequestResponse(){
  }
  public TalkRequestResponse(String message){
    this.message = message;
  }

  public String getMessage(){
    return message;
  }

  public void setMessage(String message){
    this.message = message;
  }
}
