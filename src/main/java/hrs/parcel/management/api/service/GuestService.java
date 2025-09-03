package hrs.parcel.management.api.service;

import hrs.parcel.management.api.command.GuestSearchCommand;
import hrs.parcel.management.api.exception.ResourceNotFoundException;
import hrs.parcel.management.api.model.Guest;
import hrs.parcel.management.api.orm.GuestDb;
import hrs.parcel.management.api.repository.GuestRepository;
import hrs.parcel.management.api.repository.specification.GuestSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;
    public GuestService(GuestRepository guestRepository, ModelMapper modelMapper) {
        this.guestRepository = guestRepository;
        this.modelMapper = modelMapper;
    }

    public Guest getGuest(UUID id) {
        GuestDb guestDb = this.guestRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cannot find guest with ID: " + id)
                );

        return modelMapper.map(guestDb, Guest.class);
    }

    public Guest createGuest(Guest guest) {
        GuestDb guestDb = modelMapper.map(guest, GuestDb.class);
        GuestDb savedGuestDb = this.guestRepository.save(guestDb);
        return this.modelMapper.map(savedGuestDb, Guest.class);
    }

    public Guest updateGuest(Guest guest) {
        GuestDb guestDb = this.guestRepository
                .findById(guest.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cannot find guest with ID: " + guest.getId())
                );

        modelMapper.map(guest, guestDb);

        guestDb = this.guestRepository.save(guestDb);

        return modelMapper.map(guestDb, Guest.class);

    }

    public List<Guest> searchGuests(GuestSearchCommand command) {
        var spec = Specification.where(GuestSpecification.hasId(command.getId()))
                .and(GuestSpecification.hasName(command.getName()))
                .and(GuestSpecification.hasEmail(command.getEmail()))
                .and(GuestSpecification.isCheckedIn(command.getCheckedIn()))
                .and(GuestSpecification.isCheckedOut(command.getCheckedOut()));

        return guestRepository
                .findAll(spec)
                .stream()
                .map(guestDb -> modelMapper.map(guestDb, Guest.class)).toList();
    }

    public void deleteGuest(UUID id) {
        this.guestRepository.deleteById(id);
    }
}
