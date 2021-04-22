package com.yvolabs.user.service;

import com.yvolabs.user.ValueObject.Department;
import com.yvolabs.user.ValueObject.ResponseTemplateVO;
import com.yvolabs.user.entity.User;
import com.yvolabs.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService.");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment method of UserService.");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        /* Get department for this user - Note Department is in another microservice
         *  Call the Department Microservice using a "RestTemplate" @See UserServiceApplication Class
         */
        String urlBeforeServiceRegistry = "http://localhost:9001/api/departments/" + user.getDepartmentId();
        String url = "http://DEPARTMENT-SERVICE/api/departments/" + user.getDepartmentId();
        Department department = restTemplate.getForObject(url, Department.class);

        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);

        return responseTemplateVO;
    }
}
