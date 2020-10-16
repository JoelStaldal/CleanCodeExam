package model.test;

import model.Game;
import model.GameStrategy;
import model.MooGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GameTest {

    Game game;
    GameStrategy gameType;

    @BeforeEach
    void setup() {
        game = new Game();
        gameType = mock(GameStrategy.class);
    }

    //@Test
    void testSetGameType() {

    }

    //@Test
    void testCheckBullsAndCows() {

    }

    @Test
    void testGenerateRandomNumber() {
        when(gameType.generateRandomNumber()).thenReturn("1234");
        String actual = gameType.generateRandomNumber();
        String expResult = "1234";
        assertEquals(expResult, actual);
    }

    @Test
    void testValidateGuessTrue() {
        when(gameType.validGuess("1234")).thenReturn(true);
        boolean actual = gameType.validGuess("1234");
        assertTrue(actual);
    }

    @Test
    void testValidateGuessFalse() {
        when(gameType.validGuess("msdfghj")).thenReturn(false);
        boolean actual = gameType.validGuess("msdfghj");
        assertFalse(actual);
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
        assertEquals(actual, expResult);
    }

    @Test
    void testSetGuessWithString1234() {
        game.setGuess("1234");
        String actual = game.getGuess();
        String expResult = "1234";
        assertEquals(actual, expResult);

        int actualCount = game.getGuessCount();
        int expCountResult = 1;
        assertEquals(actualCount, expCountResult);
    }

    @Test
    void testGetGoal() {
        String actual = game.getGoal();
        assertNull(actual);
    }
}