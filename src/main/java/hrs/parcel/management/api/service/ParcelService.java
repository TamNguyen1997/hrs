package hrs.parcel.management.api.service;

import hrs.parcel.management.api.model.Guest;
import hrs.parcel.management.api.model.Parcel;
import hrs.parcel.management.api.orm.ParcelDb;
import hrs.parcel.management.api.repository.ParcelRepository;
import hrs.parcel.management.api.service.validation.parcel.ParcelValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final ParcelValidationService parcelValidationService;
    private final GuestService guestService;
    private final ModelMapper modelMapper;

    public ParcelService(
            ParcelRepository parcelRepository,
            GuestService guestService,
            ParcelValidationService parcelValidationService,
            ModelMapper modelMapper) {
        this.parcelRepository = parcelRepository;
        this.parcelValidationService = parcelValidationService;
        this.guestService = guestService;
        this.modelMapper = modelMapper;
    }

    public List<Parcel> getAll() {
        return this.parcelRepository
                .findAll()
                .stream()
                .map(parcelDb -> modelMapper.map(parcelDb, Parcel.class))
                .toList();
    }
    public Parcel createParcel(Parcel parcel) {
        Guest guest = this.guestService.getGuest(parcel.getGuestId());
        parcelValidationService.validate(guest);
        ParcelDb parcelDb = modelMapper.map(parcel, ParcelDb.class);
        ParcelDb savedParcelDb = this.parcelRepository.save(parcelDb);
        return this.modelMapper.map(savedParcelDb, Parcel.class);
    }

    public void deleteParcel(UUID id) {
        this.parcelRepository.deleteById(id);
    }
}
