package SpaceShip;


public class Construc extends Sprite {

    public Construc(int x, int y) {
        super(x, y);
        
        initConstruc();
    }

    private void initConstruc() {
        loadImage("src/resources/nexus.png"); 
        getImageDimensions();
    }

}