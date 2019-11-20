package SpaceShip;

public class Construc extends Sprite {

    Board sp;
    public int posicaoNexusX;
    public int posicaoNexusy;
    int i = 0;
    public int bagNexus;
    public int lifeNexus = 5000;

    public Construc(int x, int y) {
        super(x, y);
        initConstruc();
    }

    private void initConstruc() {
        loadImage("src/resources/nexus.png");
        getImageDimensions();
    }

  

    @Override
    public String toString() {
        return ("Nexus Life [ " + String.valueOf(lifeNexus) + " ] - " + " Crystal [" + String.valueOf(bagNexus) + "]");
    }
}
