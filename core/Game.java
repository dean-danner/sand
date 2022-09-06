package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import substances.Acid;
import substances.Gravel;
import substances.Gunpowder;
import substances.Sand;
import substances.Structure;
import substances.Substance;

public class Game extends BasicGameState {
  private static UI ui = new UI();
  
  private Substance[][] curGrid;
  
  private Substance[][] newGrid;
  
  private int id;
  
  private int clickTimer;
  
  private int distanceFormula;
  
  public Game(int id) {
    this.id = id;
  }
  
  public int getID() {
    return this.id;
  }
  
  public int distance(int x1, int y1, int x2, int y2) {
    this.distanceFormula = (int)Math.sqrt(Math.pow((x1 - x2), 2.0D) + Math.pow((y1 - y2), 2.0D));
    return this.distanceFormula;
  }
  
  public int getMouseIndexX(GameContainer gc) {
    if (gc.getInput().getMouseX() / 4 < 480)
      return gc.getInput().getMouseX() / 4; 
    return 479;
  }
  
  public int getMouseIndexY(GameContainer gc) {
    if (gc.getInput().getMouseY() / 4 < 245)
      return gc.getInput().getMouseY() / 4; 
    return 244;
  }
  
  public boolean inBounds(float x, float y) {
    return (x < 480.0F && y < 245.0F && x >= 0.0F && y >= 0.0F);
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    this.curGrid = new Substance[480][245];
    this.newGrid = new Substance[480][245];
    for (int r = 0; r < 480; r++) {
      for (int c = 0; c < 245; c++) {
        this.curGrid[r][c] = new Substance(-1);
        this.newGrid[r][c] = new Substance(-1);
      } 
    } 
    ui.init(gc, sbg);
  }
  
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    for (int r = 0; r < 480; r++) {
      for (int c = 0; c < 245; c++) {
        Substance cg = this.curGrid[r][c];
        if (this.curGrid[r][c].isSubstance() || this.curGrid[r][c].isAcid())
          g.setColor(cg.getColor()); 
        if (this.curGrid[r][c].isSubstance() || this.curGrid[r][c].isAcid())
          g.fillRect((r * 4), (c * 4), 4.0F, 4.0F); 
      } 
    } 
    ui.render(gc, g);
    setLaser(gc, g);
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
    resetMap(gc);
    placeSubstance(gc);
  }
  
  public void resetMap(GameContainer gc) {
    if (Toggles.RESET_ACTIVE) {
      for (int r = 0; r < 480; r++) {
        for (int c = 0; c < 245; c++)
          this.newGrid[r][c] = new Substance(-1); 
      } 
      Toggles.RESET_ACTIVE = false;
    } 
  }
  
  public void setMotion(int r, int c) {
    Substance cg = this.curGrid[r][c];
    Point mn = cg.moveNorm(r, c);
    Point mr = cg.moveResolve(r, c);
    Point dl = cg.downLeft(r, c);
    Point dr = cg.downRight(r, c);
    if (inBounds(r, (c + 1))) {
      if (cg.isSubstance() || cg.isAcid()) {
        if (mn == null)
          return; 
        if (!this.curGrid[mn.getX()][mn.getY()].isSubstance()) {
          this.newGrid[mn.getX()][mn.getY()] = cg.getSubstance();
          this.newGrid[r][c] = new Substance(-1);
          setWind(r, c);
        } else if ((cg.getValue() == 1 || cg.getValue() == 5) && 
          this.curGrid[r][c + 1].isSubstance()) {
          if (inBounds((r + 1), (c + 1)) && inBounds((r - 1), (c + 1))) {
            if (!this.curGrid[mr.getX()][mr.getY()].isSubstance()) {
              this.newGrid[mr.getX()][mr.getY()] = cg.getSubstance();
              this.newGrid[r][c] = new Substance(-1);
            } 
          } else if (!inBounds((r + 1), (c + 1))) {
            if (!this.curGrid[dl.getX()][dl.getY()].isSubstance()) {
              this.newGrid[dl.getX()][dl.getY()] = cg.getSubstance();
              this.newGrid[r][c] = new Substance(-1);
            } 
          } else if (!inBounds((r - 1), (c + 1)) && 
            !this.curGrid[dr.getX()][dr.getY()].isSubstance()) {
            this.newGrid[dr.getX()][dr.getY()] = cg.getSubstance();
            this.newGrid[r][c] = new Substance(-1);
          } 
        } 
      } 
      if (cg.getValue() == 4) {
        this.newGrid[mn.getX()][mn.getY()] = cg.getSubstance();
        this.newGrid[r][c] = new Substance(-1);
      } 
    } 
  }
  
  public void setWind(int r, int c) {
    Substance cg = this.curGrid[r][c];
    Point wr = cg.windRight(r, c);
    Point wl = cg.windLeft(r, c);
    if (Toggles.WIND_ACTIVE && cg.isSubstance()) {
      if (Toggles.WIND_DIRECTION && inBounds((r + 1), (c + 1)) && 
        !this.curGrid[wr.getX()][wr.getY()].isSubstance()) {
        this.newGrid[wr.getX()][wr.getY()] = cg.getSubstance();
        this.newGrid[r][c + 1] = new Substance(-1);
      } 
      if (!Toggles.WIND_DIRECTION && inBounds((r - 1), (c + 1)) && 
        !this.curGrid[wl.getX()][wl.getY()].isSubstance()) {
        this.newGrid[wl.getX()][wl.getY()] = cg.getSubstance();
        this.newGrid[r][c + 1] = new Substance(-1);
      } 
    } 
  }
  
  public void setLaser(GameContainer gc, Graphics g) {
    if (Toggles.LASER_ACTIVE && gc.getInput().isMouseButtonDown(1) && gc.getInput().getMouseY() < gc.getHeight() - 105) {
      g.setColor(new Color(Color.green));
      g.fillRect((getMouseIndexX(gc) * 4 - 4), (getMouseIndexY(gc) * 4 - 4), 8.0F, 8.0F);
      if (getMouseIndexY(gc) < 244 && getMouseIndexY(gc) > 1 && getMouseIndexX(gc) < 479 && getMouseIndexX(gc) > 1)
        for (int w = -1; w < 1; w++) {
          for (int h = -1; h < 1; h++) {
            if (this.curGrid[getMouseIndexX(gc) + w][getMouseIndexY(gc) + h].isSubstance())
              this.newGrid[getMouseIndexX(gc) + w][getMouseIndexY(gc) + h] = new Substance(-1); 
          } 
        }  
    } 
  }
  
  public void placeSubstance(GameContainer gc) {
    if (gc.getInput().isMouseButtonDown(0) && !this.curGrid[getMouseIndexX(gc)][getMouseIndexY(gc)].isSubstance() && this.clickTimer == 0) {
      for (int a = 0; a < 5; a++) {
        int ranX = (int)(Math.random() * -6.0D) + 3;
        int ranY = (int)(Math.random() * -6.0D) + 3;
        if (inBounds((getMouseIndexX(gc) + ranX), (getMouseIndexY(gc) + ranY)) && !this.curGrid[getMouseIndexX(gc) + ranX][getMouseIndexY(gc) + ranY].isSubstance() && getMouseIndexY(gc) < 244) {
          if (Toggles.SAND_ACTIVE)
            this.newGrid[getMouseIndexX(gc) + ranX][getMouseIndexY(gc) + ranY] = (Substance)new Sand(1); 
          if (Toggles.GUNPOWDER_ACTIVE)
            this.newGrid[getMouseIndexX(gc) + ranX][getMouseIndexY(gc) + ranY] = (Substance)new Gunpowder(5); 
          if (Toggles.GRAVEL_ACTIVE)
            this.newGrid[getMouseIndexX(gc) + ranX][getMouseIndexY(gc) + ranY] = (Substance)new Gravel(2); 
          if (Toggles.STRUCTURE_ACTIVE && getMouseIndexY(gc) < 243 && getMouseIndexY(gc) > 2 && getMouseIndexX(gc) < 478 && getMouseIndexX(gc) > 2)
            for (int w = -2; w < 2; w++) {
              for (int h = -2; h < 2; h++) {
                if (!this.curGrid[getMouseIndexX(gc) + w][getMouseIndexY(gc) + h].isSubstance())
                  this.newGrid[getMouseIndexX(gc) + w][getMouseIndexY(gc) + h] = (Substance)new Structure(3); 
              } 
            }  
          if (Toggles.ACID_ACTIVE)
            this.newGrid[getMouseIndexX(gc) + ranX][getMouseIndexY(gc) + ranY] = (Substance)new Acid(4); 
        } 
      } 
      this.clickTimer = 3;
    } 
    if (this.clickTimer > 0)
      this.clickTimer--; 
  }
}
