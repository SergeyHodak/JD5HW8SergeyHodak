package goit.feature.user;

import goit.feature.role.Role;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String roles;

    public static UserDTO fromUser(User user) {
        Set<String> result = new HashSet<>();
        for (Role role : user.getRoles()) {
            result.add(role.getName());
        }
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(String.valueOf(result))
                .build();
    }
}