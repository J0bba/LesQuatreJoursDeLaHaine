package domains;

import java.util.ArrayList;

public class Document {
    String url;

    ArrayList<Term> terms;

    public Document(String url)
    {
        terms = new ArrayList<>();
        this.url = url;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }
}
