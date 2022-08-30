package com.doghandeveloper.doggu.Dog.sevice;

import com.doghandeveloper.doggu.Dog.dto.response.DogAuthenticationResponse;

import java.io.IOException;

public interface DogService {
    DogAuthenticationResponse sendDogInfo(String ownerName, String registerNumber) throws IOException;
}
