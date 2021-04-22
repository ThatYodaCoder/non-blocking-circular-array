package com.datatype.ip;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class BigIntTest {

  @Test
  public void test() {

    long start1 = System.currentTimeMillis();
    BigInt num = null;

    for (int ctr = 0; ctr < 10_000_000; ctr++) {
      num = new BigInt("340282366920938463463374607431768211456");
    }

    System.out.println("time=" + (System.currentTimeMillis() - start1));

    long start2 = System.currentTimeMillis();
    for (int ctr = 0; ctr < 10_000_000; ctr++) {
      //num = new BigInt("340282366920938463463374607431768211456");
      num.add(ctr);
    }
    System.out.println("time=" + (System.currentTimeMillis() - start2));

    System.out.println("num=" + num.toString());
    num.add(1);
    System.out.println("num=" + num.toString());

  }

  @Test
  public void test2() {

    long start1 = System.currentTimeMillis();

    BigInt num = null;

    for (int ctr = 0; ctr < 10_000_000; ctr++) {
      num = new BigInt("340282366920938463463374607431768211456");
    }
    System.out.println("time=" + (System.currentTimeMillis() - start1));

    ///////////////////////

    start1 = System.currentTimeMillis();
    BigInteger bigInteger = null;

    for (int ctr = 0; ctr < 10_000_000; ctr++) {
      bigInteger = new BigInteger("340282366920938463463374607431768211456");
    }

    System.out.println("time=" + (System.currentTimeMillis() - start1));

  }
}