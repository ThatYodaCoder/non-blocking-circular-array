import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Base64;

public class TinyUrl {

  public static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
  public static final int BASE = ALPHABET.length();

  public static void main(String[] args) {

    long n = 67000000000l;
    final ByteBuffer allocate = ByteBuffer.allocate(8);
    allocate.putLong(n);
    final byte[] bytes = allocate.array();
    final String s = Base64.getEncoder().encodeToString(bytes);
    System.out.println(s);

    final String base62From10 = getBase62From10(n);
    System.out.println(n+"= "+ base62From10);

    System.out.println(base62From10+"="+getBase10From62(base62From10));

  }

  private static final char[] corpus   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  /*
   * Note if seed is unique then generated base62 number will be unique as well under load balance make sure this value is not same.
   */
  public static final String getBase62From10(final long seed)
  {
    String number = seed + "";
    char[] buf = new char[number.length()];
    int charPos = number.length() - 1;
    BigInteger bigIntegerNumber = new BigInteger(number);
    BigInteger radix = BigInteger.valueOf(62);

    while (bigIntegerNumber.compareTo(radix) >= 0)
    {
      buf[charPos--] = corpus[bigIntegerNumber.mod(radix).intValue()];
      bigIntegerNumber = bigIntegerNumber.divide(radix);
    }
    buf[charPos] = corpus[bigIntegerNumber.intValue()];
    return new String(buf, charPos, (number.length() - charPos));
  }
  /**
   * @param longNumber
   * a positive number in base 62
   * @return the same number, in base 10
   */
  public static final String getBase10From62(final String longNumber)
  {
    String number = longNumber + "";
    BigInteger value = BigInteger.ZERO;
    for (char c : number.toCharArray())
    {
      value = value.multiply(BigInteger.valueOf(62));
      if ('0' <= c && c <= '9')
      {
        value = value.add(BigInteger.valueOf(c - '0'));
      }
      if ('a' <= c && c <= 'z')
      {
        value = value.add(BigInteger.valueOf(c - 'a' + 10));
      }
      if ('A' <= c && c <= 'Z')
      {
        value = value.add(BigInteger.valueOf(c - 'A' + 36));
      }
    }
    return value.toString();
  }

}
