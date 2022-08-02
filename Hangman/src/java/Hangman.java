public class Hangman {

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
        System.out.println(h.buildHangman(1));
    }
}
