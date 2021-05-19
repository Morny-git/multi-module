package com.mx.design.chain;

import java.util.Map;

public interface Filter {
    Object doFilter(Object o, Filter filter, int index);
}
