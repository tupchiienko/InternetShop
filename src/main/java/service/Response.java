package service;

public class Response<T> {
    private final T value;
    private final boolean isSuccessful;
    private final String message;

    public Response(T value, boolean isSuccessful, String message) {
        this.value = value;
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "value=" + value +
                ", isSuccessful=" + isSuccessful +
                ", message='" + message + '\'' +
                '}';
    }
}
