package com.flab.jangteoapi.user.repository;

import com.flab.jangteoapi.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {


    private final Map<UUID, User> userDB = new ConcurrentHashMap<>();

    public User save(User user) {

        userDB.put(user.getId(), user);

        return userDB.get(user.getId());
    }

    public Optional<User> findById(UUID userId) {

        return Optional.ofNullable(userDB.get(userId));
    }

    /**
     * 임시 | stream으로 전체 리스트 조회
     *
     * @param username
     * @return
     */
    public Optional<User> findByUsername(String username) {

        return userDB.values().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }


    public Boolean existsById(UUID userId) {

        return userDB.containsKey(userId);
    }

    public void deleteById(UUID userId) {

        userDB.remove(userId);
    }
}
