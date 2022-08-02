public class Hangman {

    public String buildHangman(int guesses){
        String top = "  +---+";
        String pole = "      |";
        String ground = "     ===";
        String head = "  O   |";
        String body = "  |   |";
        switch (guesses){
            case 0:
                return top + "\n\n" + pole + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
            case 1:
                return top + "\n\n" + head + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
            case 2:
                return top + "\n\n" + head + "\n\n" + body +"\n\n" + pole + "\n\n" +ground;

        }
        return top;
    }
    public static void main(String[] args) {
        Hangman h = new Hangman();
        System.out.println(h.buildHangman(2));
    }
}
