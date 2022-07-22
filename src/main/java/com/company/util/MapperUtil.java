package com.company.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MapperUtil {

    private final ModelMapper mapper;

    public MapperUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <T, E> List<T> convertList(List<E> list, Class<T> type) {
        return list.stream().map(e -> mapper.map(e, type)).collect(Collectors.toList());
    }

    public <T, E> List<T> convertList(List<E> list, Function<E, T> converter) {
        return list.stream().map(converter::apply).collect(Collectors.toList());
    }

}

