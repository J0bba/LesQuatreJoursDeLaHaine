package domains;

import java.util.ArrayList;
import java.util.HashMap;

public class RetroIndex {
    HashMap<String, ArrayList<Document>> map;

    public RetroIndex() {
        map = new HashMap<>();
    }

    public void addDocumentToToken(String token, Document doc) {
        if (map.containsKey(token)) {
            map.get(token).add(doc);
        } else {
            ArrayList arr = new ArrayList();
            arr.add(doc);
            map.put(token, arr);
        }
    }

    private double computeIdf(ArrayList<Document> documents, String token)
    {
        return Math.log(documents.size() / (1 + map.get(token).size()));
    }

    public double computeTfIdf(String token, Document doc, ArrayList<Document> documents)
    {
        return doc.getTerm(token).frequency * computeIdf(documents, token);
    }
}
