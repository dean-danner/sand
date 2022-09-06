package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Engine extends StateBasedGame {
  private BasicGameState title;
  
  private BasicGameState game;
  
  public Engine(String name) {
    super(name);
    this.title = new Title(0);
    this.game = new Game(1);
  }
  
  public void initStatesList(GameContainer gc) throws SlickException {
    addState((GameState)this.title);
    addState((GameState)this.game);
  }
  
  public static void main(String[] args) {
    try {
      AppGameContainer appgc = new AppGameContainer((Game)new Engine("Sand"));
      System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
      appgc.setDisplayMode(1920, 1080, false);
      appgc.setTargetFrameRate(120);
      appgc.setShowFPS(false);
      appgc.start();
    } catch (SlickException e) {
      e.printStackTrace();
    } 
  }
}
