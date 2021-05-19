package com.mx.design.singleton;

/**
 * 静态内部类不会随着外部类的初始化而初始化，他是要单独去加载和初始化的，当第一次执行getInstance方法时，Inner类会被初始化。
 *
 * 　　静态对象SINGLETION的初始化在Inner类初始化阶段进行，类初始化阶段即虚拟机执行类构造器<clinit>()方法的过程。
 *
 * 　　虚拟机会保证一个类的<clinit>()方法在多线程环境下被正确的加锁和同步，如果多个线程同时初始化一个类，只会有一个线程执行这个类的<clinit>()方法，其它线程都会阻塞等待
 */
public class InnerStaticVarSingleton {
    private static class SingletonHolder {
        private static final InnerStaticVarSingleton INSTANCE = new InnerStaticVarSingleton();
    }
    private InnerStaticVarSingleton (){}
    public static final InnerStaticVarSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
