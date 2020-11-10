package by.bivis.vkParser.JSONs.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {
    List<String> list = new ArrayList<>();

    public static String read(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            // char-reading
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
            return sb.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void write(String path, String[] strings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String string : strings) {
                bw.write(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}