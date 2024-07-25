package secondSpringBootAppWithSpringBootData.service.util;


import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserNewDTO;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserWithIdDTO;
import secondSpringBootAppWithSpringBootData.entity.Role;
import secondSpringBootAppWithSpringBootData.entity.State;
import secondSpringBootAppWithSpringBootData.entity.User;

@Service
public class UserConvert {
    public User fromDTOtoUser(UserNewDTO user, Role role, State state) {
        return   new User(user.getName(),
                user.getPassword(),
                user.getEmail(),
                 role,state,"");
    }
    public User fromIdDTOtoUser(UserWithIdDTO user, Role role, State state) {
        return   new User(user.getId(), user.getName(),
                user.getPassword(),
                user.getEmail(), role, state,"");
    }
}
