package hrs.parcel.management.api.repository.specification;

import hrs.parcel.management.api.orm.GuestDb;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class GuestSpecification {
    public static Specification<GuestDb> hasId(UUID id) {
        return (root, query, cb) ->
                id == null ? cb.conjunction() : cb.equal(root.get("id"), id);
    }

    public static Specification<GuestDb> hasEmail(String email) {
        return (root, query, cb) ->
                email == null ? cb.conjunction() : cb.equal(root.get("email"), email);

    }
    public static Specification<GuestDb> hasName(String name) {
        return (root, query, cb) ->
                name == null ? cb.conjunction() : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<GuestDb> isCheckedIn(Boolean checkedIn) {
        return (root, query, cb) ->
                checkedIn == null ? cb.conjunction() : cb.equal(root.get("checkedIn"), checkedIn);
    }
    
    public static Specification<GuestDb> isCheckedOut(Boolean checkedOut) {
        return (root, query, cb) ->
                checkedOut == null ? cb.conjunction() : cb.equal(root.get("checkedIn"), checkedOut);
    }
}
