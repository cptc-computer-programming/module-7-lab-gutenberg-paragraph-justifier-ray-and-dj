import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;

public class GutenbergParagraphJustifier {
public static final int TAB_SIZE = 4;

    public static void main(String[] args) throws FileNotFoundException {
        // create scanners

        PrintStream output = new PrintStream(new File("data\\output\\example.txt"))
        // create output stream


        // process
        justifyParagraphs(input, output);

        // close scanners
        input.close();
        output.close();
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
    public static Scanner getInputFileScanner(Scanner console) {
        Scanner input = null;

        do {
            String path = console.nextLine();
            try {
                input = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                input = null;
                System.err.printf("Invalid file path: %s%n", path);
            }
        } while (input == null);

        return input;
    }

    // Returns a string of n spaces (no tabs).
    public static String spaces(int n) {
        return " ".repeat(n);
    }
}
