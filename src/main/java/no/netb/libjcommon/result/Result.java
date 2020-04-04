package no.netb.libjcommon.result;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Result<V, E> {

    V okVal = null;
    E errVal = null;

    public abstract boolean isOk();
    public abstract boolean isErr();

    public static<V, E> Ok<V, E> ok(V value) {
        return new Ok<>(value);
    }

    public static<V, E> Err<V, E> err(E value) {
        return new Err<>(value);
    }

    /**
     * Converts from {@link Result} to {@link Optional}.
     * @return An optional of the value, discarding the error.
     */
    public Optional<V> getOk() {
        return Optional.ofNullable(okVal);
    }

    /**
     * Converts from {@link Result} to {@link Optional}.
     * @return An optional of the value, discarding the ok value.
     */
    public Optional<E> getErr() {
        return Optional.ofNullable(errVal);
    }

    /**
     * Maps a {@link Result}{@literal <V, E>} to a {@link Result}{@literal <W, E>}
     * by applying a function to a contained {@link Ok} value, leaving any {@link Err} untouched.
     */
    public <W> Result<W, E> map(Function<V, W> fn){
        if (isOk()) {
            return new Ok<>(fn.apply(okVal));
        }
        return new Err<>(errVal);
    }

    /**
     * Applies a function to the contained value if it is an {@link Ok},
     * otherwise return the fallback value.
     * See {@link #mapOrGet(Supplier, Function)} for the lazy version.
     */
    public <W> W mapOr(W fallback, Function<V, W> fn){
        if (isOk()) {
            return fn.apply(okVal);
        }
        return fallback;
    }

    /**
     * Applies a function to the contained value if it is an {@link Ok},
     * otherwise evaluates the fallback supplier.
     * See {@link #mapOr(Object, Function)} for the eager version.
     */
    public <W> W mapOrGet(Supplier<W> fallback, Function<V, W> fn){
        if (isOk()) {
            return fn.apply(okVal);
        }
        return fallback.get();
    }

    /**
     * Maps a {@link Result}{@literal <V, E>} to a {@link Result}{@literal <V, F>}
     * by applying a function to a contained {@link Err} value, leaving any {@link Ok} untouched.
     */
    public <F> Result<V, F> mapErr(Function<E, F> fn) {
        if (isErr()) {
            return new Err<>(fn.apply(errVal));
        } else {
            return new Ok<>(okVal);
        }
    }

    /**
     * Unwraps a result, yielding the content of an {@link Ok}.
     * @throws ResultException If the value is an {@link Err}
     */
    public V unwrap() {
        if (isErr()) {
            throw new ResultException();
        }
        return okVal;
    }

    /**
     * Unwraps a result, yielding the content of an {@link Ok}.
     * @throws ResultException If the value is an {@link Err}
     * @param msg The exception message
     */
    public V expect(String msg) {
        if (isErr()) {
            throw new ResultException(msg);
        }
        return okVal;
    }

    /**
     * Unwraps a result, yielding the content of an {@link Err}.
     * @throws ResultException If the value is an {@link Ok}
     */
    public E unwrapErr() {
        if (isOk()) {
            throw new ResultException();
        }
        return errVal;
    }

    /**
     * Unwraps a result, yielding the content of an {@link Err}.
     * @throws ResultException If the value is an {@link Ok}
     * @param msg The exception message
     */
    public E expectErr(String msg) {
        if (isOk()) {
            throw new ResultException(msg);
        }
        return errVal;
    }

    /**
     * Unwraps a result, yielding the content of an {@link Ok}, if any.
     * Otherwise returns the given fallback value.
     * @param fallback The fallback value
     */
    public V unwrapOr(V fallback) {
        return mapOr(fallback, Function.identity());
    }

    /**
     * Unwraps a result, yielding the content of an {@link Ok}, if any.
     * Otherwise evaluates the given fallback supplier.
     * @param fallback The fallback supplier
     */
    public V unwrapOrGet(Supplier<V> fallback) {
        return mapOrGet(fallback, Function.identity());
    }

    /**
     * Unwraps a result, yielding the content of an {@link Ok}, if any.
     * Otherwise, applies the given function to the error value.
     * @param fn Function that maps an {@link Err} value to {@link Ok}'s type.
     */
    public V unwrapOrElse(Function<E, V> fn) {
        if (isOk()) {
            return okVal;
        }
        return fn.apply(errVal);
    }
}
