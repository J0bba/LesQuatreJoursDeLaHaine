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

    public Term getTerm(String token)
    {
        for (Term t : terms)
        {
            if (t.getToken().equals(token))
                return t;
        }

        return null;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }
}
