/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceShip;

/**
 *
 * @author berna
 */
public class Ore extends Sprite{
    public final int crystalOre = 5000;
    public int x;
    public int y;
    
    public Ore(int x, int y) {
        super(x , y);
        
        initOre();
    }

    private void initOre() {
        loadImage("src/resources/cryst.png");
        getImageDimensions();
    }

    @Override
    public String toString() {
        return "Crystal{" + "crystalOre=" + crystalOre + '}';
    }
   

}