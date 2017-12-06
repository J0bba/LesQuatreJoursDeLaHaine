import services.CrawlerService;
import services.IndexerService;

public class Main {

  public static void main(String[] args) {

    //CrawlerService crawler = new CrawlerService();
    //System.out.print(crawler.crawl("https://fr.wikipedia.org/wiki/Rinxent"));
    IndexerService indexerService = new IndexerService();
    indexerService.index("https://en.wikipedia.org/wiki/Rinxent");
  }
}