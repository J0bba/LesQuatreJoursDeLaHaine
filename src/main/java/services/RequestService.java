package services;

import Interfaces.IRequestService;
import domains.Document;
import domains.RetroIndex;
import domains.Term;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestService implements IRequestService {

    @Override
    public ArrayList<String> search(String url) {
        /*
            Ordre des appels
            Call Crawler -> res
            Call Indexer qui return un document pour chaque url de res et qu'on stocke dans RetroIndex
         */

        RetroIndex retroIndex = new RetroIndex();

        return null;
    }
}
