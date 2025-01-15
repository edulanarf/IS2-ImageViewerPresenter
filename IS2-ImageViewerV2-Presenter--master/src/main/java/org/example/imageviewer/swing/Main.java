package org.example.imageviewer.swing;

import org.example.imageviewer.Image;
import org.example.imageviewer.ImagePresenter;
import org.example.imageviewer.mock.MockImageLoader;
import org.example.imageviewer.persistence.FolderImageLoader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.setVisible(true);
    }

    private static Image image() {
        return new FolderImageLoader(new File("images")).load();
    }
}