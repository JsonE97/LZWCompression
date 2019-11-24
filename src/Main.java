import java.io.*;

/**
 * program to find compression ratio using LZW compression
 */
public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String inputFileName = args[0];
        LZWAlgorithm lzw = new LZWAlgorithm(inputFileName);

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
