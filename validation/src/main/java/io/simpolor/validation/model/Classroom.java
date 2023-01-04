package io.simpolor.validation.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Classroom {

    FLOWER,
    SUN;

    public static Classroom get(String name){

        try {
            return Classroom.valueOf(name);
        } catch (Exception e){
            log.warn("Classroom not get: ", name);
        }

        return null;
    }
}
