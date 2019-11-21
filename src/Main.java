import java.io.*;
import java.util.*;


/**
 * program to find compression ratio using LZW compression
 */
public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String inputFileName = args[0];
        LZWAlgorithm lzw = new LZWAlgorithm(inputFileName);

        ArrayList<Character> chars = lzw.getChars();
        int i = 0;
        String s = "";

        //start the count from 128 as the ASCII chars are already in the trie
        long count = 128;

        //codeWordLength is now 8 as 2^7 chars are already in the trie
        int codeWordLength = 8;

        //whilst the position is within index of the chars array list
        while (i < chars.size()) {

            //get the current char from the given index i
            s = String.valueOf(chars.get(i));

            //while loop to get the largest string s in the trie
            while ((i + s.length() < chars.size()) && lzw.getTrie().search(s)) {

                //appends the new char to s if it is already in the trie
                s = s + String.valueOf(chars.get(i + s.length()));
            }

            //increments i by the length of s
            i += s.length();

            //decrements i if the length is greater than 1 to go back a char
            if (s.length() > 1) {
                i = i - 1;
            }

            if (i < chars.size() && !lzw.getTrie().search(s)) {

                //inserts new string s to trie, increments count, and adds the new codeWordLength to the codeCount
                lzw.getTrie().insert(s);
                count++;
                lzw.setCodeCount(lzw.getCodeCount() + codeWordLength);
            }


            //checks whether the codeWordLength needs increased to double dictionary space
            if (count > Math.pow(2, codeWordLength)) {
                codeWordLength += 1;
            }
        }

        //adds the final codeWord of the file
        lzw.setCodeCount(lzw.getCodeCount() + codeWordLength);


        //closes resources
        String outputFileName = args[1];
        FileWriter writer = new FileWriter(outputFileName);
        writer.write("Input file " + inputFileName + "  LZW algorithm\n\n");

        // write out the results here
        writer.write("Original file length in bits : " + lzw.getFileLength() * 8);
        writer.write("\nCompressed file length in bits : " + lzw.getCodeCount());
        double ratio = (double) lzw.getCodeCount() / (lzw.getFileLength() * 8);
        writer.write("\nCompressed ratio = " + Math.round(ratio * 10000.0) / 10000.0 + "\n");

        long end = System.currentTimeMillis();
        writer.write("\nElapsed time: " + (end - start) + " milliseconds\n");
        writer.close();
    }

}
