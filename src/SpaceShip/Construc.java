package SpaceShip;

public class Construc extends Sprite {

    Board sp;
    public int posicaoNexusX;
    public int posicaoNexusy;
    int i = 0;
    public int bagNexus = 50;
    public int lifeNexus = 20;
    public String dono;

    public Construc(int x, int y) {
        super(x, y);
        this.posicaoNexusX = x;
        this.posicaoNexusy = y;
        initConstruc();
    }

    private void initConstruc() {
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
        return "Nexus{" + "Quantidade de Crystal=" + bagNexus + ", Vida= " + lifeNexus + ", dono=" + dono + '}';
    }

   
}
