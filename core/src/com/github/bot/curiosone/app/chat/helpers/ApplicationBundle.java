package com.github.bot.curiosone.app.chat.helpers;

public class ApplicationBundle {

  /*
 *this class is used for calculate height of keyboard with another class/interface
 * */
  private final View view;

  public ApplicationBundle(View view) {
    this.view = view;
  }

  public View getView() {
    return view;
  }

}
