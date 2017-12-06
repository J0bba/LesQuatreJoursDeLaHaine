package services;

import java.util.ArrayList;

public interface ICrawler {
    void crawl(String url);
    ArrayList<String> extractHicks();
    void Publish();
    void RequestNextUrl();
}
