package com.flab.jangteoapi.auction.repository;

import com.flab.jangteoapi.auction.domain.Auction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuctionRepository {

    private final Map<UUID, Auction> auctionDB = new ConcurrentHashMap<>();

    public Auction save(Auction auction) {

        auctionDB.put(auction.getId(), auction);

        return auction;
    }

    public Optional<Auction> findById(UUID auctionId) {

        return Optional.ofNullable(auctionDB.get(auctionId));
    }

    public void delete(UUID auctionId) {

        auctionDB.remove(auctionId);
    }

    /**
     * 임시 | 모든 경매 목록 조회
     *
     * @return
     */
    public List<Auction> findAll() {

        return new ArrayList<>(auctionDB.values());
    }
}
