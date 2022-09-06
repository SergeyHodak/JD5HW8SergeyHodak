package goit.feature.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
public class ProductDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String manufacturer;

    public static ProductDTO fromProduct(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .manufacturer(product.getManufacturer() == null ? "" : product.getManufacturer().getName())
                .build();
    }
}