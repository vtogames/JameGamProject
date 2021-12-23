import java.io.*;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class FileIO {

    public static String readFile(String filename) throws IOException {
        String line = "";

        String singleLine = "yieldREADER";
        InputStream source = Reader.class.getResourceAsStream("/" + filename);
        InputStream inputStream = new BufferedInputStream(source);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((singleLine = reader.readLine()) != null) {
            if (!singleLine.equals("")) {
                line += singleLine;
            }

        }

        return line;
    }

    public static boolean writeFile(String path, String[] things) throws IOException, URISyntaxException {

        File filep = new File(FileIO.class.getResource("/").getFile(), path);

        boolean toReturn = filep.createNewFile();

        FileChannel.open(filep.toPath(), StandardOpenOption.WRITE).truncate(0).close();

        FileOutputStream file = new FileOutputStream(filep);

        for (int i2 = 0; i2 < things.length; i2++) {
            String st = things[i2];

            String a = System.getProperty("line.separator");

            char ch[] = st.toCharArray();

            char ch2[] = a.toCharArray();

            for (int i = 0; i < st.length(); i++) {
                file.write(ch[i]);
            }

            if (!(i2 + 1 == things.length)) {
                for (int i = 0; i < a.length(); i++) {
                    file.write(ch2[i]);
                }
            }

        }

        file.close();
        return toReturn;
    }

}
