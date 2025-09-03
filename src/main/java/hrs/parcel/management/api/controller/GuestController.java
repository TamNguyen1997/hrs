package hrs.parcel.management.api.controller;

import hrs.parcel.management.api.command.GuestCreateCommand;
import hrs.parcel.management.api.command.GuestSearchCommand;
import hrs.parcel.management.api.command.GuestUpdateCommand;
import hrs.parcel.management.api.exception.InvalidGuestException;
import hrs.parcel.management.api.model.Guest;
import hrs.parcel.management.api.service.GuestService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/guests")
public class GuestController {
    private final GuestService guestService;
    private final ModelMapper modelMapper;

    public GuestController(GuestService guestService, ModelMapper modelMapper) {
        this.guestService = guestService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getGuests(GuestSearchCommand command) {
        List<Guest> guests = this.guestService.searchGuests(command);
        return ResponseEntity.ok(guests);
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody @Valid GuestCreateCommand command) {
        Guest guest = modelMapper.map(command, Guest.class);
        Guest createdGuest = this.guestService.createGuest(guest);
        return ResponseEntity.ok(createdGuest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuest(@PathVariable("id")UUID id) {
        Guest guest = this.guestService.getGuest(id);
        return ResponseEntity.ok(guest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable("id")UUID id, @RequestBody @Valid GuestUpdateCommand command) {
        if (!command.getId().equals(id)) {
            throw new InvalidGuestException("Invalid ID in update command");
        }
        Guest guest = modelMapper.map(command, Guest.class);
        Guest createdGuest = this.guestService.updateGuest(guest);
        return ResponseEntity.ok(createdGuest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable("id")UUID id) {
        this.guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
