package com.datatype.ip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UInt128Test {

  @Test
  public void test() {

    UInt128 num = new UInt128("340282366920938463463374607431768211456");

    System.out.printf("num=" + num.toString());

  }

}