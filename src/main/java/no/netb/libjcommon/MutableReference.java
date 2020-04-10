package no.netb.libjcommon;

import java.lang.reflect.Array;

public class MutableReference<T> {

    private T[] ref;

    public MutableReference(Class<T> type, T value) {
        ref = (T[]) Array.newInstance(type, 1);
        ref[0] = value;
    }

    public T get() {
        return ref[0];
    }

    public void set(T value) {
        ref[0] = value;
    }
}
