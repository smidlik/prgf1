package ui;
import utils.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
                super.mouseClicked(e);
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

        renderer.lineDDA(300,300, coorX, coorY);


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