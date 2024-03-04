import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      /*  System.out.println("Введите первое число");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число");
        int secondNumber = new Scanner(System.in).nextInt();
        System.out.println("Вы ввели: " + firstNumber + " и " + secondNumber + "\n" +
 "Их сумма: " + (firstNumber+secondNumber) + "\n" +
                "Их разность: " + (firstNumber - secondNumber)  + "\n" +
                "Их произведение: " + firstNumber * secondNumber + "\n" +
                "Их частное: " + (double)firstNumber/secondNumber);

                */
        boolean isFileExist, isDirectory;
        int count = 0;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            isFileExist = file.exists();
            isDirectory = file.isDirectory();
            if (isFileExist && !isDirectory){
                count++;
                System.out.println("Путь указан верно\nЭто " + count + " верно указанный файл");
                continue;
            }
            if (isFileExist) {
                System.out.println("Указан путь не к файлу, а к папке\nПерепроверьте и попробуйте ещё раз");
                continue;
            }
            System.out.println("Указанный путь не существует\nПерепроверьте и попробуйте ещё раз");
        }
    }
}
