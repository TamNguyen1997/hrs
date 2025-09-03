package hrs.parcel.management.api.repository;

import hrs.parcel.management.api.orm.GuestDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface GuestRepository extends JpaRepository<GuestDb, UUID>, JpaSpecificationExecutor<GuestDb> {
}