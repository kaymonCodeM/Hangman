import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hangman {

    private final Random rand = new Random();
    private final ArrayList<Character> missedLetters = new ArrayList<>();
    private Scanner userInput;
    public String[] library = {"cat","dog","mouse","mop","stand","drive","smile","snark","shark","bat","ramp","ton","car","zoo"};
    private char[] userBank;
    private boolean playGame = true;
    private int badGuesses = 0;
    private String word;

    private String userName;

    public Hangman(){
        setWord(library[(rand.nextInt(this.library.length-1) + 1)]);
        String s = Arrays.stream(word.split("")).map(c-> "_").reduce("",(a,b) ->a+b);
        setUserBank(s.toCharArray());
    }

    public void playHangman(){
        setUserInput(new Scanner(System.in));
        System.out.println("Hello! What is your name?\n");
        System.out.println(greetUser() +"\n");
        System.out.println("HANGMAN");

        while (this.playGame){
            System.out.println(buildHangman());
            System.out.print("\nMissed letters: ");
            System.out.print(missedLetters.stream().map(Object::toString).reduce("",(a, b)->a+b));
            System.out.println("\n");
            System.out.println(this.userBank);
            System.out.println("\n");

            if(this.badGuesses==7){
                System.out.println("You Lose! The word is: \"" + this.word+ "\"");
                playAgain();
            }else {
                makeGuess();
            }
            if (this.word.compareTo(String.valueOf(this.userBank)) == 0) {
                System.out.println("\nGreat Job " + this.userName + "! The secret word is \"" + this.word + "\"! You have won!"+ "\n");

                Path path = Path.of("Hangman/resources/score.txt");
                try{
                    Files.writeString(path,"Name: " + this.userName + ", score: " + this.badGuesses +"\n",
                            StandardOpenOption.APPEND);
                }catch (Exception IO){
                    System.out.println("Error: User score not recorded.");
                }

                try {
                    Stream<String> strings = Files.lines(path);
                    Optional<String> highScore = strings.filter(s->Character.getNumericValue(s.charAt(s.length()-1))<this.badGuesses)
                            .min(Comparator.comparingInt(a -> Character.getNumericValue(a.charAt(a.length() - 1))));
                    if (highScore.isPresent()){
                        System.out.println("You do not have the best score: You will have to beat this person:");
                        System.out.println(highScore.get()+ "\n");
                    }else {
                        System.out.println("Great Job! You have the Best Score with " + this.badGuesses +  " guesses!" +"\n");
                    }
                    strings.close();
                }catch (Exception IO){
                    System.out.println("Error: Did not recover scores"+ "\n");
                }


                playAgain();
            }

        }

    }

    public String greetUser(){

        try{
            setUserName(this.userInput.next());
            return "\nWell, "+ this.userName +", You are now playing HANGMAN. guess the word before you get hanged.";
        }catch (Exception e){
            return "\nUser name is invalid";
        }
    }

    public void playAgain(){
        System.out.println("Do you want to play again? (yes or no)\n");
        try {
            String again = this.userInput.next();
            if (again.compareTo("yes")==0) {
                setPlayGame(true);
                setBadGuesses(0);
                this.missedLetters.clear();
                setWord(library[(rand.nextInt(this.library.length-1) + 1)]);
                String s = Arrays.stream(word.split("")).map(c-> "_").reduce("",(a,b) ->a+b);
                setUserBank(s.toCharArray());
            } else if (again.compareTo("no")==0) {
                setPlayGame(false);
                closeScanner();
            }else {
                System.out.println("\nNope... yes or no");
                playAgain();
            }

        } catch (Exception e) {
            System.out.println("\nVery bad input. Good Bye and Good Day");
        }
    }

    public void makeGuess(){
        System.out.println("Guess a letter.\n");
        char userGuessLetter;
        try {
            userGuessLetter = userInput.next().charAt(0);

        }catch (Exception e){
            System.out.println("Bad user Input");
            makeGuess();
            return;
        }

        if(missedLetters.contains(userGuessLetter)) {
            System.out.println("\nYou have already guessed that letter.Choose again.\n");
            makeGuess();
        }else if(String.valueOf(this.userBank).contains(String.valueOf(userGuessLetter))){
            System.out.println("\nYou have already guessed that letter.Choose again.\n");
            makeGuess();
        } else if(this.word.contains(String.valueOf(userGuessLetter))){
            IntStream.range(0, this.word.length()).filter(i -> this.word.toCharArray()[i] == userGuessLetter).forEach(n -> this.userBank[n] = userGuessLetter);
        }else {
            this.missedLetters.add(userGuessLetter);
            this.badGuesses++;
        }
    }



    public String buildHangman(){
        String result = "";
        try{
            String s = Files.readString(Path.of("Hangman/resources/hangmanArt.txt"));
            String temp =  Arrays.stream(s.split("guesses")).filter(e-> e.contains(Integer.toString(this.badGuesses))).reduce("",(a,b) -> a+b);
            result += temp.substring(1,temp.length()-3);
        }catch (Exception IO){
            result += "File was not found";
        }
        return result;
    }

    public void closeScanner(){
        this.userInput.close();
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

    public void setUserInput(Scanner userInput) {
        this.userInput = userInput;
    }

    public void setUserBank(char[] userBank) {
        this.userBank = userBank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getPlayGame(){
        return this.playGame;
    }

    public char[] getUserBank() {
        return userBank;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Character> getMissedLetters() {
        return missedLetters;
    }

    public Scanner getUserInput() {
        return userInput;
    }

    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.playHangman();
    }
}
