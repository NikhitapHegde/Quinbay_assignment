import java.util.Arrays;

public class MyList<T> {
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public MyList() {
        array = (T[]) new Object[100];
        size = 0;
    }

    public void add(T value) {
        if (size == array.length) {
            growArray();
        }
        array[size++] = value;
    }

    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        shrinkArrayIfNeeded();
    }

    public boolean deleteByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index];
    }

    private void growArray() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    private void shrinkArrayIfNeeded() {
        if (size < array.length / 4 && array.length > 100) {
            array = Arrays.copyOf(array, array.length / 2);
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        array = Arrays.copyOf(array, 100);
    }
}
