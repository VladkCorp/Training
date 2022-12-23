package com.Udm1.FileStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SearchFilesByExtension {

    public long getNumberOfFilesWithExtension(Path pathToStartSearch, String extension) throws IOException {
        if (pathToStartSearch == null
                || extension == null
        || !extension.contains(".")) {
            return 0;
        }
        try {
            Stream<Path> filteredPath = Files.walk(Paths.get(pathToStartSearch.toString()))
                        .filter(p -> p.toString().endsWith(extension))
                        .map(p -> p.getParent().getParent());
                return filteredPath.count();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
