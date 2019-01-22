package ru.unn.agile.ConwayGame.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConwayGameTest {

    @Test
    public void canCreateGameInstance() {
        ConwayGame game = new ConwayGame(4, 8);

        assertNotNull(game);
    }

    @Test
    public void canGetRowsNumber() {
        ConwayGame game = new ConwayGame(7, 4);

        assertEquals(7, game.getPlaygroundRowsNumber());
    }

    @Test
    public void canGetColumnsNumber() {
        ConwayGame game = new ConwayGame(3, 5);

        assertEquals(5, game.getPlaygroundColumnsNumber());
    }

    @Test
    public void canGetPlaygroundSize() {
        ConwayGame game = new ConwayGame(4, 2);

        assertEquals(8, game.getPlaygroundRowsNumber() * game.getPlaygroundColumnsNumber());
    }

    @Test
    public void canGetGivenGeneration() {
        ConwayGame game = new ConwayGame(2, 3);

        game.setGeneration("..**..");

        assertEquals("..*\n*..\n", game.getGeneration());
    }

    @Test
    public void canWorkWithIncorrectInput() {
        ConwayGame game = new ConwayGame(-2, 3);

        assertEquals(2, game.getPlaygroundRowsNumber());
    }

    @Test
    public void canGetNextGeneration() {
        ConwayGame game = new ConwayGame(4, 8);

        game.setGeneration("............*......**...........");
        game.moveToNextGeneration();

        assertEquals("........\n...**...\n...**...\n........\n", game.getGeneration());
    }

    @Test
    public void canGetNextGenerationSmallPlayground() {
        ConwayGame game = new ConwayGame(1, 1);

        game.setGeneration("*");
        game.moveToNextGeneration();

        assertEquals(".\n", game.getGeneration());
    }

    @Test
    public void canGetNextGenerationFilledPlayground() {
        ConwayGame game = new ConwayGame(2, 2);

        game.setGeneration("****");
        game.moveToNextGeneration();

        assertEquals("**\n**\n", game.getGeneration());
    }
}
