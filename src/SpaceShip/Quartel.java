package SpaceShip;

public class Quartel extends Sprite {

    Board sp;
    
    public int lifeQuartel = 5000;
    public String dono;

    public Quartel(int x, int y) {
        super(x, y);
        initQuartel();
    }

    private void initQuartel() {
        loadImage("src/resources/nexus.png");
        getImageDimensions();
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
    

    @Override
    public String toString() {
        return "Quartel Vida= " + lifeQuartel + ", dono=" + dono + '}';
    }

   
}
