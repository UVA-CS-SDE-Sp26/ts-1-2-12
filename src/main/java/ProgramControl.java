import java.util.ArrayList;

public class ProgramControl {

    FileHandler fileHandler;
    ArrayList<String> availableFiles;

    public ProgramControl() {
        fileHandler = new FileHandler();
        availableFiles = new ArrayList<String>();

        // manually add the known files in the data folder
        availableFiles.add("test1.txt");
        availableFiles.add("test2.txt");
    }

    public ArrayList<String> getAvailableFiles() {
        return availableFiles;
    }

    public void printFileList() {
        System.out.println("Available files:");
        for (int i = 0; i < availableFiles.size(); i++) {
            System.out.println((i + 1) + " " + availableFiles.get(i));
        }
    }

    public String getFileContentsByNumber(int fileNumber) {
        if (fileNumber < 1 || fileNumber > availableFiles.size()) {
            return "Error: invalid file number.";
        }

        String fileName = availableFiles.get(fileNumber - 1);
        ArrayList<String> lines = fileHandler.readFileFromDataFolder(fileName);

        if (lines == null) {
            return "Error: could not read file.";
        }

        String result = "";
        for (int i = 0; i < lines.size(); i++) {
            result = result + lines.get(i) + "\n";
        }
        return result;
    }

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