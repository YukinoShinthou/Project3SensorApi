package Project3TZServer.ServerSide.util;

public class MeasurementsErrorResponse {
    private String Message;
    private long timestamp;

    public MeasurementsErrorResponse(String message, long timestamp) {
        Message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
