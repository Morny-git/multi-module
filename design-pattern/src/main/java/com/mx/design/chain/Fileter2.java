package com.mx.design.chain;

public class Fileter2 implements Filter{

    @Override
    public Object doFilter(Object o, Filter filter, int index) {
        if ("2".equals(o.toString())){
            System.out.println("filter 1");
            return o;
        }
        return filter.doFilter(o,filter,index);
    }
}
