package space.aureg.seedbox.stil4m.transmission.http;

public class InvalidResponseStatus extends Throwable {

    private final int statusCode;

    public InvalidResponseStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return String.valueOf(statusCode);
    }
}
