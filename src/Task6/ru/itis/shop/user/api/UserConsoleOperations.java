package Task6.ru.itis.shop.user.api;

import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.application.UserService;

import java.util.List;
import java.util.Optional;
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
                findById();
            }
            break;
            case "4": {
                updateDescription();
            }
            break;
            case "5": {
                showAll();
            }
            break;
            case "6": {
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
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Найти всех пользователей");
        System.out.println("6. Найти пользователей по описанию");
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

    private void findById(){
        System.out.println("Введите нужный id:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Сейчас поищем");
        Optional<UserDto> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            System.out.println("Email: " + user.getEmail() + ", описание: " + user.getProfileDescription());
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void updateDescription(){
        System.out.println("Обновление описания профиля");
        System.out.println("Введите email пользователя");
        String email = scanner.nextLine();
        System.out.println("Введите новое описание");
        String description = scanner.nextLine();


        Optional<UserDto> updatedUser = userService.updateDescription(email, description);
        if (updatedUser.isPresent()) {
            System.out.println("Описание обновлено");
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void showAll(){
        System.out.println("Сейчас выведем всех пользователей:");
        List<UserDto> users = userService.showAll();

        if (users.isEmpty()) {
            System.out.println("Пользователи не найдены");
        } else {
            for (UserDto user : users) {
                System.out.println("id: " + user.getId() +
                        ", Email: " + user.getEmail() +
                        ", Описание: " + user.getProfileDescription());
            }
        }
    }

    private void showWithEqualDescription() {
        System.out.println("Введите описание по которому нужно найти");
        String description = scanner.nextLine();

        System.out.println("Сейчас выведем всех пользователей с таким описанием:");
        List<UserDto> users = userService.showWithEqualDescription(description);

        if (users.isEmpty()) {
            System.out.println("Пользователи не найдены");
        } else {
            for (UserDto user : users) {
                System.out.println("id: " + user.getId() +
                        ", Email: " + user.getEmail() +
                        ", Описание: " + user.getProfileDescription());
            }
        }
    }
}
