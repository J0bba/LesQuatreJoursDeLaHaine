package Interfaces;

import domains.Document;
import domains.RetroIndex;

public interface IIndexer {
    Document index(String url, RetroIndex retroIndex);
}
