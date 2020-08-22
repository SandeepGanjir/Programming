/*
*  This pattern ensures that we have only one instance of a class.
*
*  |—————————————————————————————————————|
*  |            ClassName                |
*  |—————————————————————————————————————|
*  | - static instance : ClassName       |
*  | - data                              |
*  |—————————————————————————————————————|
*  | - ClassName()                       |
*  | + static getInstance() : ClassName  |
*  | + setData()                         |
*  | + getData()                         |
*  |—————————————————————————————————————|
*/

import java.util.*;

class Singleton {
    private final Map<String, Object> data;

    private Singleton() {
        data = new HashMap<>();
    }

    private static class SingletonHelper {
        private static final Singleton uniqueInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.uniqueInstance;        
    }

    public Map<String, Object> getData() {
        return data;
    }

    // For testing
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.print("Do we have only one instance ? " + (s1==s2));
    }
}