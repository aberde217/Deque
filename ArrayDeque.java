import java.util.Iterator;
public class ArrayDeque <Type> implements Iterable<Type>{
    private int size;
    private Type[] arr;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_SIZE = 8;

    public ArrayDeque() {
        arr = (Type[]) new Object[INITIAL_SIZE];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void resizeUp() {
        int newCapacity = size * 2;
        Type[] newArr = (Type[]) new Object[newCapacity];
        int destPos = (newArr.length-1) - (arr.length - nextFirst);
        System.arraycopy(arr, 0, newArr, 0, nextLast);
        System.arraycopy(arr, nextFirst + 1, newArr, destPos, (arr.length-1) - nextFirst);
        arr = newArr;
        nextFirst = destPos - 1;
    }

    public void resizeDown() {
        int newCapacity = arr.length/2;
        Type[] newArr = (Type[]) new Object[newCapacity];
        int destPos = (newArr.length-1) - ((arr.length-1) - nextFirst) + 1;
        System.arraycopy(arr, 0, newArr, 0, nextLast);
        System.arraycopy(arr, nextFirst + 1, newArr, destPos, (arr.length-1) - nextFirst);
        arr = newArr;
        nextFirst = destPos - 1;


    }

    public void addFirst(Type item) {
        if (arr[nextFirst] != null)
            resizeUp();
        if (nextFirst == 0)
            nextFirst = arr.length - 1;
        arr[nextFirst] = item;
        nextFirst -= 1;
        size += 1;

    }

    public void addLast(Type item) {
        if (arr[nextLast] != null)
            resizeUp();
        arr[nextLast] = item;
        if (nextLast == arr.length-1)
            nextLast = 0;
        else
            nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < nextLast; i++) {
            if (arr[i] != null)
                System.out.print(arr[i] + " ");
        }
    }

    public Type removeFirst() {
        if (size == 0)
            return null;
        Type oldFirst = arr[nextFirst-1];
        if (size <= arr.length/4)
            resizeDown();
        if (nextFirst == arr.length -1)
            nextFirst = 0;
        else
            nextFirst += 1;
        arr[nextFirst] = null;
        size -= 1;
        return oldFirst;
    }

    public Type removeLast() {
        if (size == 0)
            return null;
        Type oldLast = arr[nextLast-1];
        if (size <= arr.length/4) {
            resizeDown();
        }
        if(nextLast == 0)
            nextLast = arr.length - 1;
        else
            nextLast -= 1;
        arr[nextLast] = null;
        size -= 1;
        return oldLast;
    }

    public Type get(int index) {
        return arr[index];
    }
    
    public Iterator<Type> iterator() {
        return new ArrayDequeIterator();
    }
    
        public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof ArrayDeque<?>))
            return false;
        else if (o == null)
            return false;
        ArrayDeque arr = (ArrayDeque) o;
        for(int i = 0; i < size; i++) {
            if(!(arr.get(i).equals(get(i))))
                return false;
        }
        return true;
    }
    
    private class ArrayDequeIterator implements Iterator<Type> {
        int pos;
        private ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        public Type next() {
            Type prev = arr[pos];
            pos += 1;
            return prev;
        }
    }

    
    
    
}
