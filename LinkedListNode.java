public class LinkedListNode<I extends Comparable<I>> {
    private I element;
    private LinkedListNode<I> next;

    public LinkedListNode(I element) {
        this.element = element;
        this.next = null;
    }

    public LinkedListNode(I element, LinkedListNode<I> next) {
        this.element = element;
        this.next = next;
    }

    public I getData() {
        return element;
    }

    public void setData(I element) {
        this.element = element;
    }

    public LinkedListNode<I> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<I> next) {
        this.next = next;
    }

    public I getElement() {
        return getData();
    }

    public void setElement(I element) {
        setData(element);
    }
}
