import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Гончаревич Андрей 8 группа
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        IO files = new IO("src" + IO.sep + "root.txt", "src" + IO.sep + "poem.txt");
        TreeMap<String, String> map = files.buildMap("src" + IO.sep + "files.txt");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " -> " + value + "\n");
            if (Objects.equals(value, "directory"))
                IO.createDir(key);
            else
                IO.createFile(key, value);
        }
    }
}
