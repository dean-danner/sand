package substances;

import core.Point;
import org.newdawn.slick.Color;

public class Gravel extends Substance {
  public Gravel(int value) {
    super(value);
    this.substance = true;
    int ran = (int)(Math.random() * 150.0D);
    setColor(new Color(55 + ran, 55 + ran, 55 + ran));
  }
  
  public Substance getSubstance() {
    return new Gravel(this.value);
  }
  
  public Point moveNorm(int r, int c) {
    return new Point(r, c + 1);
  }
}
