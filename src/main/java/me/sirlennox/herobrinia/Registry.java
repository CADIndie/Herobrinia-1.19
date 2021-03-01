package me.sirlennox.herobrinia;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Registry <T> {

    public final List<T> REGISTERED;

    public Registry() {
        this.REGISTERED = Collections.synchronizedList(new LinkedList<T>());
    }

    public abstract void init();

    public void register(T o) {
        this.REGISTERED.add(o);
    }

    public void unregister(T o) {
        this.REGISTERED.remove(o);
    }

    public T getByClass(Class<? extends T> c) {
        return this.REGISTERED.stream().filter(m -> m.getClass() == c).findFirst().orElse(null);
    }

}
