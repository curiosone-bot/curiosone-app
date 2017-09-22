import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;

public class ScreenManager {
  private static ScreenManager instance;
  private Game game;

  private ScreenManager() {
    super();
  }

  public static ScreenManager getInstance() {
    if (instance == null) {
      instance = new ScreenManager();
    }
    return instance;
  }

  public void initialize(Game game) {
    this.game = game;
  }

  public void showScreen(ScreenEnum screen) {
    Screen currentScreen = game.getScreen();
    Screen newScreen = screen.getScreen();
    game.setScreen(newScreen);
    if(currentScreen != null) {
      currentScreen.dispose();
    }
  }

}
