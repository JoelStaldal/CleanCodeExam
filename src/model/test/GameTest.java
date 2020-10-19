package model.test;

import model.Game;
import model.GameStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class GameTest {

    Game game;
    GameStrategy gameType;

    @BeforeEach
    void setup() {
        game = new Game();
        gameType = mock(GameStrategy.class);
        game.setGameType(gameType);
    }

    @Test
    void testCheckBullsAndCows() {
        when(gameType.checkBullsAndCows(nullable(String.class), nullable(String.class))).thenReturn("BBBB,");
        String actual = game.checkBullsAndCows();
        String expResult = "BBBB,";
        assertEquals(expResult, actual);
        verify(gameType).checkBullsAndCows(nullable(String.class), nullable(String.class));
    }

    @Test
    void testGenerateRandomNumber() {
        when(gameType.generateRandomNumber()).thenReturn("1234");
        game.generateRandomNumber();
        String actual = game.getGoal();
        String expResult = "1234";
        assertEquals(expResult, actual);
        verify(gameType).generateRandomNumber();
    }

    @Test
    void testValidateGuessReturnValueTrue() {
        when(gameType.validateGuess("1234")).thenReturn(true);
        boolean actual = game.validateGuess("1234");
        assertTrue(actual);
        verify(gameType).validateGuess("1234");
    }

    @Test
    void testValidateGuessReturnValueFalse() {
        when(gameType.validateGuess("lorem")).thenReturn(false);
        boolean actual = game.validateGuess("lorem");
        assertFalse(actual);
        verify(gameType).validateGuess("lorem");
    }

    @Test
    void testGetGuessCount() {
        int actual = game.getGuessCount();
        int expResult = 0;
        assertEquals(expResult, actual);
    }

    @Test
    void testSetGuessCount() {
       game.setGuessCount(5);
       int actual = game.getGuessCount();
       int expResult = 5;
       assertEquals(expResult, actual);
    }

    @Test
    void testGetGuess() {
        game.setGuess("1234");
        String actual = game.getGuess();
        String expResult = "1234";
        assertEquals(expResult, actual);
    }

    @Test
    void testSetGuessWithString1234() {
        game.setGuess("1234");
        String actual = game.getGuess();
        String expResult = "1234";
        assertEquals(expResult, actual);

        int actualCount = game.getGuessCount();
        int expCountResult = 1;
        assertEquals(expCountResult, actualCount);
    }

    @Test
    void testGetGoal() {
        String actual = game.getGoal();
        assertNull(actual);
    }
}