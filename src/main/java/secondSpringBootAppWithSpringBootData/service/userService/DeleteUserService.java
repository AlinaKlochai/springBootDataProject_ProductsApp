package secondSpringBootAppWithSpringBootData.service.userService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;

@Service
public class DeleteUserService {

    private final UserRepository userRepository;


    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

}
