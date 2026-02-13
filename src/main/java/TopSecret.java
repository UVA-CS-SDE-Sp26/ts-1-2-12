/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args){
        System.out.println("");
        System.out.println("Welcome to Top Secret Cipher Software - Command Line Edition");
        System.out.println("");

        ProgramControl control = new ProgramControl();
        if(args.length == 0){
            control.printFileList();
            return;
        } else if (args.length == 1){
            String arg = args[0];
            int fileNum;

            try {
                fileNum = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("Error: file number must be numeric.");
                return;
            }

            String content = control.getFileContentsByNumber(fileNum);
            System.out.println(content);
        } else if (args.length == 2){
            String arg = args[0];
            String keyFile = args[1];
            int fileNum;

            try {
                fileNum = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("Error: file number must be numeric.");
                return;
            }

            String content = control.getFileContentsByNumber(fileNum);

            //placeholder for D
            System.out.println("Using key: " + keyFile + "\n");
            System.out.println(content);
            return;
        } else {
            System.out.println("Too many arguments.");
        }
    }
}