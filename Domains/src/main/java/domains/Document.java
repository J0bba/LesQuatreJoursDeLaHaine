package domains;

import java.util.ArrayList;

public class Document {
    public String getUrl() {
        return url;
    }

    public final String url;

    public final ArrayList<Term> terms;

    public Document(final String url)
    {
        terms = new ArrayList<>();
        this.url = url;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public Term getTerm(final String token)
    {
        for (Term t : terms)
        {
            if (t.token.equals(token))
                return t;
        }

        return null;
    }

    public void addTerm(final Term term) {
        this.terms.add(term);
    }
}
