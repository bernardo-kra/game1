package SpaceShip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private int focus_shift_x = 0;
    private int focus_shift_y = 0;
    private final int SPEED = 2;
    public List<Sprite> objects = new ArrayList<>();//Sprite projecao na tela
    private Map map;
    private final Scanner scanner;
    public int hp;
    public int crystal;
    public final int nexusX = 100;
    public final int nexusY = 100;
    int c, q = 0, alteraX, alteraY = 0, leInteiro = 0;
    Boolean x;
    String trash;

    public Board() {
        scanner = new Scanner(System.in);
        initBoard();//criacao main
    }

    private void initBoard() {
        try {
            map = new Map("src/resources/map.png");
        } catch (IOException e) {
            System.out.println("Map file not found. Loading black background");
        }
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        objects.add(new Construc(100, 100));

        objects.add(new Ore(800, 192));
      //  objects.add(new Probe(110, 105));

        timer = new Timer(DELAY, this);
        timer.start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                play();
            }
        });
        t1.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(map.getBackground(), focus_shift_x, focus_shift_y, null);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Sprite object : objects) {
            g2d.drawImage(object.getImage(), object.getX() + focus_shift_x,
                    object.getY() + focus_shift_y, this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
 int conta=0;
    private void play() {
       
    
        System.out.println("Informe Player 1 e o Player 2");
        String key = scanner.nextLine();
        Player p1 = new Player(key);
        key = scanner.nextLine();

        Player p2 = new Player(key);
        System.out.println(p1.nome + "\n" + p2.nome);
        Player playerAtual = p1;
        
        while (true) {
            Probe p = new Probe(nexusX+455, nexusY+123);
            playerAtual.arrayPlayer.add(p);
            objects.add(p);
            key = scanner.nextLine();
              if ("probe".equals(key) || "ls".equals(key)) {
                for (Sprite o : objects) {
                    conta++;
                    if (o instanceof Probe) {
                        System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                    }
                }
                conta=0;
            
            
            playerAtual = (playerAtual == p1)? p2:p1;
            
            

            }

        }
    }
        /* int conta = 0;
        System.out.println("Informar probe");

        while (true) {
            String key = scanner.nextLine();

            if ("probe".equals(key) || "ls".equals(key)) {
                for (Sprite o : objects) {
                    conta++;
                    if (o instanceof Probe) {
                        System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                    }
                }
                System.out.println("Deseja mover o Probe ? S/n");
                key = scanner.nextLine();
                if ("S".equals(key) || "s".equals(key)) {
                    System.out.println("Qual probe deseja mover ?");
                    key = scanner.nextLine();
                    q = Integer.parseInt(key);

                    if (q != 0 && objects.get(q) instanceof Probe) {
                        Probe p = (Probe) objects.get(q);
                        System.out.println("Informe a posição X");
                        key = scanner.nextLine();
                        alteraX = Integer.parseInt(key);
                        p.x = alteraX;
                        System.out.println("Informe a posição Y");
                        key = scanner.nextLine();
                        alteraY = Integer.parseInt(key);
                        p.y = alteraY;
                        System.out.println("Moveu");
                    } else {
                        System.out.println("Nao ha um probe nesta posição");
                    }
                }

                conta = 0;
            }
            if ("n".equals(key) || "N".equals(key)) {
                for (Sprite o : objects) {
                    conta++;
                    if (o instanceof Construc) {
                        System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                        int t;
                    }
                }
                System.out.println("Deseja criar um novo Probe ? S/N");
                String key1 = scanner.nextLine();
                conta = 0;
                if ("S".equals(key1) || "s".equals(key1)) {
                    criaProbe();
                    for (Sprite o : objects) {
                        conta++;
                        c++;
                        if (o instanceof Probe) {
                            System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                        }
                    }
                    conta = 0;
                

}
            }

        }
    }

       
                                    //                     

            int conta = 0;

           
            }if ("S".equals(key) || "s".equals(key)) {
                objects.add(new Probe(350, 300));

            }if ("N".equals(key)) {
               
               Probe p = (Probe) objects.get(0);
               p.x = 333;
               p.y = 333;
            }
         */
    
/*
            if (playerAtual == p1) {
                playerAtual = p2;
                        
            }else{
                playerAtual = p1;
            }
*/
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                focus_shift_x += SPEED;
            }

            if (key == KeyEvent.VK_RIGHT) {
                focus_shift_x -= SPEED;
            }

            if (key == KeyEvent.VK_UP) {
                focus_shift_y += SPEED;
            }

            if (key == KeyEvent.VK_DOWN) {
                focus_shift_y -= SPEED;
            }
        }
    }

    public void criaProbe() {
        int conta = 5;

        objects.add(new Probe(nexusX + (2 * c), nexusY + 40 - (conta * c)));
        conta++;
        System.out.println(conta);
    }

}
