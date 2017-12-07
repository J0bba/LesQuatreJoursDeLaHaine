package Interfaces;

import domains.Document;
import domains.RetroIndex;
import services.URLRepoService;

public interface IIndexer {
    void request(URLRepoService repo);
    Document index(String url, RetroIndex retroIndex);
    void publish(int index);
}
