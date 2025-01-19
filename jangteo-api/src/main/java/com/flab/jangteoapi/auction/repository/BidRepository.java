package com.flab.jangteoapi.auction.repository;

import com.flab.jangteoapi.auction.domain.Bid;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BidRepository {

    private final Map<UUID, Bid> bidDB = new ConcurrentHashMap<>();

    public Bid save(Bid bid) {

        bidDB.put(bid.getId(), bid);

        return bid;
    }

    public Optional<Bid> findById(UUID bidId) {

        return Optional.ofNullable(bidDB.get(bidId));
    }

    public Optional<Bid> findByAuctionIdAndUserId(UUID auctionId, UUID userId) {

        return bidDB.values().stream()
                .filter(bid -> bid.getAuctionId().equals(auctionId)
                        && bid.getUserId().equals(userId))
                .findAny();
    }

    /**
     * 경매에 요청된 모든 입찰의 목록 조회 | 이후 사용
     *
     * @param auctionId
     * @return
     */
    public Map<UUID, Bid> findAllByAuctionId(UUID auctionId) {

        Map<UUID, Bid> result = new ConcurrentHashMap<>();

        bidDB.forEach((id, bid) -> {

            if (bid.getAuctionId().equals(auctionId)) {
                result.put(id, bid);
            }
        });

        return result;
    }
}
