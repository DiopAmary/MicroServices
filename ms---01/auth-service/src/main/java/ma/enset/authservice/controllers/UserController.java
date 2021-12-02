package ma.enset.authservice.controllers;

import ma.enset.authservice.entities.RoleEntity;
import ma.enset.authservice.entities.RoleUser;
import ma.enset.authservice.entities.UserEntity;
import ma.enset.authservice.entities.requests.UserRequest;
import ma.enset.authservice.entities.responses.UserResponse;
import ma.enset.authservice.services.Services;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private Services services;

    public UserController(Services services) {
        this.services = services;
    }

    @GetMapping(
            path = "/users",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    List<UserResponse> getAllUsers(){
        List<UserEntity> allUsersEntity = services.allUsers();
        ModelMapper modelMapper = new ModelMapper();
        List<UserResponse> allUsersResponse = new ArrayList<UserResponse>();
        for(UserEntity userEntity : allUsersEntity){
            UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
            allUsersResponse.add(userResponse);
        }
        return allUsersResponse;
    }

    @GetMapping(
            path = "/roles",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    List<RoleEntity> getAllRoles(){
        List<RoleEntity> allRolesEntity = services.allRoles();
        return allRolesEntity;
    }

    @GetMapping(
            path = "/user",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    UserResponse getUser(
            @RequestParam() String username
    ){
        UserEntity userEntity  = services.findUser(username);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @PostMapping(
            path = "/user",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    UserResponse addUser(
            @ModelAttribute UserRequest userRequest
            ){
        UserEntity userEntity = new UserEntity(null, userRequest.getUsername(), userRequest.getPassword(), new ArrayList<>());
        services.addUser(userEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @PostMapping(
            path = "/role",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    RoleEntity addRole(
            @RequestParam(name = "role") String role
    ){
        RoleEntity roleEntity = new RoleEntity(null, role);
        services.addRole(roleEntity);
        ModelMapper modelMapper = new ModelMapper();
        return roleEntity;
    }

    @PostMapping(
            path = "/user-role",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    UserResponse addRoleToUser(
            @ModelAttribute RoleUser roleUser
    ){
        services.addRoleToUser(roleUser.getUsername(), roleUser.getRole());
        UserEntity userEntity = services.findUser(roleUser.getUsername());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, UserResponse.class);
    }
}
