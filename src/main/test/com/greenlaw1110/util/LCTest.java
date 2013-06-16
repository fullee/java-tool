package com.greenlaw1110.util;

import com.greenlaw110.TestBase;
import com.greenlaw110.util.*;
import org.junit.Test;

import java.util.List;

import static com.greenlaw110.util._.f.*;
import static com.greenlaw110.util.S.f.*;


/**
 * Test {@link com.greenlaw110.util.ListComprehension}
 */
public class LCTest extends TestBase {
    
    @Test
    public void testMap() {
        int[] a1 = {1, 5, 8};
        int[] a2 = {1 * 2, 5 * 2, 8 * 2};
        List<Integer> l2 = C.listp(a2);
        List<Integer> l = C.lc(a1).map(Integer.class, dbl()).asList();
        eq(l, l2); 
    }
    
    @Test
    public void testChainedMaps() {
        int[] a1 = {0, 1, 20};
        String s1 = "1,2,3";
        String s = S.join(",", C.lc(a1).map(multiply(10), toStr(), size()).asList());
        eq(s1, s);
    }
    
    @Test
    public void testAll() {
        int[] a1 = {0, 1, 2};
        yes(C.lc(a1).all(gt(-1)));
        no(C.lc(a1).all(gt(0)));
    }
    
    @Test
    public void testAny() {
        int[] a1 = {0, 1, 2};
        yes(C.lc(a1).any(gt(0)));
        yes(C.lc(a1).any(gt(0)));
        no(C.lc(a1).any(gt(0)));
    }
    
    @Test
    public void testDigest() {
        int[] a1 = {0, 1, 2};
        
        int[] a2 = {1, 2};
        List<Integer> l2 = C.listp(a2);

        eq(l2, C.lc(a1).digest(gt(0)).asList());
    }
    
    @Test
    public void testReduce() {
        int[] a1 = {0, 1, 2};
        eq(3, C.lc(a1).reduce(0, _.f.sum(Integer.class)));
    }
    
    private static int sum(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }
    
    @Test
    public void testSpeed() {
        final Range<Integer> r = Range.valueOf(0, 1000);
        final List l = C.list(r);
        final ListComprehension<Integer> lc = C.lc(r);
        int sum = lc.reduce(0, _.f.sum(Integer.class));
        eq(sum, sum(l));

        F.Aggregator<Integer> agg = _.f.aggregate(0);
        lc.each(agg);
        eq(sum, agg.get());

        long ts = System.currentTimeMillis();
        int times = 10000;
        _.times(new F.F0<Integer>() {
            @Override
            public Integer run() {
                return sum(l);  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, times);
        long t0 = System.currentTimeMillis() - ts;
        ts = System.currentTimeMillis();
        _.times(new F.F0<Integer>(){
            @Override
            public Integer run() {
                return lc.reduce(0, _.f.sum(Integer.class));
            }
        }, times);
        long t1 = System.currentTimeMillis() - ts;
        
        ts = System.currentTimeMillis();
        _.times(new F.F0<Integer>(){
            @Override
            public Integer run() {
                F.Aggregator<Integer> agg = aggregate(0);
                lc.each(agg);
                return agg.get();
            };
        }, times);
        long t2 = System.currentTimeMillis() - ts;
        
        println("t0: %s; t1: %s; t2: %s", t0, t1, t2);
    }
    
    @Test
    public void testFirst() {
        int[] a1 = {0, 1, 2};
        eq(1, C.lc(a1).first(_.f.gt(0)));
    }

    public static void main(String[] args) {
        run(LCTest.class);
    }
}