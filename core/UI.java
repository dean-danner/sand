package core;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class UI {
  private TrueTypeFont ttf;
  
  private TrueTypeFont ttfg;
  
  public int getMouseIndexX(GameContainer gc) {
    return gc.getInput().getMouseX();
  }
  
  public int getMouseIndexY(GameContainer gc) {
    return gc.getInput().getMouseY();
  }
  
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    this.ttf = new TrueTypeFont(new Font("Arial", 1, 15), true);
    this.ttfg = new TrueTypeFont(new Font("Arial", 1, 10), true);
  }
  
  public void render(GameContainer gc, Graphics g) throws SlickException {
    g.setColor(new Color(Color.gray));
    g.fillRect(0.0F, (gc.getHeight() - 100), gc.getWidth(), 100.0F);
    g.setColor(new Color(Color.lightGray));
    for (int b = 0; b <= 8; b++)
      g.fillRect((425 + b * 100), (gc.getHeight() - 84), 70.0F, 70.0F); 
    g.setColor(new Color(Color.black));
    g.fillRect(925.0F, (gc.getHeight() - 84), 70.0F, 70.0F);
    renderButtons(gc, g);
  }
  
  public void renderButtons(GameContainer gc, Graphics g) throws SlickException {
    sandButton(gc, g);
    gravelButton(gc, g);
    gunpowderButton(gc, g);
    structureButton(gc, g);
    acidButton(gc, g);
    resetButton(gc, g);
    windButton(gc, g);
    windDirectionButton(gc, g);
    laserButton(gc, g);
  }
  
  public void gunpowderButton(GameContainer gc, Graphics g) throws SlickException {
    Image gunpowder = new Image("res/gunpowder.png");
    if (getMouseIndexX(gc) > 425 && getMouseIndexX(gc) < 495 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttfg.drawString((436 - this.ttfg.getWidth("GUNPOWDER") / 2 + 24), (gc.getHeight() - 48 - this.ttfg.getHeight("GUNPOWDER") / 2), "GUNPOWDER", Color.darkGray);
      if (gc.getInput().isMousePressed(0)) {
        Toggles.SAND_ACTIVE = false;
        Toggles.GRAVEL_ACTIVE = false;
        Toggles.STRUCTURE_ACTIVE = false;
        Toggles.ACID_ACTIVE = false;
        Toggles.GUNPOWDER_ACTIVE = true;
      } 
    } else {
      g.drawImage(gunpowder, 436.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void sandButton(GameContainer gc, Graphics g) throws SlickException {
    Image sand = new Image("res/sand.png");
    if (getMouseIndexX(gc) > 525 && getMouseIndexX(gc) < 595 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((536 - this.ttf.getWidth("SAND") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("SAND") / 2), "SAND", Color.darkGray);
      if (gc.getInput().isMousePressed(0)) {
        Toggles.SAND_ACTIVE = true;
        Toggles.GRAVEL_ACTIVE = false;
        Toggles.STRUCTURE_ACTIVE = false;
        Toggles.ACID_ACTIVE = false;
        Toggles.GUNPOWDER_ACTIVE = false;
      } 
    } else {
      g.drawImage(sand, 536.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void gravelButton(GameContainer gc, Graphics g) throws SlickException {
    Image gravel = new Image("res/gravel.png");
    if (getMouseIndexX(gc) > 625 && getMouseIndexX(gc) < 695 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((636 - this.ttf.getWidth("GRAVEL") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("GRAVEL") / 2), "GRAVEL", Color.darkGray);
      if (gc.getInput().isMousePressed(0)) {
        Toggles.SAND_ACTIVE = false;
        Toggles.GRAVEL_ACTIVE = true;
        Toggles.STRUCTURE_ACTIVE = false;
        Toggles.ACID_ACTIVE = false;
        Toggles.GUNPOWDER_ACTIVE = false;
      } 
    } else {
      g.drawImage(gravel, 636.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void structureButton(GameContainer gc, Graphics g) throws SlickException {
    Image structure = new Image("res/structure.png");
    if (getMouseIndexX(gc) > 725 && getMouseIndexX(gc) < 795 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttfg.drawString((736 - this.ttfg.getWidth("STRUCTURE") / 2 + 24), (gc.getHeight() - 48 - this.ttfg.getHeight("STRUCTURE") / 2), "STRUCTURE", Color.darkGray);
      if (gc.getInput().isMousePressed(0)) {
        Toggles.SAND_ACTIVE = false;
        Toggles.GRAVEL_ACTIVE = false;
        Toggles.STRUCTURE_ACTIVE = true;
        Toggles.ACID_ACTIVE = false;
        Toggles.GUNPOWDER_ACTIVE = false;
      } 
    } else {
      g.drawImage(structure, 736.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void acidButton(GameContainer gc, Graphics g) throws SlickException {
    Image acid = new Image("res/acid.png");
    if (getMouseIndexX(gc) > 825 && getMouseIndexX(gc) < 895 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((836 - this.ttf.getWidth("ACID") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("ACID") / 2), "ACID", Color.darkGray);
      if (gc.getInput().isMousePressed(0)) {
        Toggles.SAND_ACTIVE = false;
        Toggles.GRAVEL_ACTIVE = false;
        Toggles.STRUCTURE_ACTIVE = false;
        Toggles.ACID_ACTIVE = true;
        Toggles.GUNPOWDER_ACTIVE = false;
      } 
    } else {
      g.drawImage(acid, 836.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void resetButton(GameContainer gc, Graphics g) throws SlickException {
    Image resetArrow = new Image("res/resetArrow.png");
    if (getMouseIndexX(gc) > 925 && getMouseIndexX(gc) < 995 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((936 - this.ttf.getWidth("RESET") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("RESET") / 2), "RESET", Color.white);
      if (gc.getInput().isMousePressed(0))
        Toggles.RESET_ACTIVE = true; 
    } else {
      g.drawImage(resetArrow, 936.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void windButton(GameContainer gc, Graphics g) throws SlickException {
    Image windInactive = new Image("res/windInactive.png");
    Image windActive = new Image("res/windActive.png");
    if (getMouseIndexX(gc) > 1025 && getMouseIndexX(gc) < 1095 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((1036 - this.ttf.getWidth("WIND") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("WIND") / 2), "WIND", Color.darkGray);
      this.ttf.drawString((1036 - this.ttf.getWidth("(WIP)") / 2 + 24), (gc.getHeight() - 32 - this.ttf.getHeight("(WIP)") / 2), "(WIP)", Color.red);
      if (gc.getInput().isMousePressed(0))
        Toggles.WIND_ACTIVE = !Toggles.WIND_ACTIVE; 
    } else if (Toggles.WIND_ACTIVE) {
      g.drawImage(windActive, 1036.0F, (gc.getHeight() - 73));
    } else {
      g.drawImage(windInactive, 1036.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void windDirectionButton(GameContainer gc, Graphics g) throws SlickException {
    Image windRightArrow = new Image("res/windRightArrow.png");
    Image windLeftArrow = new Image("res/windLeftArrow.png");
    if (getMouseIndexX(gc) > 1125 && getMouseIndexX(gc) < 1195 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttfg.drawString((1136 - this.ttfg.getWidth("DIRECTION") / 2 + 24), (gc.getHeight() - 48 - this.ttfg.getHeight("DIRECTION") / 2), "DIRECTION", Color.darkGray);
      if (gc.getInput().isMousePressed(0))
        Toggles.WIND_DIRECTION = !Toggles.WIND_DIRECTION; 
    } else if (Toggles.WIND_DIRECTION) {
      g.drawImage(windRightArrow, 1136.0F, (gc.getHeight() - 73));
    } else {
      g.drawImage(windLeftArrow, 1136.0F, (gc.getHeight() - 73));
    } 
  }
  
  public void laserButton(GameContainer gc, Graphics g) throws SlickException {
    Image laserInactive = new Image("res/laserInactive.png");
    Image laserActive = new Image("res/laserActive.png");
    if (getMouseIndexX(gc) > 1225 && getMouseIndexX(gc) < 1295 && getMouseIndexY(gc) > 996.5D && getMouseIndexY(gc) < 1066.5D) {
      this.ttf.drawString((1236 - this.ttf.getWidth("LASER") / 2 + 24), (gc.getHeight() - 48 - this.ttf.getHeight("LASER") / 2), "LASER", Color.darkGray);
      if (gc.getInput().isMousePressed(0))
        Toggles.LASER_ACTIVE = !Toggles.LASER_ACTIVE; 
    } else if (Toggles.LASER_ACTIVE) {
      g.drawImage(laserActive, 1236.0F, (gc.getHeight() - 73));
    } else {
      g.drawImage(laserInactive, 1236.0F, (gc.getHeight() - 73));
    } 
  }
}
