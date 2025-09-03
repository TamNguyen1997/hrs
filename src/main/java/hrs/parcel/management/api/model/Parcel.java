package hrs.parcel.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parcel {
    private UUID id;
    private UUID guestId;
    private String name;
    private Boolean pickedUp;
}