package org.example.imageviewer;

import java.awt.image.BufferedImage;

public interface ImageDisplay {
    void paint(String id, int offset, BufferedImage image);

    int getWidth();


    void clear();

    void on(Shift shift);

    void on(Released released);

    interface Shift {
        Shift Null = offset -> {
        };

        void offset(int offset);
    }

    interface Released {
        Released Null = offset -> {
        };

        void offset(int offset);
    }
}
