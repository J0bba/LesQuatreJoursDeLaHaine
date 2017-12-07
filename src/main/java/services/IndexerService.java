package services;

import Interfaces.IIndexer;
import domains.Document;
import domains.Term;
import org.jsoup.Jsoup;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IndexerService implements IIndexer {

    private final String regex = "[^a-zA-ZáàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ']+";
    private final ArrayList<String> stopWords =
            new ArrayList<>(
                    Arrays.asList("a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the")
            );
    private final ArrayList<String> endWords =
            new ArrayList<>(
                    Arrays.asList("ing", "ed", "ly")
            );

    @Override
    public void request(URLRepoService repo) {

    }

    @Override
    public domains.Document index(String url) {

        org.jsoup.nodes.Document jsoupDoc = null;
        try {
            jsoupDoc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsoupDoc != null) {
            org.jsoup.nodes.Document cleanDoc = new Cleaner(Whitelist.none()).clean(jsoupDoc);
            String fullText = cleanDoc.body().text();
            ArrayList<String> textWithoutPunctuation =
                    new ArrayList<>(
                            Arrays.stream(fullText.split(regex))
                                    .map(String::toLowerCase).collect(Collectors.toList()));

            textWithoutPunctuation.removeAll(stopWords);
            for (int i = 0; i < textWithoutPunctuation.size(); i++) {
                String current = textWithoutPunctuation.get(i);
                for (String endWord : endWords) {
                    if (current.endsWith(endWord))
                        textWithoutPunctuation.set(i, current.replaceAll(endWord + "$", ""));
                }
            }

            Document res = new Document(url);
            for (int i = 0; i < textWithoutPunctuation.size(); i++) {
                String token = textWithoutPunctuation.get(i);

                boolean synonym = false; // TODO
                if (synonym) {
                    Term original = new Term("original"); // A remplacer par la recherche du term original
                    original.addPosition(i);
                } else {
                    Term newTerm = new Term(token);
                    newTerm.addPosition(i);
                    res.addTerm(newTerm);
                }
            }

            for (Term term : res.getTerms())
                term.setFrequency(term.getPositions().size() / res.getTerms().size());

            return res;
        } else
            return null;
    }

    @Override
    public void publish(int index) {

    }
}
