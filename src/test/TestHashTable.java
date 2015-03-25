//package test;
//
//import java.util.Hashtable;
//
///**
// * Created by tongxiqing on 2015/3/25.
// */
//public class TestHashTable {
//
//    public static void main(String[] args){
//        Hashtable heroHphashtable = new Hashtable();
//        heroHphashtable.put(0,'a');
//        heroHphashtable.put(1,'b');
//        heroHphashtable.put(2,'c');
//        heroHphashtable.put(3,'d');
//
//        while (heroHphashtable.keys().hasMoreElements()){
//            Integer v = (Integer) heroHphashtable.elements().nextElement();
//            v = new Integer((v.intValue() - (5/100)*v.intValue()));
//            heroHphashtable.put(heroHphashtable.keys().nextElement(),v);
//            System.out.println(v);
//        }
//    }
//}
