package goit.feature.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ManufacturerDAO {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findByName(String name) {
        return manufacturerRepository.findByName(name);
    }

    @Transactional
    public void create(Manufacturer manufacturer) {
        UUID uuid;
        do{
            uuid = UUID.randomUUID();
        }while (manufacturerRepository.existsById(uuid));
        manufacturer.setId(uuid);
        manufacturerRepository.save(manufacturer);
    }

    public void update(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public void deleteById(UUID id) {
        manufacturerRepository.deleteById(id);
    }
}