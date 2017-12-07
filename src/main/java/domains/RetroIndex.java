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
}
