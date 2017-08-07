package com.github.bot.curiosone.app.chat.world;

/**
 * Created by federico-pc on 07/08/2017.
 */

public class AITemporanea {

  public String getRisposta(String question)
  {


    String answer;
    switch (question) {
      case "ciao":
        answer = "ciao";
        break;
      case "come stai?":
        answer = "bene grazie";
        break;
      case "che cos'e' il super sayan blu?":
        answer = "e' il super sayan di un sayan che ha superato la potenza del super sayan go diventando super sayan god super sayan";
        break;
      case "a che ora?":
        answer = "alle 9 in piazza";
        break;
      case "voglio giocare":
        answer = "scarica xenoverse 2";
        break;
      default:
        answer="hai gia' fatto i soldi";
    }

  return answer;
  }



}
