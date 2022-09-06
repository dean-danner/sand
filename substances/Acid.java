package substances;

import core.Point;
import org.newdawn.slick.Color;

public class Acid extends Substance {
  public Acid(int value) {
    super(value);
    this.substance = true;
    int ranR = (int)(Math.random() * 25.0D);
    int ranG = (int)(Math.random() * 40.0D);
    int ranB = (int)(Math.random() * 30.0D);
    setColor(new Color(ranR, 200 + ranG, 100 + ranB));
  }
  
  public Substance getSubstance() {
    return new Acid(this.value);
  }
  
  public Point moveNorm(int r, int c) {
    return new Point(r, c + 1);
  }
}
