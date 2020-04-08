package no.netb.libjcommon.result;

public class ResultException extends RuntimeException {
    public ResultException() {
    }

    public ResultException(String s) {
        super(s);
    }

    public ResultException(Throwable throwable) {
        super(throwable);
    }

    public ResultException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
