package com.github.bot.curiosone.app.chat.helpers;

/**
 * Created by Luca Pierfederici on 11/09/2017.
 */

public class TalkRequestResponse {
  private String message;
  private String scope;

  public TalkRequestResponse(String message, String scope){
    this.message = message;
    this.scope = scope;
  }

  public TalkRequestResponse(){
    this("", "");
  }

  public String getMessage(){
    return message;
  }

  public String getScope(){
    return scope;
  }

  public void setMessage(String message){
    this.message = message;
  }

  public void setScope(String scope){
    this.scope = scope;
  }

  public String json(){
    return "{\"message\": \"" + message + "\", \"scope\": \"" + scope + "\"}";
  }

}
