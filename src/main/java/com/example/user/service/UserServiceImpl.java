package com.example.user.service;

import com.example.user.dto.UserPhone;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.entity.Phone;
import com.example.user.entity.User;
import com.example.user.repository.PhoneRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Value("${register.user.ok}")
    private String msgRegisterOk;

    @Value("${register.user.error}")
    private String msgRegisterError;

    @Value("${email.validation}")
    private String emailVal;

    @Override
    public UserResponse createUser(UserRequest user) throws Exception {
        UserResponse userResponse = new UserResponse();
        User userSave = new User();
        Boolean validateEmail = validateExistingMail(user);
        if (validateEmail){
            userResponse = generateResponse(Boolean.TRUE, userSave);
        }
        else{
            userSave = userRepository.save(generateUser(user));
            savePhone(userSave, user.getPhones());
            userResponse = generateResponse(Boolean.FALSE, userSave);
        }

        return userResponse;
    }

    private Boolean validateExistingMail(UserRequest user){
        Boolean validateEmail = Boolean.FALSE;
        User userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail != null && userEmail.getEmail().equalsIgnoreCase(user.getEmail())){
            validateEmail = Boolean.TRUE;
        }
        return validateEmail;
    }

    private User generateUser(UserRequest userRequest){
        User user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(Boolean.TRUE);

        return user;
    }

    private UserResponse generateResponse(Boolean status, User userSave){
        UserResponse userResponse = new UserResponse();
        if(status){
            userResponse.setMessage(msgRegisterError);
        }
        else{
            userResponse.setId(userSave.getId().toString());
            userResponse.setCreated(userSave.getCreated());
            userResponse.setModified(userSave.getModified());
            userResponse.setLastLogin(userSave.getLastLogin());
            userResponse.setToken(userSave.getToken());
            userResponse.setActive(userSave.isActive());
            userResponse.setMessage(msgRegisterOk);
        }
        return userResponse;
    }

    private void savePhone(User userSave, List<UserPhone> phonelst){
        Phone phone = new Phone();
        phone.setUserId(userSave.getId());

        for (UserPhone up:phonelst) {
            phone.setNumber(up.getNumber());
            phone.setCityCode(up.getCityCode());
            phone.setCountryCode(up.getCountryCode());
            phoneRepository.save(phone);
        }
    }
}
