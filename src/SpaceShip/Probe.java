package SpaceShip;

public class Probe extends Sprite {

    public int lifeProbe = 20;
    public int bagProbe;
   // public int custoProbe = 20;
    public String dono;

    public Probe(int x, int y) {
        super(x, y);

        initProbe();
    }

    private void initProbe() {
        loadImage("src/resources/probe.png");
        getImageDimensions();
    }

    @Override
    public String toString() {
        return ("Probe Life [ " + String.valueOf(lifeProbe) + " ] - " + " Dono: " + this.dono + " !  Crystal [" + String.valueOf(bagProbe) + "]");
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
    
    
}
