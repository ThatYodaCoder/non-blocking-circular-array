import static java.util.stream.Collectors.joining;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Result {


  public static void main(String[] args) throws IOException {

    final List<String> sama = getArticleTitles("sama");

    System.out.println("");

  }

  /*
   * Complete the 'getArticleTitles' function below.
   *
   * The function is expected to return a STRING_LIST.
   * The function accepts STRING author as parameter.
   *
   * URL for cut and paste:
   * https://restmock.techgig.com/news?author=<authorName>&page=<num>
   *
   */
  public static List<String> getArticleTitles(String author) {
    // Implementation here

    final RespBean obj = getJsonResp(author,"");

    int totalPagesToFetch = obj.getTotal_page();
    int totalArticles = obj.getTotal();

    List<String> results = new ArrayList<>();
    for(int i = 1; i <= totalPagesToFetch; i++){
      final RespBean jsonResp = getJsonResp(author, Integer.toString(i));

      for(Content content : jsonResp.getContent()){

        if(content.getTitle() != null && !content.getTitle().isEmpty()){
          results.add(content.getTitle());
        }else if(content.getLink()!=null && !content.getLink().isEmpty()){
          results.add(content.getLink());
        }
      }
    }
    Collections.sort(results);
    return results;
  }

  public static RespBean getJsonResp(String author,String pageNum) {

    String response = null;
    try {

      String urlStr = "https://restmock.techgig.com/news?author="+author;

      if(!pageNum.isEmpty()){
        urlStr = urlStr + "&page="+pageNum;
      }

      URL url = new URL("https://restmock.techgig.com/news?author="+author);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
      }
      InputStreamReader in = new InputStreamReader(conn.getInputStream());
      BufferedReader br = new BufferedReader(in);

      StringBuilder resp = new StringBuilder();
      String output;
      while ((output = br.readLine()) != null) {
        resp.append(output);
      }
      conn.disconnect();

      response = resp.toString();

    } catch (Exception e) {
      System.out.println("Exception in NetClientGet:- " + e);
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    final RespBean respBean = gson.fromJson(response, RespBean.class);

    return respBean;
  }

  public static class RespBean {

    private int page;
    private int total_page;
    private int per_page;
    private int total;
    private List<Content> content;

    public int getPage() {
      return page;
    }

    public int getTotal_page() {
      return total_page;
    }

    public int getPer_page() {
      return per_page;
    }

    public int getTotal() {
      return total;
    }

    public List<Content> getContent() {
      return content;
    }
  }

  public static class Content {

    private String title;
    private String link;

    public String getTitle() {
      return title;
    }

    public String getLink() {
      return link;
    }
  }
}


class CandidateCode {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String author = bufferedReader.readLine();

    List<String> result = Result.getArticleTitles(author);

    System.out.println(result.stream().collect(joining("\n")) + "\n");

    bufferedReader.close();
  }
}