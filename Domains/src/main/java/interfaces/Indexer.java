package interfaces;

import domains.Document;
import domains.RetroIndex;

public interface Indexer {
    Document index(String url, RetroIndex retroIndex);
}
