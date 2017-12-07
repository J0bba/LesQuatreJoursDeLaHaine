package services;

import Interfaces.IRequestService;
import domains.Document;
import domains.RetroIndex;
import domains.Term;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestService implements IRequestService {

    @Override
    public ArrayList<String> search(String query) {
        query = query.toLowerCase();

        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://pastebin.com/raw/bSZWcdZJ");
        urls.add("https://pastebin.com/raw/Vsjq6inb");
        urls.add("https://pastebin.com/raw/AFB0FYU0");
        urls.add("https://pastebin.com/raw/0RRX3Q47");
        urls.add("https://pastebin.com/raw/tL1BSsYF");

        CrawlerService crawlerService = new CrawlerService();

        ArrayList<String> toCrawl = new ArrayList<>();
        for (String url: urls)
        {
            ArrayList<String> crawled = crawlerService.crawl(url);
            for (String u : crawled) {
                toCrawl.add(u);
            }
        }

        IndexerService indexerService = new IndexerService();
        RetroIndex retroIndex = new RetroIndex();
        ArrayList<Document> documents = new ArrayList<>();

        for (String url : toCrawl)
            documents.add(indexerService.index(url, retroIndex));

        HashMap<String, Double> result = new HashMap<>();

        for (Document doc : documents)
        {
            Term t = doc.getTerm(query);
            if (t != null)
            {
                double tf = t.getFrequency();
                double idf = Math.log((double)documents.size() / (double)(1 + retroIndex.getMap().get(query).size()));
                result.put(doc.getUrl(), tf * idf);
            }
        }

        ArrayList<String> links = new ArrayList<>();
        for (HashMap.Entry<String, Double> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            links.add(entry.getKey());
        }

        return links;
    }
}
