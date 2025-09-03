package hrs.parcel.management.api.repository;

import hrs.parcel.management.api.orm.ParcelDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParcelRepository extends JpaRepository<ParcelDb, UUID> {
}
