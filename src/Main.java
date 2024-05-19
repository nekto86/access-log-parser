import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean isFileExist, isDirectory;
        int count = 0, countLines = 0;
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
            fileReader = new FileReader(path);                          //Пробую считать файл и если не получается, выбрасываю ошибку
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //String ip, date, method, answer, size, url, userAgent;              //TODO Разобраться, нужны переменные или можно выбросить при работе с парсингом
        try (BufferedReader reader = new BufferedReader(fileReader);) {                     //Считываю строку файла
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 1024)
                    throw new RuntimeException("Длина строки превышает 1024 символа");          //Если строка более чем в 1024 символа в длину - выбрасываю эксепшн
                countLines++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        logLine[] LogLines = new logLine[countLines+1]; //Создаю массив объектов logLine, где logLine - объект, содержащий элементы парсинга логов
        //BufferedReader reader = new BufferedReader(fileReader);
        File file = new File(path);
        Scanner sc = new Scanner(file);
        String[] result;
        for (int i = 0; i < countLines; i++) {
            result = parseLine(sc.nextLine());
            LogLines[i] = new logLine(result[0],result[1],result[2],result[3],result[4],result[5],result[6]);
            System.out.println(LogLines[i]);
        }


    }

    public static String[] parseLine(String line) {          //Метод, который берёт строку логов и парсит её на части...ну тут прям грязь будет, прям фу, да...
        String[] result = new String[7], parse;
        parse = line.split(" ", 4);      //Выделяем ip адрес
        result[0] = parse[0];
        parse = parse[3].split("[\\[\\]]", 3);     //Выделяем дату
        result[1] = parse[1];
        parse = parse[2].split("\"", 3);
        result[2] = parse[1];                   //Выделяем запрос
        parse = parse[2].split(" ", 4);
        result[3] = parse[1];               //Выделяем код ответа
        result[4] = parse[2];               //Выделяем размер ответа
        parse = parse[3].split("\"", 5);
        result[5] = parse[1];             //Выделяем страницу редиректа
        result[6] = parse[3];             //Выделяем User-Agent
        //TODO Я уверен, что это можно сделать адекватнее, мб стоит попробовать потом в рамках самосовершенствования

        return result;
    }
}
