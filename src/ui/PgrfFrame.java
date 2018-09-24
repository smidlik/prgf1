package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PgrfFrame extends JFrame {
    private BufferedImage img;
    static int width = 600;
    static int height = 800;

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

        draw();
    }
    private void draw()
    {
        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight());
        for (int i = 0; i < 100; i++) {
            img.setRGB(200+i,200, Color.RED.getRGB());

        }
        getGraphics().drawImage(img,0,0,img.getWidth(),img.getHeight(),null);
    }
}

