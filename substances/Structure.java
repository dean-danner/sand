package substances;

import org.newdawn.slick.Color;

public class Structure extends Substance {
  public Structure(int value) {
    super(value);
    this.substance = true;
    int ranR = (int)(Math.random() * 55.0D);
    int ranG = (int)(Math.random() * 55.0D);
    int ranB = (int)(Math.random() * 55.0D);
    setColor(new Color(200 + ranR, 0 + ranG, 0 + ranB));
  }
  
  public Substance getSubstance() {
    return new Structure(this.value);
  }
}
