package Interfaces;

import java.util.ArrayList;

public interface IURLRepo {
    void receive(ArrayList<String> url);
    void store(ArrayList<String> url);
    ArrayList<String> request();
}
