import org.junit.jupiter.api.*;

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
        String pole = "      |";
        String ground = "     ===";
        String head = "  O   |";
        String upperBody = "  |   |";
        String lowerBody = "  |   |";
        String leftLeg = " /";
        String leftRightLeg = " / \\";
        String leftArm = " \\";
        String leftRightArm = " \\ /";
        String result = top + "\n\n" + head + "\n"+ leftRightArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
        assertEquals(result,hangman.buildHangman(7),"Full Hangman Failed");
    }
}