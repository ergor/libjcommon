package no.netb.libjcommon.result;

public class Err<V, E> extends Result<V, E> {

    private Err() {
        // hide default constructor
    }

    public Err(E value) {
        this.errVal = value;
    }
}
