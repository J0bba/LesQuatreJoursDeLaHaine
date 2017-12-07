package domains;

import java.util.ArrayList;

public class Term {

    public final String token;
    final ArrayList<Integer> positions;
    double frequency;

    public Term(final String token)
    {
        positions = new ArrayList<>();
        frequency = 0.0;
        this.token = token;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void addPosition(final int pos)
    {
        positions.add(pos);
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(final double frequency) {
        this.frequency = frequency;
    }
}
