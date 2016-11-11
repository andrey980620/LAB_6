import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Гончаревич Андрей 8 группа
 */
public class IO {
    public String rootDir;
    public static final String sep = File.separator;
    private ArrayList<String> verses = new ArrayList<>();

    IO(String rootFile, String poemFile) {
        try {
            BufferedReader root = new BufferedReader(new FileReader((rootFile)));
            BufferedReader poem = new BufferedReader(new FileReader((poemFile)));

            rootDir = root.readLine() + sep + root.readLine();
            //System.out.println(rootDir);

            String tmp;
            while ((tmp = poem.readLine()) != null)
                verses.add(tmp);
            //System.out.println(verses);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createFile(String path, String content) throws IOException {
        File newFile = new File(path);
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();
        FileWriter out = new FileWriter(newFile);
        out.write(content);
        out.close();
    }

    public static void createDir(String path) throws IOException {
        File newDir = new File(path);
        newDir.getParentFile().mkdirs();
        newDir.mkdir();
    }


    public static int getRank(String path) {
        int rank = -1;
        int index = -1;
        do {
            index = path.indexOf("\\", index + 1);
            rank++;
        } while (index != -1);
        return rank;
    }


    public TreeMap<String, String> buildMap(String filesList) {
        try {
            BufferedReader files = new BufferedReader(new FileReader((filesList)));
            TreeMap<String, String> result = new TreeMap<String, String>();

            File root = new File(rootDir);
            int rootRank = getRank(root.getAbsolutePath());
            //System.out.println(rootRank);

            String tmp;
            while ((tmp = files.readLine()) != null) {
                int isFile;
                if (tmp.endsWith("\\") || tmp.endsWith("/")) isFile = 0;
                else isFile = 1;
                tmp = rootDir + sep + tmp;
                File tempFile = new File(tmp);
                String key = tempFile.getAbsolutePath();
                switch (isFile) {
                    case 1:
                        int rank = getRank(key) - rootRank;
                        String value = key + "\r\n" + rank + "\r\n" + verses.get(rank);
                        result.put(key, value);
                        break;
                    case 0:
                        result.put(key, "directory");
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static boolean isCorrectPath(String path) {
//        Pattern pathModel = Pattern.compile("([A-Z]:)?(\\" + sep + "[^\"<>:[*]/|\\\\]*)*");
//        //Pattern pathModel = Pattern.compile("([A-Z]:)?(\\\\[^\"<>:[*]/|\\\\]*)*");
//        return path.matches(pathModel.pattern());
//    }
}
