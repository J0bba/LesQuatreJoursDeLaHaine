package services;

import domain.Document;

public interface IIndexer {
    void Request(URLRepo repo);
    Document index();
    void publish(int index);
}
