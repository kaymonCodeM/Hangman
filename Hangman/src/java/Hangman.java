import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class Hangman {

    public InputStream in = System.in;
    public String[] library = {"cat","dog","mouse","mop","stand","drive","smile","snark","shark","bat","ramp","ton","car","zoo"};

    public void playHangman(){
        Random rand = new Random();
        int selectWord = rand.nextInt(library.length-1)+1;
        String word = library[selectWord];
        char[] userBank = new char[word.length()];
        Arrays.fill(userBank, '_');
        int userGuesses = 0;
        System.out.println("  HANGMAN\n");
        System.out.println(buildHangman(userGuesses));
        char[] temp = userBank;
        System.out.println(temp);

    }

    public String buildHangman(int guesses){
        String top = "  +---+";
        String pole = "      |";
        String ground = "     ===";
        String head = "  O   |";
        String upperBody = "  |   |";
        String lowerBody = "  |   |";
        String leftLeg = " /";
        String leftRightLeg = " / \\";
        String leftArm = " \\";
        String leftRightArm = " \\ /";
        switch (guesses){
            case 1:
                return top + "\n\n" + head + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
            case 2:
                return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + pole + "\n\n" +ground;
            case 3:
                return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n\n" +ground;
            case 4:
                return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftLeg+"\n" +ground;
            case 5:
                return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
            case 6:
                return top + "\n\n" + head + "\n"+ leftArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
            case 7:
                return top + "\n\n" + head + "\n"+ leftRightArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
            default:
                return top + "\n\n" + pole + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
        }

    }
    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.playHangman();
    }
}
