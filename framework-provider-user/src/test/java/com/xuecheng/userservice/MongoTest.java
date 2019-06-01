package com.xuecheng.userservice;

import com.xuecheng.userservice.repository.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserInfoRepository userInfoRepository;

//    @Autowired
//    AddressRepository addressRepository;
//
//    @Autowired
//    private EhsEvaluatePeriodRepository evaluatePeriodRepository;
//
//    @Autowired
//    private EhsEquipmentTypeRepository equipmentTypeRepository;
//    @Autowired
//    private EhsActivityTypeRepository activityTypeRepository;
//
//    @Autowired
//    private EhsActivityFrequencyTypeRepository activityFrequencyTypeRepository;
//
//    @Autowired
//    private EhsScraftnodeTypeRepository scraftnodeTypeRepository;
//
//    @Autowired
//    private EhsRiskControlParamRepository riskControlParamRepository;
//
//    @Autowired
//    private EhsEvaluateMethodLSLRepository evaluateMethodLSLRepository;
}
