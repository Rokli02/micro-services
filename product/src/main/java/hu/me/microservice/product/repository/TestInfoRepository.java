package hu.me.microservice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.me.microservice.product.entity.TestInfo;

@Repository
public interface TestInfoRepository extends JpaRepository<TestInfo, Long> {}
