package Task4.ru.itis.shop.user.application;

import Task4.ru.itis.shop.user.domain.User;
import Task4.ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public boolean updateDescription(String email, String description) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            userOptional.get().setProfileDescription(description);
            userRepository.updateUserDescription(userOptional.get());
            return true;
        } else return false;
    }

    public void showAll() {
        List<User> list = userRepository.findAll();
        for (User user : list){
            System.out.println("Имя пользователя - " + user.getName() + ", почта пользователя - " + user.getEmail());
        }
    }
}
