package substances;

import core.Point;
import org.newdawn.slick.Color;

public class Gunpowder extends Substance {
  public Gunpowder(int value) {
    super(value);
    this.substance = true;
    int ranR = (int)(Math.random() * 50.0D);
    int ranG = (int)(Math.random() * 50.0D);
    int ranB = (int)(Math.random() * 50.0D);
    setColor(new Color(255 - ranR, 255 - ranG, 255 - ranB));
  }
  
  public Substance getSubstance() {
    return new Gunpowder(this.value);
  }
  
  public Point moveNorm(int r, int c) {
    return new Point(r, c + 1);
  }
  
  public Point moveResolve(int r, int c) {
    int ran = 1;
    if (Math.random() < 0.5D)
      ran = -1; 
    return new Point(r + ran, c + 1);
  }
  
  public Point downLeft(int r, int c) {
    return new Point(r - 1, c + 1);
  }
  
  public Point downRight(int r, int c) {
    return new Point(r + 1, c + 1);
  }
}
