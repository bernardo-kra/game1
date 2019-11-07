package SpaceShip;


public class SpaceShip extends Sprite {

    public SpaceShip(int x, int y) {
        super(x, y);
        
        initSpaceShip();
    }

    private void initSpaceShip() {
        loadImage("src/resources/Fighter_mode.gif"); 
        getImageDimensions();
    }

}