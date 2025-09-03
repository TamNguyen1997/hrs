package hrs.parcel.management.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hrs.parcel.management.api.command.ParcelCreateCommand;
import hrs.parcel.management.api.model.Parcel;
import hrs.parcel.management.api.service.ParcelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/parcels")
public class ParcelController {
    private final ParcelService parcelService;
    private final ObjectMapper objectMapper;

    public ParcelController(ParcelService filterService, ObjectMapper objectMapper) {
        this.parcelService = filterService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<Parcel>> getParcels() {
        List<Parcel> parcels = this.parcelService.getAll();
        return ResponseEntity.ok(parcels);
    }

    @PostMapping
    public ResponseEntity<Parcel> createParcel(@RequestBody @Valid ParcelCreateCommand command) {
        Parcel parcel = this.objectMapper.convertValue(command, Parcel.class);
        Parcel createdParcel = this.parcelService.createParcel(parcel);
        return ResponseEntity.ok(createdParcel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcel(@PathVariable("id")UUID id) {
        this.parcelService.deleteParcel(id);
        return ResponseEntity.noContent().build();
    }
}
