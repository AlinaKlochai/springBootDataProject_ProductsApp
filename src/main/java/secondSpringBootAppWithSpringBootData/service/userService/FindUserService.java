package secondSpringBootAppWithSpringBootData.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.service.util.UserConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    //private final ProductRepository productRepository;




    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        }

        return users.stream()
                .map(userConverter::userToDto)
                .collect(Collectors.toList());

    }


    public UserResponseDto findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userConverter.userToDto(userOptional.get());
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    public UserResponseDto findUserByName(String name) {
        Optional<User> userOptional = userRepository.findUserByName(name);
        if (userOptional.isPresent()) {
            return userConverter.userToDto(userOptional.get());
        }else {
            throw new NotFoundException("User not found with name: " + name);
        }
    }


    public UserResponseDto findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            return userConverter.userToDto(userOptional.get());
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

//    public List<Product> getProductsByClientId(Integer clientId) {
//        return productRepository.findAllByClientClientId(clientId);
//    }

}
