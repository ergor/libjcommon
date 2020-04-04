package no.netb.libjcommon.result;

public class Err<V, E> extends Result<V, E> {

    private Err() {
        // hide default constructor
    }

    public Err(E value) {
        this.errVal = value;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }
}
