package com.example.timemanagementapp.utils;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    private ModelMapperUtil() {
    }

    public static <C, D> D map(C entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <C, D> C map(C entity, D outEntity) {
        modelMapper.map(entity, outEntity);
        return entity;
    }

    public static <C, D> D mapSkipNullable(C entity, Class<D> outClass) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        D d = modelMapper.map(entity, outClass);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        return d;
    }

    public static <C, D> C mapSkipNullable(C entity, D outClass) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(entity, outClass);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        return entity;
    }

    public static <C, D> List<D> mapAll(final Collection<C> entities, Class<D> outClass) {
        return entities.stream().map(entity -> map(entity, outClass)).collect(Collectors.toList());
    }

}
