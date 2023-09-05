package com.example.nabd.service.imp;

import com.example.nabd.dtos.BasisResponse;
import com.example.nabd.dtos.UserDto;
import com.example.nabd.entity.User;
import com.example.nabd.enums.Roles;
import com.example.nabd.mapper.BasisResponseMapper;
import com.example.nabd.repository.UserRepo;
import com.example.nabd.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {
    private final UserRepo userRepo;
    private final BasisResponseMapper basisResponseMapper = new BasisResponseMapper();

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public BasisResponse getUsers(int pageNo,int pageSize,String sortBy,String filter) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<User> users = userRepo.findAll(pageable);
        List<User> userList = users.getContent();
        if (filter!=null){
            Roles.valueOf(filter);
            List<User> userFilterList = userList.stream().filter(user -> user.getRoles().name().equals(filter)).toList();
            List<UserDto> userDtolist = userFilterList.stream().map(user -> UserDto.builder()
                    .name(user.getName()).phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail()).build()).toList();
            basisResponseMapper.createBasisResponseForUser(userDtolist,pageNo,users);
        }
        List<UserDto> userDtolist = userList.stream().map(user -> UserDto.builder()
                .name(user.getName()).phoneNumber(user.getPhoneNumber())
                .email(user.getEmail()).build()).toList();
        return basisResponseMapper.createBasisResponseForUser(userDtolist,pageNo,users);
    }
}
