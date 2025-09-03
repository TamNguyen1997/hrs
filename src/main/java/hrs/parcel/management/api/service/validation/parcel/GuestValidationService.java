package hrs.parcel.management.api.service.validation.parcel;

import hrs.parcel.management.api.model.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestValidationService implements IGuestValidation {
    @Override
    public Boolean isValid(Guest guest) {
        return guest == null || !guest.getCheckedIn() || guest.getCheckedOut();
    }
}
