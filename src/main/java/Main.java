import domains.RetroIndex;
import services.CrawlerService;
import services.IndexerService;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

    //CrawlerService crawler = new CrawlerService();
    //System.out.print(crawler.crawl("https://fr.wikipedia.org/wiki/Rinxent"));
    IndexerService indexerService = new IndexerService();
    RetroIndex retroIndex = new RetroIndex();
    indexerService.index("https://en.wikipedia.org/wiki/Rinxent", retroIndex);
  }
}