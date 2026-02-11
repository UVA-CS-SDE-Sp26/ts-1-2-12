/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args){
        System.out.println("");
        System.out.println("Welcome to Top Secret Cipher Software - Command Line Edition");
        System.out.println("");

        FileHandler handler = new FileHandler(args);
        handler.readDefaultFiles();
        handler.readFileFromDataFolder("test2.txt");

        //System.out.println("Reading char index from file:");
        char returned = handler.readFileIndexFromData("test2.txt", 7);
        System.out.println(returned);
    }
}