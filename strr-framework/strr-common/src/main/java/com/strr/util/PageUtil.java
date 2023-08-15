package com.strr.util;

import com.strr.base.model.Pageable;

public class PageUtil {
    private static final ThreadLocal<Pageable> PAGE = new ThreadLocal<>();

    public static void start(Pageable pageable) {
        PAGE.set(pageable);
    }

    public static void end() {
        PAGE.remove();
    }

    public static Pageable get() {
        return PAGE.get();
    }
}
