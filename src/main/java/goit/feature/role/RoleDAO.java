package goit.feature.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoleDAO {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void create(Role role) {
        UUID uuid;
        do{
            uuid = UUID.randomUUID();
        }while (roleRepository.existsById(uuid));
        role.setId(uuid);
        roleRepository.save(role);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }
}