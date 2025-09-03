package hrs.parcel.management.api.command;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GuestSearchCommand {
    private UUID id;
    private String name;
    private String email;
    private Boolean checkedIn;
    private Boolean checkedOut;
}
