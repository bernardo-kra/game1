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

public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private int focus_shift_x = 0;
    private int focus_shift_y = 0;
    private final int SPEED = 2;
    private List<Sprite> objects = new ArrayList<>();//Sprite projecao na tela
    private Map map;
    private final Scanner scanner;

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

        objects.add(new Probe(ICRAFT_X, ICRAFT_Y));
        objects.add(new Construc(500, 150));

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

    private void play() {
        while (true) {
            System.out.println("Deseja criar mais um Probe S/N");
            String key = scanner.nextLine();
            if ("S".equals(key)) {
                objects.add(new Probe(200, 350));

            }

        }
    }

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
