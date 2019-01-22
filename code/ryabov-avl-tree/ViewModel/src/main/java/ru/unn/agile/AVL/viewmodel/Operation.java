package ru.unn.agile.AVL.viewmodel;

public enum Operation {
    INSERT("Insert"),
    SEARCH("Search");

    private final String name;

    Operation(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
