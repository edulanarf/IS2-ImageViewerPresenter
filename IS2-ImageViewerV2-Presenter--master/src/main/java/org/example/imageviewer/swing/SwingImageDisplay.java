package org.example.imageviewer.swing;

import org.example.imageviewer.Image;
import org.example.imageviewer.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Paint> paints = new ArrayList<>();
    private List<BufferedPaints> bufferedPaints = new ArrayList<>();
    private BufferedImage bitmap;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void paint(String id, int offset, BufferedImage bitmap) {
        bufferedPaints.add(new BufferedPaints(bitmap, offset, id));
        repaint();
    }


    @Override
    public void clear() {
        paints.clear();
    }


    @Override
    public void paint(Graphics g) {
        for(BufferedPaints images: bufferedPaints){
            g.fillRect(images.offset, 0, this.getWidth(), this.getHeight());
            int x = images.offset + (this.getWidth() - images.bitmap.getWidth()) / 2;
            int y = (this.getHeight() - images.bitmap.getHeight()) / 2;
            g.drawImage(images.bitmap, x, y, null);

        }
    }



    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    private record Paint(String id, int offset) {
    }

    private record BufferedPaints(BufferedImage bitmap, int offset, String id){

    }


}
