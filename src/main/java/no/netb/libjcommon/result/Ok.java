package no.netb.libjcommon.result;

public class Ok<V, E> extends Result<V, E> {

    private Ok() {
        // hide default constructor
    }

    public Ok(V value) {
        this.okVal = value;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }
}
