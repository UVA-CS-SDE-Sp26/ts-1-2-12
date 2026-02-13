import java.util.List;

public class Cipher{

    private int key_number;
    private String data;
    private String key_original;
    private String key_cipher;

    public Cipher(String data, int key_number) { // assumes data has already been read
        this.data = data;
        this.key_number = key_number;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setKeyNumber(int key_number) {
        this.key_number = key_number;
    }

    private String getKeyLine(int line_index) { // returns data from line index in key file
        FileHandler key_handler = new FileHandler();
        List<String> key_list = key_handler.readCipherFromID(key_number);
        return key_list.get(line_index);
    }

    private Boolean validateKey() { // checks if line 1 and line 2 of key are the same length
        String key_original = getKeyLine(0);
        String key_cipher = getKeyLine(1);
        if(key_original.length() == key_cipher.length()) {
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
                result += key_cipher.charAt(key_original.indexOf(c));
                // concatenates the corresponding character of index of c in line 1 of key in line 2 to result
            }
            return result;
        }
        return "Error: invalid key."
    }


}