import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isFileExist, isDirectory;
        int count = 0;
        String path;
        while (true) {
            path = new Scanner(System.in).nextLine();
            File file = new File(path);
            isFileExist = file.exists();
            isDirectory = file.isDirectory();
            if (isFileExist && !isDirectory) {
                count++;
                System.out.println("Путь указан верно\nЭто " + count + " верно указанный файл");
                break;
            }
            if (isFileExist) {
                System.out.println("Указан путь не к файлу, а к папке\nПерепроверьте и попробуйте ещё раз");
                continue;
            }
            System.out.println("Указанный путь не существует\nПерепроверьте и попробуйте ещё раз");
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int countLine = 0, maxLenLine = Integer.MIN_VALUE, minLenLine = Integer.MAX_VALUE, tmp = 0;

        try (BufferedReader reader = new BufferedReader(fileReader);) {
            String line;
            while ((line = reader.readLine()) != null) {
                countLine++;
                //line.length() > maxLenLine ? maxLenLine = line.length() : (line.length() < minLenLine ? minLenLine = line.length());
                if (line.length() > maxLenLine) maxLenLine = line.length();
                else if (line.length() < minLenLine) minLenLine = line.length();
                int length = line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("В файле всего " + countLine + " строк\nДлина самой длинной строки: " + maxLenLine + " символов\nДлина самой короткой строки: " + minLenLine);
        }
        // Scanner sc = new Scanner()

    }
}
