package ru.unn.agile.ConwayGame.Model;

public class ConwayGame {
    private int rowsNumber;
    private int columnsNumber;
    private int stepsTaken;
    private boolean[] firstGeneration;
    private boolean[] nextGeneration;

    public static final int MIN_NEIGHBORS = 2;
    public static final int MAX_NEIGHBORS = 3;

    public ConwayGame(final int rows, final int columns) {
        if (rows > 0) {
            this.rowsNumber = rows;
        } else {
            this.rowsNumber = rows * (-1);
        }
        if (columns > 0) {
            this.columnsNumber = columns;
        } else {
            this.columnsNumber = columns * (-1);
        }
        this.firstGeneration = new boolean[rowsNumber * columnsNumber];
        this.nextGeneration = new boolean[rowsNumber * columnsNumber];
        this.stepsTaken = 0;
    }

    private boolean getCurrentValueInCell(final int i) {
        if (stepsTaken > 0) {
            return this.nextGeneration[i];
        } else {
            return this.firstGeneration[i];
        }
    }

    // count the number of neighbors
    private int countNeighbors(final int i) {
        int count = 0;
        int x = i % columnsNumber;
        int y = i / columnsNumber;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int nX = x + dx;
                int nY = y + dy;
                if (nX < 0 || nX > columnsNumber - 1) {
                    continue;
                }
                if (nY < 0 || nY > rowsNumber - 1) {
                    continue;
                }
                count += (firstGeneration[nY * columnsNumber + nX]) ? 1 : 0;
            }
        }
        if (firstGeneration[y * columnsNumber + x]) {
            count--;
        }
        return count;
    }

    public void moveToNextGeneration() {
        for (int i = 0; i < rowsNumber * columnsNumber; i++) {
            int count = countNeighbors(i);
            nextGeneration[i] = firstGeneration[i];
            // if are 3 live neighbors around empty cells - the cell becomes alive
            nextGeneration[i] = (count == MAX_NEIGHBORS || nextGeneration[i]);
            // if cell has less than 2 or greater than 3 neighbors - it will die
            nextGeneration[i] = !((count < MIN_NEIGHBORS) || (count > MAX_NEIGHBORS))
                    && nextGeneration[i];
        }
        this.stepsTaken++;
    }

    public int getPlaygroundRowsNumber() {
        return rowsNumber;
    }

    public int getPlaygroundColumnsNumber() {
        return columnsNumber;
    }

    public void setGeneration(final String generation) {
        if (generation.length() != rowsNumber * columnsNumber) {
            return;
        }
        for (int i = 0; i < generation.length(); i++) {
            if (generation.charAt(i) == '*') {
                firstGeneration[i] = true;
            } else {
                firstGeneration[i] = false;
            }
        }
    }

    public String getGeneration() {
        String currentGeneration = "";
        for (int i = 0; i < rowsNumber * columnsNumber; i++) {
            if (getCurrentValueInCell(i)) {
                currentGeneration += '*';
            } else {
                currentGeneration += '.';
            }
            if (i % columnsNumber == columnsNumber - 1) {
                currentGeneration += '\n';
            }
        }
        return currentGeneration;
    }
}
