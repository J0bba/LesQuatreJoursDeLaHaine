package domain;

import java.util.ArrayList;

public class Document {
    String url;

    ArrayList<Term> terms;

    public Document(String url)
    {
        this.url = url;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }
}
