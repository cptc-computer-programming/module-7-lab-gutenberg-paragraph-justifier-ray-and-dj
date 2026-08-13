import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;

public class GutenbergParagraphJustifier {
    public static final int TAB_SIZE = 4;
    private static String path = null;

    public static void main(String[] args) throws FileNotFoundException {
        // create scanners
        Scanner console = new Scanner(System.in);
        Scanner input = getInputFileScanner(console); // Input FILE
        console.close();

        // create output stream
        PrintStream output = new PrintStream(new File(String.format("%s_indented", path)));

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
        boolean shouldRead = false;
        boolean inParagraph = false;
        while (input.hasNextLine()) {
            String line = input.nextLine().trim(); // trim() whitespace
            if (line.isEmpty()) {
                if (inParagraph)
                    out.println("");
                
                inParagraph = false;
                continue;
            }

            if (line.startsWith("*** START")) { // Header
                shouldRead = true;
                continue;
            }

            if (line.startsWith("*** END")) { // Footer
                shouldRead = false;
                continue;
            }

            // Process lines of book in here
            if (shouldRead) {
                String newLine;
                if (!inParagraph) {
                    newLine = spaces(TAB_SIZE) + line;
                } else {
                    newLine = line;
                }
                out.println(newLine);
                inParagraph = true;
            }
        }
    }

    // Prompts until a readable file name is provided, then returns a Scanner on it.
    public static Scanner getInputFileScanner(Scanner console) throws FileNotFoundException {
        File input = null;

        do {
            String p = console.nextLine();
            input = new File(p);

            if (!input.canRead()) { // Can't read
                System.err.printf("Invalid file path: %s%n", p);
                input = null;
            }
            path = p;
        } while (input == null);

        return new Scanner(input);
    }

    // Returns a string of n spaces (no tabs).
    public static String spaces(int n) {
        return " ".repeat(n);
    }
}
