package com.mx.design.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mengxin1 on 2019/2/12.
 */
public class FilterChain implements Filter {
    private List<Filter> chain= new ArrayList<>();

    public FilterChain addFilter(Filter filter){
        chain.add(filter);
        return this;
    }

    @Override
    public Object doFilter(Object o, Filter filter, int index) {
        if (index == chain.size()) {
            return null;
        }
        Filter currenFilter = chain.get(index++);
        return currenFilter.doFilter(o,this,index);
    }
}
