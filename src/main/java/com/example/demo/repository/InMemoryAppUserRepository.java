package com.example.demo.repository;

import com.example.demo.entity.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author Hassan Ali
 * @since 23rd December 2024
 */
@Repository
public class InMemoryAppUserRepository implements AppUserRepository {

    private final Map<Integer, AppUser> store = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    @Override
    public <S extends AppUser> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<AppUser> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<AppUser> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public AppUser save(AppUser user) {
        user.setUserId(idCounter.incrementAndGet());
        store.put(user.getUserId(), user);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        store.remove(id);
    }

    @Override
    public void delete(AppUser entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppUser> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends AppUser> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends AppUser> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<AppUser> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public AppUser getOne(Integer integer) {
        return null;
    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public AppUser getById(Integer integer) {
        return null;
    }

    @Override
    public AppUser getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends AppUser> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AppUser> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends AppUser> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends AppUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AppUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AppUser> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends AppUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<AppUser> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<AppUser> findAll(Pageable pageable) {
        return null;
    }
}
