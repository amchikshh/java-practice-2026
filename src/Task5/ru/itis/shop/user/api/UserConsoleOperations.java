package Task5.ru.itis.shop.user.api;

import Task5.ru.itis.shop.user.application.UserService;
import org.postgresql.util.OSUtil;

import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                showAll();
            }
            break;
            case "4": {
                showWithEqualDescription();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти всех пользователей");
        System.out.println("4. Найти пользователей по описанию");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void showAll(){
        System.out.println("Сейчас выведем всех пользователей:");
        if (userService.showAll()) {
            System.out.println("Пользователи успешно выведены");
        } else {
            System.out.println("Пользователи не найдены");
        }
    }

    private void showWithEqualDescription() {
        System.out.println("Введите описание по которому нужно найти");
        String description = scanner.nextLine();

        System.out.println("Сейчас выведем всех пользователей с таким описанием:");
        if (userService.showWithEqualDescription(description)) {
            System.out.println("Пользователи успешно выведены");
        } else {
            System.out.println("Пользователи не найдены");
        }
    }

}
