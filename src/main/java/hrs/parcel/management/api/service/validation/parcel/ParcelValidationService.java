package hrs.parcel.management.api.service.validation.parcel;

import hrs.parcel.management.api.exception.InvalidGuestException;
import hrs.parcel.management.api.model.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelValidationService {
    private final List<IGuestValidation> guestValidations;

    public ParcelValidationService(List<IGuestValidation> guestValidations) {
        this.guestValidations = guestValidations;
    }

    public void validate(Guest guest) {
        guestValidations.forEach(validation -> {
            if (validation.isValid(guest)) {
                throw new InvalidGuestException("Guest either doesn't exist, hasn't checked in or has already checked out");
            }
        });
    }
}
