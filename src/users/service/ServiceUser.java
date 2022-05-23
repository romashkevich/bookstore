package users.service;

import users.dao.entity.User;
import users.service.dto.UserDto;


import java.sql.SQLException;
import java.util.List;

public interface ServiceUser {
    public List<UserDto> getAllUserDto();

    public UserDto getUserDtoById(Long id) throws SQLException;

    public UserDto createUserDto(UserDto user) throws SQLException;

    public UserDto updateUserDto(UserDto user) throws SQLException;

    public void deleteUserDto(Long id) throws SQLException;

    public UserDto getUserDtoByEmail(String email) throws SQLException;

    public List<UserDto> getUsersDtoByLastName(String lastName) throws SQLException;

    public int countAllUsersDto() throws SQLException;
    UserDto getUserDtoByLolin(String login) throws SQLException;

}
