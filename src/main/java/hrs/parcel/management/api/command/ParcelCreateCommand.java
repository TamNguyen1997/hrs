package hrs.parcel.management.api.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ParcelCreateCommand {
    @NotNull
    private String name;
    @NotNull
    private UUID guestId;
    private Boolean pickedUp = false;
}
