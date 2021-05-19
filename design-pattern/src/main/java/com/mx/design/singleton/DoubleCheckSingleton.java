package com.mx.design.singleton;

/**为了确保创建对象时，不会发生指令重排。声明称volatile
 * 创建对象分解为：
 * memory = allocate();　　// 1.分配对象的内存空间
 * ctorInstance(memory);　　// 2.初始化对象
 * sInstance = memory;　　// 3.设置sInstance指向刚分配的内存地址
 */

public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton singleton;
    private DoubleCheckSingleton (){}
    public static DoubleCheckSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
