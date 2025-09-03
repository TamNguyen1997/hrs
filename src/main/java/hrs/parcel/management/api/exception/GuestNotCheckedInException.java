package hrs.parcel.management.api.exception;

public class GuestNotCheckedInException extends RuntimeException {
    public GuestNotCheckedInException() {
        super("Guest not checked in");
    }
}
