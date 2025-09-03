package hrs.parcel.management.api.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GuestUpdateCommand {
    @NotNull
    private UUID id;
    private String name;
    private String email;
    private Boolean checkedIn;
    private Boolean checkedOut;
}
