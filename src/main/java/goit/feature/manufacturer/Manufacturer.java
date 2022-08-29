package goit.feature.manufacturer;

import goit.feature.product.Product;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Table(name = "manufacturer")
@Data
@Entity
public class Manufacturer {
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

    @OneToMany(mappedBy= "manufacturer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Product> products;

    public Manufacturer() {}
}