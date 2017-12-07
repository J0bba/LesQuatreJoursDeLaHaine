package domains;

import java.util.ArrayList;

public class Term {
    String token;
    ArrayList<Integer> positions;
    float frequency;

    public Term(String token)
    {
        positions = new ArrayList<>();
        this.token = token;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void addPosition(int pos)
    {
        positions.add(pos);
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public String getToken() {
        return token;
    }
}
