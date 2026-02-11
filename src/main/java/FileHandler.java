import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Stream;

public class FileHandler {
    String readFileName;
    ArrayList<String> storedFiles;
    String docFolder = "data"; //directory for base text file

    public String getDocFolder() { //getter for docFolder for use by test files
        return docFolder;
    }
    public FileHandler(String[] args){ //if commandline arguments are passed along
        storedFiles = new ArrayList<String>();
        for(int i = 0; i < args.length; i++){
            Path path = Paths.get(args[i]);
            if(Files.exists(path)){
                storedFiles.add(args[i]);
                System.out.println("File path added to list successfully.");
            }
        }
    }
    public FileHandler(){ //blank constructor without a commandline argument
        readDefaultFiles();
    }
    public String readDefaultFiles(){
        System.out.println("Default Files:");
        System.out.println("~~~~~~~~~~~~~~");

        Path dataPath = Paths.get(System.getProperty("user.dir"), docFolder); //appends the root directory with the docFolder directory
        System.out.println("Path: " + dataPath.toString());

        System.out.println("Files:");

        try(Stream<Path> stream = Files.list(dataPath)){
            stream.map(Path::getFileName).forEach(System.out::println);
        } catch(IOException e){
            e.printStackTrace(); //in the event of an IOException
        }

        return dataPath.toString();
    }
    public ArrayList<String> readFile(String path){
        Path inputPath = Paths.get(path);
        if(Files.exists(inputPath))
            return readFile(inputPath); //pass along the input path
        else
            return null;
    }
    public ArrayList<String> readFile(Path path){//overload for readFile that accepts a path object instead of a string for the path name
        ArrayList<String> readLines = new ArrayList<>();
        if(Files.exists(path)){
            try{ //inside a try block, as IOExceptions will terminate program control if not caught and handled properly
                List<String> lines = Files.readAllLines(path);
                for(int i = 0; i < lines.size(); i++){
                    System.out.println(lines.get(i));
                }

                readLines = new ArrayList<>(lines);
            }
            catch(IOException e){
                //what to do if the IOException is caught
            }
        }
        else{
            System.out.println("File from path " + path + " does not exist");
        }

        if(!readLines.isEmpty()){
            return readLines;
        }
        else {
            return null; //return nothing
        }
    }
    public ArrayList<String> readFileFromDataFolder(String fileName){
        //helper function for readFile that instead allows just the document file name in the data folder
        Path dataPath = Paths.get(System.getProperty("user.dir"), docFolder, fileName); //append the filename
        return readFile(dataPath);
    }
    public char readFileIndex(String path, int charIndex){
        //this function reads an index of a character at a specified point in the file.
        //the function returns false if the read failed
        List<String> readLines = readFile(path);
        //sum the lengths of all strings
        if(readLines == null) return ' ';
        int totalLength = readLines.stream().mapToInt(String::length).sum(); //fun use of streams here
        if(charIndex > totalLength) return ' '; //returning a space char is acceptable as there isn't a cipher assignment

        int remainingIndex = charIndex;

        for(String line: readLines){
            if(remainingIndex < line.length()){
                return line.charAt(remainingIndex);
            }
            else{
                remainingIndex -= line.length();
            }
        }

        return ' '; //return space if the character is not found
    }
    public char readFileIndexFromData(String fileName, int charIndex){
        //find the appropriate path and return readFileIndex for that path
        Path desiredPath = Paths.get(System.getProperty("user.dir"), docFolder, fileName);
        return readFileIndex(desiredPath.toString(), charIndex);
    }
}