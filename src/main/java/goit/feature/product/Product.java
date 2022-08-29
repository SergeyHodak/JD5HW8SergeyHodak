package goit.feature.product;

import goit.feature.manufacturer.Manufacturer;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Table(name = "product")
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    @NonNull
    private BigDecimal price;

    @ManyToOne(cascade={CascadeType.REMOVE})
    @JoinColumn(name="manufacturer_id", nullable=false)
    private Manufacturer manufacturer;

    public Product() {}
}