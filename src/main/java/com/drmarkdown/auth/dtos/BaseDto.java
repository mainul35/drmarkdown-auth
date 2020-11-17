package com.drmarkdown.auth.dtos;

public abstract class BaseDto<T> {

    public abstract void mapEntityToDto(T t);
}
