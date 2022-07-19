package com.doghandeveloper.doggu.Dog.sevice;

import java.io.IOException;

public interface DogService {
    void sendDogInfo(String ownerName, String registerNumber) throws IOException;
}
