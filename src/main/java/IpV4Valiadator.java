import java.util.ArrayList;
import java.util.List;

public class IpV4Valiadator {

  public static void main(String[] args) throws Exception {

    validIPAddresses("1921680");

  }

  public static ArrayList<String> validIPAddresses(String string) {
    // Write your code here.
    String ip = string;

    String o1 = null;
    String o2 = null;
    String o3 = null;
    String o4 = null;

    List<String> ipList = new ArrayList<>();
    int oct1, oct2, oct3, oct4;
    oct1 = oct2 = oct3 = oct4 = 0;
    for (int i = 0; i < ip.length(); i++) {

      oct1 = Integer.parseInt("" + ip.charAt(i));
      o1 = o1 == null ? "" + oct1 : o1 + oct1;
      oct1 = Integer.parseInt(o1);
      System.out.println(" OCT1=" + oct1 + " | OCT2=" + oct2 + " | OCT3=" + oct3 + " | OCT4=" + oct4);
      if (!isValid(oct1)) {
        break;
      }

      for (int j = i + 1; j < ip.length(); j++) {

        oct2 = Integer.parseInt("" + ip.charAt(j));
        o2 = o2 == null ? "" + oct2 : o2 + oct2;
        oct2 = Integer.parseInt(o2);
        System.out.println(" OCT1=" + oct1 + " | OCT2=" + oct2 + " | OCT3=" + oct3 + " | OCT4=" + oct4);

        if (!isValid(oct2)) {
          o2 = null;
          o3 = null;
          o4 = null;
          break;
        }

        for (int k = j + 1; k < ip.length(); k++) {

          oct3 = Integer.parseInt("" + ip.charAt(k));
          o3 = o3 == null ? "" + oct3 : o3 + oct3;
          oct3 = Integer.parseInt(o3);
          System.out.println(" OCT1=" + oct1 + " | OCT2=" + oct2 + " | OCT3=" + oct3 + " | OCT4=" + oct4);

          if (!isValid(oct3)) {
            o3 = null;
            o4 = null;
            break;
          }

          for (int l = k + 1; l < ip.length(); l++) {

            oct4 = Integer.parseInt(ip.substring(l));
            o4 = o4 == null ? "" + oct4 : o4 + oct4;

            oct4 = Integer.parseInt(o4);

            System.out.println(" OCT1=" + oct1 + " | OCT2=" + oct2 + " | OCT3=" + oct3 + " | OCT4=" + oct4);

            if (!isValid(oct4)) {
              System.out.println(
                  "************Breaking OCT1=" + oct1 + " | OCT2=" + oct2 + " | OCT3=" + oct3 + " | OCT4=" + oct4);
              o4 = null;
              break;
            }

            String ipStr = o1 + "." + o2 + "." + o3 + "." + o4;
            System.out.println("ipStr=" + ipStr);
            ipList.add(ipStr);
            break;
          }
        }
      }
    }

    return (ArrayList) ipList;
  }

  private static boolean isValid(int oct) {
    return oct >= 0 && oct <= 255;
  }
}
