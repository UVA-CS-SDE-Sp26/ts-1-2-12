import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;

public class ProgramControl {

    FileHandler fileHandler;
    ArrayList<String> availableFiles;

    public ProgramControl() {
        fileHandler = new FileHandler();
        availableFiles = new ArrayList<String>();

        // Build the file list dynamically from the data folder
        // instead of hardcoding file names -- this way new files are picked up automatically
        Path dataPath = Paths.get(System.getProperty("user.dir"), fileHandler.getDocFolder());

        try (Stream<Path> stream = Files.list(dataPath)) {
            stream.map(path -> path.getFileName().toString())
                    .sorted()
                    .forEach(availableFiles::add);
        } catch (IOException e) {
            // If the folder can't be read, availableFiles stays empty
            System.out.println("Warning: could not read data folder.");
        }
    }

    public ArrayList<String> getAvailableFiles() {
        return availableFiles;
    }

    // Prints the numbered list of available files
    // Format matches the spec: "01 filename.txt"
    public void printFileList() {
        System.out.println("Available files:");
        for (int i = 0; i < availableFiles.size(); i++) {
            // zero-pad the number to match the format shown in the assignment
            String number = String.format("%02d", i + 1);
            System.out.println(number + " " + availableFiles.get(i));
        }
    }

    // Returns the full text content of the file at the given 1-based index
    public String getFileContentsByNumber(int fileNumber) {
        if (fileNumber < 1 || fileNumber > availableFiles.size()) {
            return "Error: invalid file number.";
        }

        String fileName = availableFiles.get(fileNumber - 1);
        ArrayList<String> lines = fileHandler.readFileFromDataFolder(fileName);

        if (lines == null) {
            return "Error: could not read file.";
        }

        // Build the result string line by line
        String result = "";
        for (int i = 0; i < lines.size(); i++) {
            result = result + lines.get(i) + "\n";
        }
        return result;
    }

    // Handles a string argument from the user -- either shows the file list or file contents
    public void run(String argument) {
        if (argument == null || argument.equals("")) {
            printFileList();
        } else {
            try {
                int fileNumber = Integer.parseInt(argument);
                String contents = getFileContentsByNumber(fileNumber);
                System.out.println(contents);
            } catch (NumberFormatException e) {
                System.out.println("Error: please enter a valid file number.");
            }
        }
    }
}