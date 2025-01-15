package org.example.imageviewer.persistence;

import org.example.imageviewer.Image;
import org.example.imageviewer.ImageLoader;
import java.util.Set;
import java.io.File;
import java.io.FilenameFilter;

public class FolderImageLoader implements ImageLoader {

    private final File[] ids;

    public FolderImageLoader(File folder) {
        this.ids = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg",".png");

    private FilenameFilter isImage() {
        return (dir,name)-> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return ids[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % ids.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i -1 : ids.length-1);
            }
        };
    }
}
