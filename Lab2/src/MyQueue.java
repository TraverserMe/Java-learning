public class MyQueue<E> {
    private MyLinkedList<E> list = new MyLinkedList<E>();

    public void enqueue(E e) {
        list.add(e);
    }

    public E dequeue() {
        E first = list.get(0);
        list.remove(0);
        return first;
    }

    public int getSize() {
        return list.size();
    }

    public MyLinkedList<E> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Queue: " + list.toString();
    }
}