package goit.feature.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID>, JpaSpecificationExecutor<Manufacturer> {
    @Query(
            nativeQuery = true,
            value = "SELECT id, name FROM manufacturer WHERE name = :name")
    Manufacturer findByName(@Param("name") String name);
}