package org.example.imageviewer.swing;

import org.example.imageviewer.Image;
import org.example.imageviewer.ImagePresenter;
import org.example.imageviewer.mock.MockImageLoader;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.setVisible(true);
    }

    private static Image image() {
        return new MockImageLoader().load();
    }
}