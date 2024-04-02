package com.realestate.service;

import com.realestate.entity.Roles;
import com.realestate.exception.UserAlreadyExistException;

public interface UserService {
    public void createUser(Roles.UserDto userDto) throws UserAlreadyExistException;
}
