package Task6.ru.itis.shop.user.application;

import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.domain.User;
import Task6.ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Пользователь с таким email уже есть");
            return;
        }
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public Optional<UserDto> findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(UserMapper.toDto(user));
        }
        return Optional.empty();
    }

    public Optional<UserDto> updateDescription(String email, String description) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfileDescription(description);
            userRepository.updateUserDescription(user);
            return Optional.of(UserMapper.toDto(user));
        }
        return Optional.empty();
    }

    public List<UserDto> showAll() {
        List<User> list = userRepository.findAll();
        return UserMapper.toDtoList(list);
    }

    public List<UserDto> showWithEqualDescription(String description) {
        List<User> list = userRepository.findAllByProfileDescription(description);
        return UserMapper.toDtoList(list);
    }
}
