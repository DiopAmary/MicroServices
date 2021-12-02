package ma.enset.authservice.services;

import ma.enset.authservice.entities.RoleEntity;
import ma.enset.authservice.entities.UserEntity;

import java.util.List;

public interface Services {
    UserEntity addUser(UserEntity userEntity);
    RoleEntity addRole(RoleEntity roleEntity);
    void addRoleToUser(String username, String role);
    UserEntity findUser(String username);
    List<UserEntity> allUsers();
    List<RoleEntity> allRoles();
}
