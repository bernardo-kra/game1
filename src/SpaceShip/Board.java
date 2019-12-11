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

        objects.add(new Ore(900, 10));
        objects.add(new Ore(900, 50));
        objects.add(new Ore(900, 100));
        objects.add(new Ore(50, 600));
        objects.add(new Ore(50, 650));
        objects.add(new Ore(50, 700));
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
    int conta = 0;

    private void play() {

        System.out.println("Informe Player 1 e o Player 2");
        String key = scanner.nextLine();
        Player p1 = new Player(key);

        key = scanner.nextLine();
        Player p2 = new Player(key);
        System.out.println(p1.nome + "\n" + p2.nome);

        Construc c1 = new Construc(50, 50);
        c1.setDono(p1.nome);
        p1.arrayPlayer.add(c1);
        Construc c2 = new Construc(750, 600);
        c2.setDono(p2.nome);
        p2.arrayPlayer.add(c2);
        objects.add(c1);
        objects.add(c2);

        Probe pr1 = new Probe(150, 150);
        pr1.setDono(p1.nome);
        p1.arrayPlayer.add(pr1);
        Probe pr2 = new Probe(700, 550);
        pr2.setDono(p2.nome);
        p2.arrayPlayer.add(pr2);

        objects.add(pr1);
        objects.add(pr2);
        Player playerAtual = p1;

        while (true) {
            
            int op;

            System.out.println("************** Player Atual [ " + playerAtual.nome + " ] ************** \n"
                    + "Escolha uma Operação \n"
                    + "1:Listar Todos objetos do Player: " + playerAtual.nome + "\n"
                    + "2:Listar Todos os Minerios" + "\n"
                    + "3:Tropas" + "\n"
                    + "4:Quartel"
            );
            op = scanner.nextInt();

            switch (op) {

                case 1:
                    System.out.println("");

                    for (Sprite o : objects) {
                        conta++;
                        if (o instanceof Probe) {
                            if (((Probe) o).getDono().equals(playerAtual.nome)) {
                                System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                            }
                        }
                        if (o instanceof Construc) {
                            if (((Construc) o).getDono().equals(playerAtual.nome)) {
                                System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                            }
                        }
                    }
                    conta = 0;

                    System.out.println("Informe uma ação\n"
                            + "1: Novo Probe\n"
                            + "2: Atacar\n"
                            + "3: Coletar");

                    op = scanner.nextInt();
                    switch (op) {

                        case 1:
                            Construc nexus = (Construc) playerAtual.arrayPlayer.get(0);
                            if (nexus.bagNexus >= 50) {

                                if (playerAtual == p1) {
                                    Probe p = new Probe(150, 150);
                                    p.setDono(playerAtual.nome);
                                    playerAtual.arrayPlayer.add(p);
                                    objects.add(p);
                                    nexus.bagNexus -= 50;
                                } else {
                                    Probe p = new Probe(600, 600);
                                    p.setDono(playerAtual.nome);
                                    playerAtual.arrayPlayer.add(p);
                                    objects.add(p);
                                    nexus.bagNexus -= 50;

                                }
                            } else {
                                System.out.println("Não possui Cristais suficiente");
                            }

                            break;
                        case 2:
                            
                            conta = 0;
                            
                            int guardaPosicaoX,
                             guardaPosicaoY;
                            System.out.println("Informe um Probe para Atacar");
                            for (Sprite o : objects) {
                                conta++;
                                if (o instanceof Probe) {
                                    if (((Probe) o).getDono().equals(playerAtual.nome)) {
                                        System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                                    }
                                }

                            }
                            if(conta == 0){
                                System.out.println("Não há tropas!!!");
                                break;
                            }
                            conta = 0;
                            op = scanner.nextInt();
                            Probe probeAtaca = (Probe) objects.get(op);

                            System.out.println("Indique um Probe para ser atacado:");
                            for (Sprite o : objects) {
                                conta++;
                                if (o instanceof Probe) {
                                    if (!((Probe) o).getDono().equals(playerAtual.nome)) {
                                        System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                                    }
                                }
                            }
                            
                            conta = 0;

                            int a = scanner.nextInt();
                            Probe probeAtacado = (Probe) objects.get(a);

                            probeAtacado.lifeProbe -= 20;

                            guardaPosicaoX = probeAtaca.x;
                            guardaPosicaoY = probeAtaca.y;

                            probeAtaca.x = probeAtacado.x;

                            probeAtaca.y = probeAtacado.y;

                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                            }

                            probeAtaca.x = guardaPosicaoX;
                            probeAtaca.y = guardaPosicaoY;
                            
                            
                            
                            if (probeAtacado.lifeProbe<=0) {
                                Probe probeMorre = probeAtacado;
                                if(playerAtual.nome != probeMorre.dono){
                                    objects.remove(probeMorre);
                                    
                                }
                                
                            }
                            
                            

                            break;
                        case 3: ///
                            conta = 0;
                            System.out.println("Indique qual cristal deseja coletar:");
                            for (Sprite o : objects) {
                                conta++;
                                if (o instanceof Ore) {
                                    System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                                }
                            }

                            conta = 0;

                            op = scanner.nextInt();
                            Ore ore = (Ore) objects.get(op);

                            if (ore.quantidadeCrystal > 0) {
                                ore.quantidadeCrystal = ore.quantidadeCrystal - 50;

                                System.out.println("Qual probe vai coletar:");
                                for (Sprite o : objects) {
                                    conta++;
                                    if (o instanceof Probe) {
                                        if (((Probe) o).getDono().equals(playerAtual.nome)) {
                                            System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                                        }
                                    }
                                }

                                int selcprobe = scanner.nextInt();
                                Probe p = (Probe) objects.get(selcprobe);
                                p.bagProbe = p.bagProbe + 50;
                                p.x = ore.x;
                                p.y = ore.y;

                                Construc nexus1 = (Construc) playerAtual.arrayPlayer.get(0);

                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex) {
                                }
                                nexus1.bagNexus = p.bagProbe;

                                p.x = nexus1.posicaoNexusX;
                                p.y = nexus1.posicaoNexusy;

                                p.bagProbe = 0;

                            } else {
                                System.out.println("Não possui mais cristais");
                            }
                            conta = 0;

                            break;
                    }

                case 2:

                    break;

                case 3:

                    break;

            }
            playerAtual = (playerAtual == p1) ? p2 : p1;
        }
    }
        /* 
  key = scanner.nextLine();
            if ("probe".equals(key) || "ls".equals(key)) {
                for (Sprite o : objects) {
                    conta++;
                    if (o instanceof Probe) {
                        if (((Probe) o).getDono().equals(playerAtual.nome)) {

                            System.out.println("Arraylist[ " + (conta - 1) + " ] = " + o);
                        }
                    }
                }
                conta = 0;




int conta = 0;
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
}
