import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private final Random rand = new Random();
    private InputStream in = System.in;
    public String[] library = {"cat","dog","mouse","mop","stand","drive","smile","snark","shark","bat","ramp","ton","car","zoo"};

    private char[] userBank;
    private ArrayList<Character> missedLetters = new ArrayList<>();
    private boolean playGame = true;
    private int badGuesses = 0;

    private String word;

    public Hangman(){
        setWord(library[(rand.nextInt(this.library.length-1) + 1)]);
        this.userBank = new char[word.length()];
        Arrays.fill(this.userBank, '_');
    }

    public void playHangman(){
        System.out.println("  HANGMAN");

        while (this.playGame){
            System.out.println(buildHangman(this.badGuesses));
            System.out.print("\nMissed letters: ");
            for (char c: this.missedLetters){
                System.out.print(c +" ");
            }
            System.out.println("\n");
            System.out.println(this.userBank);
            System.out.println();

            if(this.badGuesses==7){
                System.out.println("You Lose! The word is: " + this.word);
                break;
            }
            makeGuess();
            if (this.word.compareTo(new String(this.userBank))==0){
                System.out.println("\nYes! The secret word is \"" + this.word + "\"! You have won!" );
                playAgain();
            }

        }

    }

    public void playAgain(){
        System.out.println("Do you want to play again? (yes or no)\n");
        try {
            Scanner userInput = new Scanner(in);
            String again = userInput.next();
            if (again.compareTo("yes")==0) {
                setPlayGame(true);
                setBadGuesses(0);
                this.missedLetters.clear();
                setWord(library[(rand.nextInt(this.library.length-1) + 1)]);
                this.userBank = new char[this.word.length()];
                Arrays.fill(this.userBank, '_');
            } else if (again.compareTo("no")==0) {
                setPlayGame(false);
            }else {
                System.out.println("\nNope... yes or no");
                playAgain();
            }

        } catch (Exception e) {
            System.out.println("\nVery bad input. Good Bye and Good Day");
        }
    }

    public void makeGuess(){
        System.out.println("Guess a Letter.\n");
        char userGuessLetter = '_';
        boolean guessExist = false;
        try {
            Scanner userInput = new Scanner(in);
            userGuessLetter = userInput.next().charAt(0);

        }catch (Exception e){
            System.out.println("Bad user Input");
            makeGuess();
        }

        if(missedLetters.contains(userGuessLetter)) {
            System.out.println("\nYou have already guessed that letter.Choose again.\n");
            guessExist = true;
            makeGuess();
        }else {
            for (int i = 0; i < this.word.length(); i++) {
                char letter = this.word.charAt(i);
                if (userBank[i] == userGuessLetter) {
                    System.out.println("\nYou have already guessed that letter.Choose again.\n");
                    guessExist= true;
                    makeGuess();
                } else if (userGuessLetter == letter) {
                    this.userBank[i] = letter;
                    guessExist = true;
                }
            }
        }
        if (!guessExist){
            this.missedLetters.add(userGuessLetter);
            this.badGuesses++;
        }
    }

    public String buildHangman(int guesses){
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

    public void setPlayGame(boolean playGame) {
        this.playGame = playGame;
    }

    public void setBadGuesses(int badGuesses) {
        this.badGuesses = badGuesses;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.playHangman();
    }
}
