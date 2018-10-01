package ui;

import utils.Renderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class PgrfFrame extends JFrame implements MouseMotionListener {
    private int FPS =1000/60;

    private BufferedImage img;
    static int width = 600;
    static int height = 800;

    private int corX,corY;

    private JPanel panel;
    private  Renderer renderer;

    public static void main(String... args) {

        PgrfFrame pgrfFrame = new  PgrfFrame();
        pgrfFrame.img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        pgrfFrame.init(width,height);


    }
    private void  init(int height, int width){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width,height);
        setTitle("PRGF");

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



        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                draw();

            }
        },100,FPS);

        draw();
    }



    private void draw()
    {

        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight());

        renderer.lineTrivial(300,300, corX,corY);


        panel.getGraphics().drawImage(img,0,0,img.getWidth(),img.getHeight(),null);
        panel.paintComponents(getGraphics());


    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        corX = e.getX();
        corY=  e.getY();
    }
}

