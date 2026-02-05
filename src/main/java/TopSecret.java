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
    }
}