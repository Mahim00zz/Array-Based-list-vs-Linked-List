

public class LinkedList<I extends Comparable<I>> implements ListInterface<I> {

    java.util.LinkedList<I> list = new java.util.LinkedList<I>();

    @Override
    public ListInterface<I> copy() {
        LinkedList<I> newList = new LinkedList<>();
        for (I element : list) {
            // Perform a deep copy if I is mutable
            newList.add(element);
        }
        return newList;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void add(I element) {
        list.add(element);
    }

    @Override
    public void add(I element, int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index <= list.size()) {
            list.add(index, element);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public void addSorted(I element) {
        int i = 0;
        for (I a : list) {
            if (element.compareTo(a) < 0) {
                break;
            }
            i++;
        }

        add(element, i);
    }

    @Override
    public I get(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public I replace(I element, int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < list.size()) {
            return list.set(index, element);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public I remove(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < list.size()) {
            return list.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public void removeAll() {
        list.clear();
    }
}
