package com.harun.actor.mapper;

import org.mapstruct.factory.Mappers;

public class MapperGeneratorSingleton {

    MapperGeneratorSingleton() {

    }
    public static final MapperGenerator INSTANCE = Mappers.getMapper(MapperGenerator.class);
}
