package org.example.imageviewer;
import org.example.imageviewer.ImageDisplay.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;
    private BufferedImage bitmap;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
    }

    private void shift(int offset) {
        display.clear();
        display.paint(image.id(), offset, bitmap);
        if (offset > 0) {
            display.paint(image.prev().id(), offset - display.getWidth(), bitmap);
        }
        else {
            display.paint(image.next().id(), display.getWidth() + offset, bitmap);
        }
    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2)
            image = offset > 0 ? image.prev() : image.next();
        repaint();
    }

    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.id());
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(image.id(), 0,bitmap);

    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}