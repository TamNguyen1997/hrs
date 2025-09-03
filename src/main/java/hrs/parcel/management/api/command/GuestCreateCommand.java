package hrs.parcel.management.api.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestCreateCommand {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private Boolean checkedIn;
    private Boolean checkedOut = false;
}
