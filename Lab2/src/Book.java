
public class Book {
    private String title;
    private String ISBN;
    private boolean available;
    private MyQueue<String> reservedQueue = new MyQueue<>();

    void setTitle(String t) {
        title = t;
    }

    String getTitle() {
        return title;
    }

    void setISBN(String isbn) {
        ISBN = isbn;
    }

    String getISBN() {
        return ISBN;
    }

    void setAvailable(boolean a) {
        available = a;
    }

    boolean getAvailable() {
        return available;
    }

    void setReservedQueue(MyQueue<String> ReservedQueue) {
        reservedQueue = ReservedQueue;
    }

    MyQueue<String> getReservedQueue() {
        return reservedQueue;
    }

    public boolean isAvailable() {
        return available;
    }

}
