package it.polimi.ingsw.model.enumeration;

public enum PawnColor {
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    PINK("\u001B[35m"),
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),

    RESET("\u001B[0m");

    private String escape;

    PawnColor(String escape) {
        this.escape = escape;
    }

    public String getEscape() {
        return escape;
    }

    @Override
    public String toString() {
        return escape;
    }
}
