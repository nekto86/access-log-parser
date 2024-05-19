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

        logLine[] LogLines = new logLine[countLines + 1]; //Создаю массив объектов logLine, где logLine - объект, содержащий элементы парсинга логов
        //BufferedReader reader = new BufferedReader(fileReader);
        File file = new File(path);
        Scanner sc = new Scanner(file);
        String[] result = null;
        for (int i = 0; i < countLines; i++) {
            result = parseLine(sc.nextLine());
            LogLines[i] = new logLine(result[0], result[1], result[2], result[3], result[4], result[5], result[6]);
            //System.out.println(LogLines[i]);
        }

        whatBot(LogLines[176124]);

        int countYandexBot = 0, countGoogleBot = 0;
        for (int i = 0; i < LogLines.length - 1; i++) {

            if (whatBot(LogLines[i]) == 1) countYandexBot++;
            else if (whatBot(LogLines[i]) == 2) countGoogleBot++;
        }
        System.out.println("Всего было выполнено: " + countLines + " запросов, из них " + countYandexBot + " к Яндекс боту и " + countGoogleBot + " к Гугл боту");

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
    /*public static int whatBot(logLine line) {                   //Метод который вычленяет название бота из объекта строки лога
        String[] UserAgent = line.getUserAgent().split(";");                 //Разбиваем на части строку
        String bot="";
        if (UserAgent.length > 1) {                                 //Т.к. у нас есть ситуация, когда заместо значения у нас прочерк, надо выполнить проверку, а точно ли объект подходящий для нас
            bot = UserAgent[1];
        } else return 0;
        UserAgent = bot.split("[\\/ ]");         //Переиспользуем массив UserAgent для разбивки блока с указанием названия бота, чтобы выкинуть всё то, что идёт после слеша, не забываем про экранирование
        if (UserAgent.length > 1) bot = UserAgent[1];         //Переиспользуем переменную, теперь в ней хранится название бота
        if (bot.equals("YandexBot")) return 1;
        else if (bot.equals("Googlebot")) return 2;
        return 0;
    }*/   //Метод по подсчёту ботов в решении, которое описано в задании (не работает, т.к. в логах есть витеиватые User-Agent

    public static int whatBot(logLine line) {                   //Метод который вычленяет название бота из объекта строки лога
        if (line.getUserAgent().length() < 5) return 0;             //Если нам пришла пустая строка/с пробелом/дефисом, да чёрт знаем каким ещё прочерком
        String bot = line.getUserAgent();                       //Забираем User-Agent
        if (bot.contains("YandexBot")) return 1;            //Ищем необходимое значение
        if (bot.contains("Googlebot")) return 2;
        return 0;
    }
}



