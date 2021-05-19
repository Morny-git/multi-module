package com.mx.design.chain;

import java.util.Map;

public class Fileter1 implements Filter{

    @Override
    public Object doFilter(Object o, Filter filter, int index) {
        if ("1".equals(o.toString())){
            System.out.println("filter 1");
            return o;
        }
        return filter.doFilter(o,filter,index);
    }
}
