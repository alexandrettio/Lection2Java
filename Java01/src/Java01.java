/**
 * Created by Alexandret on 17/11/14.
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Java01 {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("input.txt")));
        System.out.print(content);
    }
}
