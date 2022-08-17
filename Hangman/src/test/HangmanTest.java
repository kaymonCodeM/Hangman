import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    Hangman hangman;
    @BeforeEach
    void setUp() {
        hangman = new Hangman();
    }

    @DisplayName("Test User Stored into score file")
    @Test
    void storeUser(){
        hangman.setUserName("Stanly");
        hangman.setBadGuesses(5);
        hangman.storeUser();
        String s ="";
        try{
            s += Files.readString(Path.of("Hangman/resources/score.txt"));
        }catch (Exception e){
            System.out.println("File not found");
        }
        assertTrue(s.contains(hangman.getUserName()), "Store user failed");
    }

    @DisplayName("User name test")
    @Test
    void greatUser(){
        InputStream input = new ByteArrayInputStream("Tom".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.greetUser();
        assertEquals(0,hangman.getUserName().compareTo("Tom"),"User name Test Failed");
    }

    @DisplayName("User has top score")
    @Test
    void getBestPlayer(){
        hangman.setBadGuesses(0);
        String result = hangman.getBestPlayer();
        assertEquals(0, result.compareTo("Great Job! You have the Best Score with " + 0 + " guesses!" + "\n"), "Best Score Failed");
    }

    @DisplayName("Test YES play again")
    @Test
    void yesPlayAgain(){
        InputStream input = new ByteArrayInputStream("yes".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.playAgain();
        assertTrue(hangman.getPlayGame(), "Test YES play again failed");
    }

    @DisplayName("Test NO play again")
    @Test
    void noPlayAgain(){
        InputStream input = new ByteArrayInputStream("no".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.playAgain();
        assertFalse(hangman.getPlayGame(), "Test NO play again failed");
    }

    @DisplayName("Test made correct guess (userBank)")
    @Test
    void makeGuessCorrectUserBank(){
        hangman.setWord("dog");
        hangman.setUserBank(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("o".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.makeGuess();
        assertTrue(new String(hangman.getUserBank()).contains("o"), "Correct Guess failed (userBank)");
    }

    @DisplayName("Test made bad guess (userBank)")
    @Test
    void makeGuessBadUserBank(){
        hangman.setWord("dog");
        hangman.setUserBank(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("n".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.makeGuess();
        assertFalse(new String(hangman.getUserBank()).contains("n"), "Made bad guess failed (userBank)");
    }

    @DisplayName("Test made correct guess (missedLetters)")
    @Test
    void makeGuessCorrectMissedLetters(){
        hangman.setWord("tank");
        hangman.setUserBank(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("a".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.makeGuess();
        assertFalse(hangman.getMissedLetters().contains('a'), "Correct guess failed (missedLetters)");
    }

    @DisplayName("Test made bad guess (missedLetters)")
    @Test
    void makeGuessBadMissedLetters(){
        hangman.setWord("tank");
        hangman.setUserBank(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("b".getBytes());
        hangman.setUserInput(new Scanner(input));
        hangman.makeGuess();
        assertTrue(hangman.getMissedLetters().contains('b'), "Bad guess failed (missedLetters)");
    }

    @AfterEach
    void tearDown(){
        if (hangman.getUserInput()!=null){
            hangman.closeScanner();
        }
    }
}