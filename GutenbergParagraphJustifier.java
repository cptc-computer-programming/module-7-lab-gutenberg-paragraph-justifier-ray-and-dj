import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;

public class GutenbergParagraphJustifier {
public static final int TAB_SIZE = 4;

    public static void main(String[] args) throws FileNotFoundException {
        // create scanners
        Scanner input = new Scanner(new File("data\\input\\example.txt"));
        // create output stream


        // process

        // close scanners
        input.close();
    }

    // Reads the input text and writes a "justified" version:
    // - trims every line
    // - collapses multiple blank lines into ONE blank line
    // - indents every nonblank line by TAB_SIZE spaces
    public static void justifyParagraphs(Scanner input, PrintStream out) {
        input.nextLine();
        while (input.hasNextLine()) {
            
        }
    }

    // Prompts until a readable file name is provided, then returns a Scanner on it.
    public static Scanner getInputFileScanner(Scanner console) throws FileNotFoundException {
        return new Scanner(System.in);
    }

    // Returns a string of n spaces (no tabs).
    public static String spaces(int n) {
        return "";
    }
}
