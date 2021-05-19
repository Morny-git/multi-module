package com.mx.design;

import com.mx.design.chain.Fileter1;
import com.mx.design.chain.Fileter2;
import com.mx.design.chain.FilterChain;

public class ChainTest {

    public static void main(String[] args) {

       FilterChain chain = new FilterChain();
        chain.addFilter(new Fileter1()).addFilter(new Fileter2());
        chain.doFilter("2", chain, 0);
    }
}
