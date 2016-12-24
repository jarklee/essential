/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.essential.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;

public class Optional<T> {

    private static final Optional<?> EMPTY = new Optional<>(null);

    private final T obj;

    private Optional(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public T get(T defaultValue) {
        return orElse(defaultValue);
    }

    public boolean isPresent() {
        return obj != null;
    }

    public T orNull() {
        if (isPresent()) {
            return obj;
        }
        return null;
    }

    public T orElse(T otherValue) {
        if (isPresent()) {
            return obj;
        }
        return otherValue;
    }

    public T orElse(Supplier<T> elseSupplier) {
        if (elseSupplier == null) {
            throw new IllegalArgumentException("null may not be passed as an argument; use orNull() instead.");
        }
        if (isPresent()) {
            return obj;
        }
        return elseSupplier.get();
    }

    public T orThrow() throws Throwable {
        if (isPresent()) {
            return obj;
        }
        throw new Throwable("Object is not present");
    }

    public <E extends Throwable> T orThrow(E throwable) throws E {
        if (throwable == null) {
            throw new IllegalArgumentException("null may not be passed as an argument; use orNull() instead.");
        }
        if (isPresent()) {
            return obj;
        }
        throw throwable;
    }

    public <E extends Throwable> T orThrow(Supplier<E> throwSupplier) throws E {
        if (throwSupplier == null) {
            throw new IllegalArgumentException("null may not be passed as an argument; use orNull() instead.");
        }
        if (isPresent()) {
            return obj;
        }
        throw throwSupplier.get();
    }

    public Optional<T> filter(@NonNull Predicate<T> predicate) {
        if (!isPresent()) {
            return this;
        }
        if (predicate.test(obj)) return this;
        return empty();
    }

    public Optional<T> filterNot(@NonNull Predicate<T> predicate) {
        if (!isPresent()) {
            return this;
        }
        if (!predicate.test(obj)) return this;
        return empty();
    }

    public Optional<T> where(Predicate<T> predicate) {
        if (!isPresent()) {
            return this;
        }
        if (predicate.test(obj)) return this;
        return empty();
    }

    public Optional<T> whereNot(Predicate<T> predicate) {
        if (!isPresent()) {
            return this;
        }
        if (!predicate.test(obj)) return this;
        return empty();
    }

    public <U> Optional<U> map(@NonNull Function<T, U> mapper) {
        if (!isPresent()) {
            return empty();
        }
        return Optional.ofNullable(mapper.apply(obj));
    }

    public <U> Optional<U> $(@NonNull Function<T, U> mapper) {
        if (!isPresent()) {
            return empty();
        }
        return Optional.ofNullable(mapper.apply(obj));
    }

    public void invoke(Invokable<T> invokable) {
        if (isPresent()) {
            invokable.invoke(obj);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> Optional<T> of(T obj) {
        return new Optional<>(obj);
    }

    public static <T> Optional<T> ofNullable(T obj) {
        if (obj == null) return empty();
        return of(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> void invoke(@Nullable T obj, @NonNull Invokable<T> invokable) {
        if (obj != null) {
            invokable.invoke(obj);
        }
    }

    public static <T, U> Optional<U> get(@Nullable T obj,
                                         @NonNull Function<T, U> mapper) {
        U result = null;
        if (obj != null) {
            result = mapper.apply(obj);
        }
        return Optional.ofNullable(result);
    }

    public static <T extends Closeable> void resources(@Nullable T resource,
                                                       @NonNull Invokable<T> invokable) {
        if (resource == null) {
            return;
        }
        try {
            invokable.invoke(resource);
        } finally {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T extends Closeable, U> Optional<U> resources(@Nullable T resource,
                                                                 @NonNull Function<T, U> mapper) {
        if (resource == null) {
            return empty();
        }
        try {
            return get(resource, mapper);
        } finally {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface Supplier<T> {
        T get();
    }

    public interface Function<T, U> {
        U apply(T obj);
    }

    public interface Predicate<T> {
        boolean test(T obj);
    }

    public interface Invokable<T> {
        void invoke(T obj);
    }
}
