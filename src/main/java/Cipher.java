import java.util.List;

public class Cipher {

    private String cipherPath;
    private String data;
    private String key_original;
    private String key_cipher;

    public Cipher(String data, String cipherPath) { // assumes data has already been read
        this.data = data;
        this.cipherPath = cipherPath;
    }

    private String getKeyLine(int line_index) { // returns data from line index in key file
        FileHandler key_handler = new FileHandler();
        List<String> key_list = key_handler.readCipherFolder(cipherPath);
        if(key_list != null)
            return key_list.get(line_index);
        return null;
    }

    private boolean noDuplicateCharacterCheck(String str){ // checks for duplicate character in a string
        char[] charArray = str.toCharArray();
        int length = charArray.length;

        // Each loop saves the next character and iterates through entire string, checking for a duplicate
        for(int i = 0; i < length; i++) {
            for(int j = i + 1; j < length; j++) {
                if(charArray[i] == charArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean validateKey() { // checks if line 1 and line 2 of key are the same length
        String key_original = getKeyLine(0);
        String key_cipher = getKeyLine(1);
        if(key_original != null && key_cipher != null && key_original.length() == key_cipher.length()
                && noDuplicateCharacterCheck(key_original) && noDuplicateCharacterCheck(key_cipher)) {
            this.key_original = key_original;
            this.key_cipher = key_cipher;
            return true;
        }
        return false;
    }

    public String decipherData() { // deciphers the data using the cipher in the key
        if(validateKey()) {
            String result = "";
            for(int i = 0; i < data.length(); i++) { // iterates by character through the data
                char c = data.charAt(i);
                int index_in_key_cipher = key_cipher.indexOf(c);
                if (index_in_key_cipher == -1) {
                    System.out.print(index_in_key_cipher);
                    result += c;
                    // if c does not exist in key, then concatenate c to result as is.
                }
                else {
                    result += key_original.charAt(index_in_key_cipher);
                    // concatenates the corresponding character of index of c in line 2 of key in line 1 to result
                }
            }
            return result;
        }
        return "Error: invalid key.";
    }
}