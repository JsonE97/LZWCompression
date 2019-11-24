/**
 * Required imports for class
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Public class to encapsulate the LZW compression algorithm
 */

public class LZWAlgorithm {

    /**
     * Private class variables
     */

    private int codeCount;
    private String inFile;
    private Trie trie;
    private ArrayList<Character> chars;
    private int fileLength;

    /**
     * Public constructor for LZWAlgorithm
     *
     * @param inputFileName - String - file name to be read from
     * @throws IOException
     */

    public LZWAlgorithm(String inputFileName) throws IOException {
        inFile = inputFileName;
        codeCount = 0;
        fileLength = 0;
        trie = new Trie();
        chars = new ArrayList<>();
        executeAlgorithm();
    }

    /**
     * executeAlgorithm - public method to execute the main LZW steps
     *
     * @throws IOException
     */

    public void executeAlgorithm() throws IOException {
        initialiseTrie();
        readFromFile();
        addWordsToTrie();
    }

    /**
     * initialiseTrie - Algorithm uses a trie for storage prefixes to words
     */

    private void initialiseTrie() {
        //generate initial trie of ASCII chars
        for (int i = 0; i <= 127; i++) {
            trie.insert(String.valueOf((char) (i)));
        }
    }

    /**
     * readFromFile - method to read from the given file in class construction
     *
     * @throws IOException
     */

    private void readFromFile() throws IOException {
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
        in.close();
        reader.close();
    }

    /**
     * addWordsToTrie - adds words loaded into ArrayList (chars) to the trie
     */

    private void addWordsToTrie() {
        int i = 0;
        //start the count from 128 as the ASCII chars are already in the trie
        long count = 128;

        //codeWordLength is now 8 as 2^7 chars are already in the trie
        int codeWordLength = 8;

        //whilst the position is within index of the chars array list
        while (i < chars.size()) {
            String s = String.valueOf(chars.get(i));

            //while loop to get the largest string s in the trie
            while ((i + s.length() < chars.size()) && trie.search(s)) {

                //appends the new char to s if it is already in the trie
                s = s + String.valueOf(chars.get(i + s.length()));
            }

            i += s.length();
            //decrements i if the length is greater than 1 to go back a char
            if (s.length() > 1) {
                i = i - 1;
            }
            if (i < chars.size() && !trie.search(s)) {
                trie.insert(s);
                count++;
                codeCount += codeWordLength;
            }
            //checks whether the codeWordLength needs increased to double dictionary space
            if (count > Math.pow(2, codeWordLength)) {
                codeWordLength += 1;
            }
        }
        //adds the final codeWord of the file
        codeCount += codeWordLength;
    }

    /**
     * Required getters
     */


    /**
     * getFileLength
     * @return - int - fileLength
     */

    public int getFileLength() {
        return fileLength;
    }

    /**
     * getCodeCount
     * @return - int - codeCount
     */

    public int getCodeCount() {
        return codeCount;
    }

}
