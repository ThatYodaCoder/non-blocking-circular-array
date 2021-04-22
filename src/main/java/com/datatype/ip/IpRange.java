package com.datatype.ip;

import java.io.Serializable;
import java.util.Objects;

public class IpRange implements Serializable {

  private final IpDataType ipStart;
  private final IpDataType ipEnd;

  public IpRange(IpDataType ipStart, IpDataType ipEnd) {
    this.ipStart = ipStart;
    this.ipEnd = ipEnd;
  }

  public IpDataType getIpStart() {
    return ipStart;
  }

  public IpDataType getIpEnd() {
    return ipEnd;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpRange ipRange = (IpRange) o;
    return ipStart.equals(ipRange.ipStart) && ipEnd.equals(ipRange.ipEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipStart, ipEnd);
  }
}
