package com.purkynka.paintpp.logic.pathmanager;

public record TypedPath(PathType pathType, java.nio.file.Path path) {
    public TypedPath newPath(java.nio.file.Path newPath) {
        return new TypedPath(this.pathType, newPath);
    }
}
