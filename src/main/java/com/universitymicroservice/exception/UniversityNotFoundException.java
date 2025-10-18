package com.universitymicroservice.exception;

import static com.universitymicroservice.utils.MessageConstants.UNIVERSITY_NOT_FOUND;

public class UniversityNotFoundException extends RuntimeException{

    public UniversityNotFoundException() {
        super(UNIVERSITY_NOT_FOUND);
    }
}