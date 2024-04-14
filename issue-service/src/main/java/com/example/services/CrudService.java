package com.example.services;

import java.util.List;
import java.util.UUID;

public interface CrudService<T, E> {
    /**
     * Получить список всех объектов.
     *
     * @return список найденных объектов.
     */
    List<T> getAll();

    /**
     * Получить объект по его uuid.
     *
     * @param uuid uuid объекта.
     * @return найденный объект.
     */
    T getByUuid(UUID uuid);

    /**
     * Создание объекта.
     *
     * @param request данные для создания.
     * @return созданный объект.
     */
    T create(E request);

    /**
     * Обновление данных.
     *
     * @param request параметры для обновления.
     * @return обновленный объект.
     */
    T update(Long id, E request);

    /**
     * Удаление по uuid.
     *
     * @param id id объекта.
     * @return список объектов.
     */
    void deleteById(Long id);
}
