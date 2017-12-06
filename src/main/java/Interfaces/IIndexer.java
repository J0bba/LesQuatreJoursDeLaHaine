package Interfaces;

import domains.Document;
import services.URLRepoService;

public interface IIndexer {
    void request(URLRepoService repo);
    Document index(String url);
    void publish(int index);
}
