package com.flab.jangteoapi.mypage.repository;

import com.flab.jangteoapi.mypage.domain.Deposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class DepositRepository {

    private final Map<UUID, Deposit> depositDB = new ConcurrentHashMap<>();

    public Deposit save(Deposit deposit) {

        depositDB.put(deposit.getId(), deposit);

        return depositDB.get(deposit.getId());
    }

    public Optional<Deposit> findById(UUID depositId) {

        return Optional.ofNullable(depositDB.get(depositId));
    }

    public Optional<Deposit> findByUserId(UUID userId) {

        return depositDB.values().stream()
                .filter(deposit -> deposit.getUserId().equals(userId))
                .findFirst();
    }

    public Boolean existsById(UUID depositId) {

        return depositDB.containsKey(depositId);
    }

    public void deleteById(UUID depositId) {

        depositDB.remove(depositId);
    }
}
