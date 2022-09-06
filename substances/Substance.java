package substances;

import core.Point;
import org.newdawn.slick.Color;

public class Substance {
  protected int value;
  
  protected boolean substance;
  
  private Color c;
  
  int velocity = 0;
  
  int timer = 10;
  
  public Substance(int value) {
    this.value = value;
    this.substance = false;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public Substance getSubstance() {
    return null;
  }
  
  public Color getColor() {
    return this.c;
  }
  
  public void setColor(Color c) {
    this.c = c;
  }
  
  public boolean isSubstance() {
    return !(!(this instanceof Sand) && !(this instanceof Gravel) && !(this instanceof Structure) && !(this instanceof Gunpowder));
  }
  
  public boolean isAcid() {
    return this instanceof Acid;
  }
  
  public boolean isGunpowder() {
    return this instanceof Gunpowder;
  }
  
  public Point moveNorm(int r, int c) {
    return null;
  }
  
  public Point moveResolve(int r, int c) {
    return null;
  }
  
  public Point downLeft(int r, int c) {
    return null;
  }
  
  public Point downRight(int r, int c) {
    return null;
  }
  
  public Point windRight(int r, int c) {
    return new Point(r + 1, c + 1);
  }
  
  public Point windLeft(int r, int c) {
    return new Point(r - 1, c + 1);
  }
}
