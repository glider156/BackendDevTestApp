package com.example.backenddev.service;

public interface EntityService<T> {
    void create(T entity);
    T getReference(Object id);
    T find(Object id);
    T update(T entity);
    void delete(T entity);
    int count();
}
