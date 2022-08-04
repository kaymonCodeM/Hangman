import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    Hangman hangman;
    @BeforeEach
    void setUp() {
        hangman = new Hangman();
    }

    @DisplayName("Initiate Hangman")
    @Test
    void initiateHangman(){
        String top = "\n  +---+";
        String pole = "      |";
        String ground = "     ===";
        String result = top + "\n\n" + pole + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
        assertEquals(result,hangman.buildHangman(0),"Initiate Hangman Failed");
    }
    @DisplayName("Full Hangman")
    @Test
    void fullHangman(){
        String top = "\n  +---+";
        String ground = "     ===";
        String head = "  O   |";
        String upperBody = "  |   |";
        String lowerBody = "  |   |";
        String leftRightLeg = " / \\";
        String leftRightArm = " \\ /";
        String result = top + "\n\n" + head + "\n"+ leftRightArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
        assertEquals(result,hangman.buildHangman(7),"Full Hangman Failed");
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

    @DisplayName("Test made bad guess")
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