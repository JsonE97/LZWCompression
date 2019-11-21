import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LZWAlgorithm {

    private int codeCount;
    private String inFile;
    private Trie trie;
    private ArrayList<Character> chars;
    private int fileLength;

    public LZWAlgorithm(String inputFileName) throws IOException {
        inFile = inputFileName;
        codeCount = 0;
        fileLength = 0;
        trie = new Trie();
        chars = new ArrayList<Character>();
        executeAlgorithm();
    }

    public void executeAlgorithm() throws IOException {
        initialiseTrie();
        readFromFile();
    }

    public void initialiseTrie() {
        //generate initial trie of ASCII chars
        for (int i = 0; i <= 127; i++) {
            trie.insert(String.valueOf((char) (i)));

        }
    }

    public void readFromFile() throws IOException {
        FileReader reader = new FileReader(inFile);
        Scanner in = new Scanner(reader);

        String line = in.nextLine();
        while (line != null) {
            //adds all the characters of the current line to an array list
            for (int i = 0; i < line.length(); i++) {
                fileLength++;
                chars.add(line.charAt(i));
            }

            //adds each new line as the current line is traversed
            fileLength++;
            chars.add('\n');

            //for each line, add new sequence of characters to trie if it does not exist
            if (in.hasNextLine()) {
                line = in.nextLine();
            } else {
                line = null;
            }


        }
        reader.close();
    }

    public Trie getTrie() {
        return trie;
    }

    public ArrayList<Character> getChars() {
        return chars;
    }

    public void setCodeCount(int newCodeCount) {
        codeCount = newCodeCount;
    }

    public int getFileLength() {
        return fileLength;
    }

    public int getCodeCount() {
        return codeCount;
    }

}
