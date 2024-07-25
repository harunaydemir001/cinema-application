package com.harun.notiticitionservice.email.mapper;

import org.mapstruct.factory.Mappers;

public class MapperGeneratorSingleton {

    private MapperGeneratorSingleton() {}
    public static final MapperGenerator INSTANCE = Mappers.getMapper(MapperGenerator.class);
}
