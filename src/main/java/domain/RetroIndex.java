package domain;

import java.util.ArrayList;

public class RetroIndex {
    ArrayList<Document> documents;

    public RetroIndex(ArrayList<Document> documents)
    {
        this.documents = documents;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }
}
