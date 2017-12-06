package services;

import java.util.ArrayList;
import java.util.List;

public interface ICrawler {
    List<String> crawl(String url);
    ArrayList<String> extractHicks();
    void Publish();
    void RequestNextUrl();
}
