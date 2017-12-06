package services;

import java.util.ArrayList;
import java.util.List;

public interface ICrawler {
    ArrayList<String> crawl(String url);
    ArrayList<String> extractHicks();
    void publish();
    void requestNextUrl();
}
