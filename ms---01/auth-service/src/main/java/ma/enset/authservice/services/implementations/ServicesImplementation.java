package ma.enset.authservice.services.implementations;

import ma.enset.authservice.entities.RoleEntity;
import ma.enset.authservice.entities.UserEntity;
import ma.enset.authservice.repositories.RoleRepository;
import ma.enset.authservice.repositories.UserRepository;
import ma.enset.authservice.services.Services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicesImplementation implements Services {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public ServicesImplementation(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public RoleEntity addRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        RoleEntity roleEntity = roleRepository.findRoleEntityByRole(role);
        userEntity.getRoles().add(roleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUser(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public List<UserEntity> allUsers() { return userRepository.findAll(); }

    @Override
    public List<RoleEntity> allRoles() { return roleRepository.findAll(); }


}
