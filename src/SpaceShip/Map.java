/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceShip;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 
 * @author darolt
 */
public class Map {
  
  private final int[] WATER_VALUE = {0, 0, 0};
  private final int[] MINERAL_VALUE = {0, 0, 0};
  private final int[] GAS_VALUE = {0, 0, 0};
  
  private final Image background;
  private final BufferedImage buffer;
  
  
  public Map(String filepath) throws IOException {
    File backgroundFile = new File(filepath);
    ImageIcon backgroundIcon = new ImageIcon(filepath);
    background = backgroundIcon.getImage();
    buffer = ImageIO.read(backgroundFile);
  }
  
  public boolean isWater(int x, int y) {
    int[] pixel = getPixel(x, y);
    return comparePixels(pixel, WATER_VALUE);    
  }
  
  public Image getBackground() {
    return background;
  }

  public boolean isMineral(int x, int y) {
    int[] pixel = getPixel(x, y);
    return comparePixels(pixel, MINERAL_VALUE);    
  }

  public boolean isGas(int x, int y) {
    int[] pixel = getPixel(x, y);
    return comparePixels(pixel, GAS_VALUE);
  }
  
  private int[] getPixel(int x, int y) {
    int p = buffer.getRGB(0, 0);
    int a = (p>>24) & 0xff;
    int r = (p>>16) & 0xff;
    int g = (p>>8) & 0xff;
    int b = p & 0xff;
    return new int[]{r, g, b};
  }//compara pixeis == azul no mapa antigo, alterar para infomar [ x , y] de maeira
  //que quando == [ x, y ] retorne true(para informar na hora de minerar se existe minerio)
  //alterar juntamente com isMineral e is Gas
  
  private boolean comparePixels(int[] pixel1, int[] pixel2) {
    return (pixel1[0] == pixel2[0] &&
            pixel1[1] == pixel2[1] &&
            pixel1[2] == pixel2[2]);
  }
}
