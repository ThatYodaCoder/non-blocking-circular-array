package com.datatype.ip;

import java.math.BigInteger;
import java.util.Objects;

public class IpDataType extends Number implements Comparable<IpDataType> {

  private final int ipv4;

  private BigInteger ipv6;


  public IpDataType(Integer ipv4) {
    this.ipv4 = ipv4;
  }

  public IpDataType(BigInteger ipv6) {
    this.ipv6 = ipv6;
    this.ipv4 = 0;
  }

  @Override
  public int intValue() {
    if (ipv6 == null) {
      return ipv4;
    } else {
      return ipv6.intValue();
    }
  }

  @Override
  public long longValue() {
    if (ipv6 == null) {
      return ipv4;
    } else {
      return ipv6.longValue();
    }
  }

  @Override
  public float floatValue() {
    if (ipv6 == null) {
      return ipv4;
    } else {
      return ipv6.floatValue();
    }
  }

  @Override
  public double doubleValue() {
    if (ipv6 == null) {
      return ipv4;
    } else {
      return ipv6.doubleValue();
    }
  }

  public int getIpv4() {
    return ipv4;
  }

  public BigInteger getIpv6() {
    return ipv6;
  }

  @Override
  public int compareTo(IpDataType anotherVal) {

    if ((this.ipv6 == null && anotherVal.ipv6 != null) && (ipv6 != null && anotherVal.ipv6 == null)) {
      throw new UnsupportedOperationException("Can't compare Integer with BigInt");
    }

    if (ipv6 == null) {
      return ipv6.compareTo(anotherVal.ipv6);
    } else {
      return (this.ipv4 < anotherVal.ipv4) ? -1 : ((this.ipv4 == anotherVal.ipv4) ? 0 : 1);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpDataType that = (IpDataType) o;
    return ipv4 == that.ipv4 && Objects.equals(ipv6, that.ipv6);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipv4, ipv6);
  }
}
