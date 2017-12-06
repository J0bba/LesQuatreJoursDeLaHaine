import services.CrawlerService;

public class Main {

  public static void main(String[] args) {

    CrawlerService crawler = new CrawlerService();
    System.out.print(crawler.crawl("http://www.mit.edu/"));
  }
}