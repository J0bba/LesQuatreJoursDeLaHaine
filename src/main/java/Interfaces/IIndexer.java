package Interfaces;

import domain.Document;
import services.URLRepoService;

public interface IIndexer {
    void Request(URLRepoService repo);
    Document index();
    void publish(int index);
}
