package com.github.bot.curiosone.app.chat.helpers;

/**
 * Created by Luca Pierfederici on 11/09/2017.
 */

public class TalkRequestResponse {
  private String message;
  private String scope;
  private String emotion;

  public TalkRequestResponse(String message, String scope, String emotion){
    this.message = message;
    this.scope = scope;
    this.emotion = emotion;
  }

  public TalkRequestResponse(){
    this("", "", "");
  }

  public String getMessage(){
    return message;
  }

  public String getScope(){
    return scope;
  }

  public String getEmotion(){
    return emotion;
  }

  public void setMessage(String message){
    this.message = message;
  }

  public void setScope(String scope){
    this.scope = scope;
  }

  public void setEmotion(String emotion){
    this.emotion = emotion;
  }

  public String json(){
    return "{\"message\": \"" + message + "\", \"scope\": \"" + scope + "\"}";
  }

}
