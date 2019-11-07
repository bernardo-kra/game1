package SpaceShip;


public class Probe extends Sprite {

    public Probe(int x, int y) {
        super(x, y);
        
        initProbe();
    }

    private void initProbe() {
        loadImage("src/resources/probe.png"); 
        getImageDimensions();
    }

}