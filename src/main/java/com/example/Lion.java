package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    private final Feline feline;

    public Lion(Feline feline) {
        this.feline = feline;
    }

    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.eatMeat();
    }
}