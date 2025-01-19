package hu.me.microservice.product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.me.microservice.product.models.TestInfo;
import hu.me.microservice.product.repository.TestInfoRepository;

@Service
public class HealthcheckServiceImpl implements HealthcheckService{
    private final TestInfoRepository testInfoRepository;

    public HealthcheckServiceImpl(TestInfoRepository testInfoRepository) {
        this.testInfoRepository = testInfoRepository;
    }

    @Override
    public List<TestInfo> getAll() {
        List<TestInfo> testInfos = new ArrayList<>();
        
        List<hu.me.microservice.product.entity.TestInfo> result = this.testInfoRepository.findAll();

        for (hu.me.microservice.product.entity.TestInfo entity : result) {
            testInfos.add(TestInfo.fromEntity(entity));
        }
        
        return testInfos;
    }
}
