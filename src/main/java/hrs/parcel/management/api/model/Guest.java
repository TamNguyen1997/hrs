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
public class Guest {
    private UUID id;
    private String name;
    private String email;
    private Boolean checkedIn;
    private Boolean checkedOut;
}
