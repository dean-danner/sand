package core;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;
import substances.Sand;
import substances.Substance;

public class Title extends BasicGameState {
  private StateBasedGame sbg;
  
  private TrueTypeFont ttf;
  
  private TrueTypeFont ttft;
  
  private Substance[][] curGrid;
  
  private Substance[][] newGrid;
  
  private int id;
  
  private int placeTimer;
  
  public Title(int id) {
    this.id = id;
  }
  
  public int getID() {
    return this.id;
  }
  
  public boolean inBounds(float x, float y) {
    return (x < 480.0F && y < 245.0F && x >= 0.0F && y >= 0.0F);
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    this.sbg = sbg;
    this.ttf = new TrueTypeFont(new Font("Arial", 1, 45), true);
    this.ttft = new TrueTypeFont(new Font("Arial", 1, 65), true);
    this.curGrid = new Substance[480][245];
    this.newGrid = new Substance[480][245];
    for (int r = 0; r < 480; r++) {
      for (int c = 0; c < 245; c++) {
        this.curGrid[r][c] = new Substance(-1);
        this.newGrid[r][c] = new Substance(-1);
      } 
    } 
  }
  
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    for (int r = 0; r < 480; r++) {
      for (int c = 0; c < 245; c++) {
        Substance cg = this.curGrid[r][c];
        if (this.curGrid[r][c].isSubstance()) {
          g.setColor(cg.getColor());
          g.fillRect((r * 4), (c * 4), 4.0F, 4.0F);
        } 
      } 
    } 
    drawTitle(gc, g);
  }
  
  public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    int r;
    for (r = 0; r < 480; r++) {
      for (int c = 0; c < 245; c++)
        this.curGrid[r][c] = this.newGrid[r][c]; 
    } 
    for (r = 479; r >= 0; r--) {
      for (int c = 244; c >= 0; c--)
        setMotion(r, c); 
    } 
    placeSand();
  }
  
  public void setMotion(int r, int c) {
    Substance cg = this.curGrid[r][c];
    Point mn = cg.moveNorm(r, c);
    if (inBounds(r, (c + 1)) && 
      cg.isSubstance()) {
      if (mn == null)
        return; 
      if (!this.curGrid[mn.getX()][mn.getY()].isSubstance()) {
        this.newGrid[mn.getX()][mn.getY()] = cg.getSubstance();
        this.newGrid[r][c] = new Substance(-1);
      } 
    } 
  }
  
  public void placeSand() {
    int ran = (int)(Math.random() * 480.0D - 1.0D);
    if (this.placeTimer == 0) {
      this.newGrid[0 + ran][0] = (Substance)new Sand(1);
      ran = (int)(Math.random() * 480.0D - 1.0D);
      this.placeTimer = 25;
    } 
    if (this.placeTimer > 0)
      this.placeTimer--; 
  }
  
  public void drawTitle(GameContainer gc, Graphics g) {
    g.setBackground(new Color(Color.black));
    g.setColor(new Color(Color.white));
    this.ttf.drawString((960 - this.ttf.getWidth("SAND GAME") / 2), 100.0F, "SAND GAME");
    this.ttft.drawString((960 - this.ttft.getWidth("PRESS SPACEBAR TO CONTINUE") / 2), 250.0F, "PRESS SPACEBAR TO CONTINUE");
    g.setColor(new Color(Color.gray));
    g.fillRect(0.0F, (gc.getHeight() - 100), gc.getWidth(), 100.0F);
  }
  
  public void keyReleased(int key, char c) {
    if (key == 57)
      this.sbg.enterState(1, (Transition)new FadeOutTransition(Color.white, 480), (Transition)new FadeInTransition(Color.white, 480)); 
  }
}
