import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число");
        int secondNumber = new Scanner(System.in).nextInt();
        System.out.println("Вы ввели: " + firstNumber + " и " + secondNumber + "\n" +
 "Их сумма: " + (firstNumber+secondNumber) + "\n" +
                "Их разность: " + (firstNumber - secondNumber)  + "\n" +
                "Их произведение: " + firstNumber * secondNumber + "\n" +
                "Их частное: " + (double)firstNumber/secondNumber);
    }
}
