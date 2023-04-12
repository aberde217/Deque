public class LinkedListDeque <Type> {

    private class TypeNode {
        private Type item;
        private TypeNode next;
        TypeNode prev;
        public TypeNode(Type i, TypeNode n) {
            item = i;
            next = n;
        }
    }

    int size;
    TypeNode sentinel;
    public LinkedListDeque() {
        size = 0;
        sentinel = new TypeNode(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(Type val) {
        size += 1;
        TypeNode L = new TypeNode(val, null);
        TypeNode after = sentinel.next;
        sentinel.next = L;
        L.prev = sentinel;
        if (size == 1) {
            sentinel.prev = L;
            L.next = sentinel;
            return;
        }
        L.next = after;
        after.prev = L;
    }

    public void addLast(Type val) {
        size += 1;
        TypeNode L = new TypeNode(val, null);
        TypeNode before = sentinel.prev;
        L.next = sentinel;
        sentinel.prev = L;
        if (size == 1){
            L.prev = sentinel;
            sentinel.next = L;
            return;
        }
        L.prev = before;
        before.next = L;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TypeNode L = sentinel.next;
        while (L != sentinel) {
            System.out.println(L.item);
            L = L.next;
        }
    }

    public Type removeFirst() {
        if (size == 0)
            return null;
        size -= 1;
        TypeNode currentFirst = sentinel.next;
        Type item = currentFirst.item;
        sentinel.next = currentFirst.next;
        currentFirst.next.prev = sentinel;
        return item;
    }

    public Type removeLast() {
        if (size == 0)
            return null;
        size -= 1;
        TypeNode currentLast = sentinel.prev;
        Type item = currentLast.item;
        sentinel.prev = currentLast.prev;
        currentLast.prev.next = sentinel;
        return item;
    }

    public Type get(int index) {
        int count = 0;
        TypeNode L = sentinel.next;
        while (count != index) {
            L = L.next;
        }
        return L.item;
    }

    public Type getRecursive(TypeNode L, int index, int count) {
        if (count == index) {
            return L.item;
        }
        return getRecursive(L.next, index, count += 1);
    }
    public Type getRecursive(int index) {
        return getRecursive(sentinel.next, index, 0);
    }
}
