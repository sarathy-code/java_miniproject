import java.io.*;
import java.util.Scanner;
import java.util.zip.*;

public class FileCompression {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the source file : ");
        String sourceFile = scan.nextLine();
        System.out.print("Enter the file you want to compress file with .gz file format : ");
        String compressedFile = scan.nextLine();
        System.out.print("Enter the file you want to decompress : ");
        String decompressedFile = scan.nextLine();

        compressedFile(sourceFile, compressedFile);
        decompressedFile(compressedFile, decompressedFile);
    }


    public static void compressedFile(String inputFile, String outputFile) {
        try (
            FileReader fr = new FileReader(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
            OutputStreamWriter osw = new OutputStreamWriter(gzipOS)
        ) {
            int data;
            while ((data = fr.read()) != -1) {
                osw.write(data); 
            }
            System.out.println("File compressed successfully to: " + outputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred - " + e.getMessage());
        }
    }
    public static void decompressedFile(String inputFile, String outputFile) {
        try (
            FileInputStream fis = new FileInputStream(inputFile);
            GZIPInputStream gzipIS = new GZIPInputStream(fis);
            InputStreamReader isr = new InputStreamReader(gzipIS);
            FileWriter fw = new FileWriter(outputFile)
        ) {
            int data;
            while ((data = isr.read()) != -1) {
                fw.write(data);
            }
            System.out.println("File decompressed successfully to: " + outputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred - " + e.getMessage());
        }
    }

    
}