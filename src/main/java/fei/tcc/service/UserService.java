package fei.tcc.service;

import fei.tcc.dto.UsersRegisteredDto;
import fei.tcc.entity.UserEntity;
import fei.tcc.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagoretondar on 27/11/16.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersRegisteredDto> getUsers(Integer parentId) {
        List<UserEntity> allByParentId = userRepository.findAllByParentId(parentId);

        List<UsersRegisteredDto> users = new ArrayList<>();
        allByParentId.forEach(user -> {
            UsersRegisteredDto u = new UsersRegisteredDto();
            BeanUtils.copyProperties(user, u);
            users.add(u);
        });

        return users;
    }
}
