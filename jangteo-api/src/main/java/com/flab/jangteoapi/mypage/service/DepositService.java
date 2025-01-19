package com.flab.jangteoapi.mypage.service;

import com.flab.jangteoapi.mypage.domain.Deposit;
import com.flab.jangteoapi.mypage.exception.DepositNotFoundException;
import com.flab.jangteoapi.mypage.exception.InsufficientDepositException;
import com.flab.jangteoapi.mypage.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final DepositRepository depositRepository;

    public Deposit getDepositAmount(UUID userId, int amount) {

        Optional<Deposit> existingDeposit = depositRepository.findByUserId(userId);

        if (existingDeposit.isPresent()) {

            Deposit updatedDeposit = existingDeposit.get()
                    .toBuilder()
                    .amount(existingDeposit.get().getAmount() + amount)
                    .build();

            return depositRepository.save(updatedDeposit);
        }

        Deposit newDeposit = Deposit.builder()
                .userId(userId)
                .amount(amount)
                .build();

        return depositRepository.save(newDeposit);
    }

    public int getUserDeposit(UUID userId) {

        return depositRepository.findByUserId(userId)
                .map(Deposit::getAmount)
                .orElse(0);
    }

    public boolean requestRefund(UUID userId, int amount) {

        Optional<Deposit> existingDeposit = depositRepository.findByUserId(userId);

        if (existingDeposit.isPresent()) {

            Deposit deposit = existingDeposit.get();

            if (deposit.getAmount() >= amount) {

                Deposit updatedDeposit = deposit.toBuilder()
                        .amount(deposit.getAmount() - amount)
                        .build();

                depositRepository.save(updatedDeposit);

                return true;

            } else {
                throw new InsufficientDepositException("보증금이 충분하지 않습니다");
            }
        }

        throw new DepositNotFoundException("사용자의 보증금이 존재하지 않습니다");
    }
}
