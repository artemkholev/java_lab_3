import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Введите ваше ФИО: Иванов Иван Иванович");
        try {
            newUser.setUserName(in.nextLine());
        } catch (InvalidInputException error) {
            System.out.println("Неправельный ввод: " + error.getMessage());
        }

        System.out.println("Введите вашу дату рождения: 01.02.1970");
        try {
            newUser.setUserAge(in.nextLine());
        } catch (InvalidInputException error) {
            System.out.println("Неправельный ввод: " + error.getMessage());
        }

        if (newUser.isAuth) {
            System.out.println(newUser.getInfoUser());
        }
    }
}
