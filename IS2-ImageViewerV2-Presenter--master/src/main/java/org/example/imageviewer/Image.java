package org.example.imageviewer;

import java.awt.image.BufferedImage;

public interface Image {
    String id();
    Image next();
    Image prev();

}
