package substances;

import core.Point;
import org.newdawn.slick.Color;

public class Sand extends Substance {
  public Sand(int value) {
    super(value);
    this.substance = true;
    int ranR = (int)(Math.random() * 55.0D);
    int ranG = (int)(Math.random() * 55.0D);
    setColor(new Color(200 + ranR, 200 + ranG, 0));
  }
  
  public Substance getSubstance() {
    return new Sand(this.value);
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
