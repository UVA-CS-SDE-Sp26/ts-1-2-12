import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TopSecretTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;


    //Allows printed content to be inspected and captured
    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /*Restores standard output after each test to
      ensure that printing to the console behaves normally */
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    //Test for if there are no arguments given.
    @Test
    void testNoArgumentsPrintsFileList() {
        TopSecret.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("Available files:"));
    }

    //Test for if one argument is given (user requests a valid file by number)
    @Test
    void testSingleValidFileArgument() {
        TopSecret.main(new String[]{"1"});
        String output = outContent.toString();
        // Should contain either file content or error about missing file
        assertTrue(output.contains("Error: could not read file.") || output.contains("Using key:") == false);
    }

    //Test for if one argument is given but it is an invalid file argument.
    @Test
    void testSingleInvalidFileArgument() {
        TopSecret.main(new String[]{"abc"}); // non-numeric argument
        String output = outContent.toString();
        assertTrue(output.contains("Error: file number must be numeric."));
    }

    @Test
    //Test for two valid arguments
    void testTwoArguments() {
        TopSecret.main(new String[]{"1", "key.txt"});
        String output = outContent.toString();
        assertTrue(output.contains("Using key: key.txt") || output.contains("Error: could not read file."));
    }

    @Test
    //Test for too many argyments
    void testTooManyArguments() {
        String[] arguments = {"1", "key.txt", "extra"};
        TopSecret.main(arguments);
        String output = outContent.toString();
        assertTrue(output.contains("Too many arguments."), "Argument count is " + arguments.length);
    }

    @Test

    //Test for if two arguments are given, and the first is invalid
    void testTwoArgumentsFirstInvalid() {
        TopSecret.main(new String[]{"abc", "key.txt"});
        String output = outContent.toString();
        assertTrue(output.contains("Error: file number must be numeric."));
    }

    @Test
    //Test for if two arguments are given, and the second is invalid
    void testTwoArgumentsKeyFileMissing() {
        TopSecret.main(new String[]{"1", "nonexistentkey.txt"});
        String output = outContent.toString();
        assertTrue(output.contains("Using key: nonexistentkey.txt"));
    }

}
