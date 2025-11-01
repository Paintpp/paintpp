package com.purkynka.paintpp.logic.pathmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;


public class PathManager {
    private static final String applicationName = "paintpp";
    private static final String os = System.getProperty("os.name").toLowerCase();
    private static final Map<PathType, TypedPath> paths = new EnumMap<>(PathType.class);

    static {
        for (PathType type : PathType.values()) {
            TypedPath typedPath = switch (type) {
                case HOME_DIR -> getHomePath();
                case CONFIG_DIR -> getConfigPath();
                case PICTURES_DIR -> getPicturesPath();
                case LAST_SAVE_DIR, LAST_EXPORT_DIR -> getTypedPath(PathType.PICTURES_DIR);
                case LAST_SAVE_FILE, LAST_EXPORT_FILE -> null;
            };
            paths.put(type, typedPath);
        }
    }

    private static TypedPath getHomePath() {
        return new TypedPath(PathType.HOME_DIR, Paths.get(System.getProperty("user.home")));
    }

    private static TypedPath getConfigPath() {
        Path path;
        if (os.equals("win")) {
            path = getPath(PathType.HOME_DIR).resolve(Path.of("AppData", "Roaming", applicationName));
        } else {
            path = getPath(PathType.HOME_DIR).resolve(Path.of(".config", applicationName));
        }

        CheckDirectoryExists(path);
        return new TypedPath(PathType.CONFIG_DIR, path);
    }

    private static TypedPath getPicturesPath() {
        Path path = getPath(PathType.HOME_DIR).resolve(Path.of("Pictures"));
        CheckDirectoryExists(path);
        return new TypedPath(PathType.CONFIG_DIR, path);
    }

    private static void CheckDirectoryExists(java.nio.file.Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static TypedPath getTypedPath(PathType type) {
        return paths.get(type);
    }

    public static Path getPath(PathType type) {
        return paths.get(type).path();
    }

    public static void setLastSavePath(java.nio.file.Path path) {
        CheckDirectoryExists(path);
        paths.get(PathType.LAST_SAVE_FILE).newPath(path);
    }

    public static void setLastExportPath(java.nio.file.Path path) {
        CheckDirectoryExists(path);
        paths.get(PathType.LAST_EXPORT_FILE).newPath(path);
    }
}
