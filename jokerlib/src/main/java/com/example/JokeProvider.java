package com.example;

import java.util.Random;

public class JokeProvider {

    Random rd = new Random();

    private String[] jokeV = {"What did the ocean say to the beach? Nothing, it just waved."
            , "A neutron walks into a bar and asks, how much for a beer? The bartender says, for you? no charge."
            , "Why did the chicken cross the mobius strip? To get to the same side."};

    public String getRandomJoke(){
        return jokeV[rd.nextInt(jokeV.length)];
    }
}
