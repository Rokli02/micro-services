package hu.me.microservice.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.me.microservice.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUserId(Long userId, Pageable page);
    List<Order> findAllByUserIdAndGroup(Long userId, String group);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Order SET status = :toStatus WHERE status = :fromStatus AND group = :group AND userId = :userId")
    int updateStatusByUserIdAndGroup(
        @Param("userId") long userId,
        @Param("group") String group,
        @Param("fromStatus") int fromStatus,
        @Param("toStatus") int toStatus
    );

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Order SET status = :toStatus WHERE status = :fromStatus")
    int updateStatus(
        @Param("fromStatus") int fromStatus,
        @Param("toStatus") int toStatus
    );
}
