import java.io.FileNotFoundException;
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
        String outPath = String.format("data/output/%s_indented.txt", path);
        PrintStream output = new PrintStream(new File(outPath));

        // process
        justifyParagraphs(input, output);
        System.out.printf("Processed: %s%n", outPath);

        // close scanners
        input.close();
        output.close();
    }

    // Reads the input text and writes a "justified" version:
    // - trims every line
    // - collapses multiple blank lines into ONE blank line
    // - indents every nonblank line by TAB_SIZE spaces
    public static void justifyParagraphs(Scanner input, PrintStream out) {
        boolean shouldRead = false; // Whether we are inside *** START and *** END (book content)
        boolean inParagraph = false; // Whether we are in a paragraph, or inbetween blank lines and looking for one

        while (input.hasNextLine()) {
            String line = input.nextLine().trim(); // trim() whitespace
            if (line.isEmpty()) {
                if (inParagraph) // Add one blank line at the end of a paragraph
                    out.println("");

                inParagraph = false; // When not in a paragraph this will consume extra blank lines
                continue;
            }

            if (line.startsWith("*** START")) { // Header
                shouldRead = true; // Main book content entered
                continue;
            }

            if (line.startsWith("*** END")) { // Footer
                shouldRead = false; // Main book content exited
                continue;
            }

            // Process lines of book in here
            if (shouldRead) {
                String newLine;
                if (!inParagraph) { // Not already in a paragraph, so this is the first line
                    newLine = spaces(TAB_SIZE) + line; // Indent first line
                } else {
                    newLine = line; // Just trim subsequent lines
                }

                out.println(newLine);
                inParagraph = true; // If we are writing text we are in a paragraph
            }
        }
    }

    // Prompts until a readable file name is provided, then returns a Scanner on it.
    public static Scanner getInputFileScanner(Scanner console) throws FileNotFoundException {
        File input = null;

        do {
            System.out.print("File path (relative): ");
            String p = console.nextLine();
            input = new File(p);

            if (!input.canRead()) { // Can't read
                System.err.printf("Invalid file path: %s%n", p);
                input = null;
                continue;
            }
            path = input.getName();
            path = path.substring(0, path.lastIndexOf("."));
        } while (input == null);

        return new Scanner(input);
    }

    // Returns a string of n spaces (no tabs).
    public static String spaces(int n) {
        return " ".repeat(n);
    }
}
