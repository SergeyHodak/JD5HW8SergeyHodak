package goit.feature.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductDAO {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void create(Product product) {
        UUID uuid;
        do{
            uuid = UUID.randomUUID();
        }while (productRepository.existsById(uuid));
        product.setId(uuid);
        productRepository.save(product);
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}