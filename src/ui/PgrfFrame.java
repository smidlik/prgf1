package ui;
import utils.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class PgrfFrame extends JFrame implements MouseMotionListener {

    static int FPS = 1000/60;
    private BufferedImage img; // pro vykreslovani
    static int width = 800;
    static int height = 600;
    private JPanel panel; // musime pridat panel, protoze to nevykreslovalo
    private Renderer renderer;
    private int coorX, coorY;
    private  int clicX = 300, clicY = 300;
    private  int count = 3;


    public static void main(String... args) {
        PgrfFrame pgrfFrame = new PgrfFrame();
        pgrfFrame.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // Prideleni ARGB barev
        pgrfFrame.init(width,height);
    }

    // Inicializace vykresleni
    private void init(int width, int height){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width,height);
        setTitle("Počítačová grafika");

        panel = new JPanel();
        add(panel);


        panel.addMouseMotionListener(this);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicX = e.getX();
                clicY = e.getY();
                super.mouseClicked(e);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_UP)//sipka nahoru
                {
                    count++;
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)//sipka dolu
                {
                    if(count>3)count--;
                }
                if(e.getKeyCode()==KeyEvent.VK_ADD)//plus na num klavesnici
                {

                }
                super.keyReleased(e);
            }
        });

        setLocationRelativeTo(null);

        renderer = new Renderer(img);

        Timer timer = new Timer(); // timer pro obnoveni toho vykresleni a ted uz to funguje
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        }, 100, FPS);
        draw();

    }

    // vykresleni
    private void draw(){
        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight()); // prideleni pozadi

        renderer.lineDDA(clicX,clicY, coorX, coorY);
        renderer.polygon(clicX,clicY,coorX,coorY,count);


        panel.getGraphics().drawImage(img, 0,0,img.getWidth(), img.getHeight(), null); // zde ji to vykresli
        panel.paintComponents(getGraphics());
    }

    @Override
    public void mouseDragged(MouseEvent e) { // v hlavicce mousemotionlisenner a dat CTRL+I a tyto 2 tridy se udelaji samy

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        coorX = e.getX(); // zjisti kde byla naposledy mys a ulozi jeji misto (pixely)
        coorY = e.getY();
    }
}