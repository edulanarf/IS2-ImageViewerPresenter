package org.example.imageviewer;

public interface Image {
    String id();
    Image next();
    Image prev();

}
