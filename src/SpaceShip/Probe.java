package SpaceShip;


public class Probe extends Sprite {
    public int lifeProbe;
    public int bagProbe;
    public final int custoProbe = 20;
    
    public Probe(int x, int y) {
        super(x, y);
        
        initProbe();
    }
    
    private void initProbe() {
        loadImage("src/resources/probe.png"); 
        getImageDimensions();
    }
    
    @Override
    public String toString(){
        return ("Probe Life [ " + String.valueOf(lifeProbe) + " ] - "+" Crystal ["+String.valueOf(bagProbe)+"]");
    }

}