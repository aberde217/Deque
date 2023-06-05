import java.util.Comparator;
public class MaxArrayDeque<Type> extends ArrayDeque<Type> {
    private Comparator<Type> comparator;
    public MaxArrayDeque(Comparator<Type> c) {
        super();
        comparator = c;
    }

    public Type max(){
        if (isEmpty())
            return null;
        Type max = this.get(0);
        for (Type i : this) {
            if (comparator.compare(i, max) > 0)
                max = i;
        }
        return max;
    }

    public Type max(Comparator<Type> c) {
        if (isEmpty())
            return null;
        Type max = this.get(0);
        for (Type i : this) {
            if (c.compare(i, max) > 0)
                max = i;
        }
        return max;
    }
}
