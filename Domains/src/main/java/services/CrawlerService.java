package services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import interfaces.ICrawler;

import java.util.ArrayList;

public class CrawlerService implements ICrawler {
    @Override
    public ArrayList<String> crawl(String url) {

        ArrayList<String> res = new ArrayList<>();
        res.add(url);

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements questions = doc.select("a[href]");
        if (questions != null) {
          for (Element link : questions) {
            String currentUrl = link.attr("href");
            if (!currentUrl.startsWith("#") && !res.contains(currentUrl))
              res.add(link.attr("abs:href"));
          }
        }

        return res;
    }
}
