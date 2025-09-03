package hrs.parcel.management.api.service.validation.parcel;

import hrs.parcel.management.api.model.Guest;

/**
 * An interface for guest validation rules.
 * Each implementation defines a specific condition that determines whether
 * a {@link Guest} is invalid.
 * These validations are used by {@link ParcelValidationService} before
 * accepting a parcel, ensuring that parcels are only accepted for valid guests.
 */
public interface IGuestValidation {
    Boolean isValid(Guest guest);
}
