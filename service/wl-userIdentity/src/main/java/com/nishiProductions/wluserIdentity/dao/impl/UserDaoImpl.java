package com.nishiProductions.wluserIdentity.dao.impl;

import com.nishiProductions.wluserIdentity.dao.UserDao;
import com.nishiProductions.wluserIdentity.domain.User;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import com.nishiProductions.wluserIdentity.modelMapper.UserModelMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    UserModelMapper userModelMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        log.info("UserDaoImpl.saveUser invoked");
        User savedUser = userModelMapper.userDtoToUser(userDto);
        entityManager.persist(savedUser);
        UserDto savedUserDto = userModelMapper.userToUserDto(savedUser);
        log.info("savedUserDto------------------{}", savedUserDto);
        return savedUserDto;
    }

    @Override
    public UserDto getUserByUserNameAndPassword(LoginRequestDto loginRequestDto) {
        log.info("UserDaoImpl.getUserByUserNameAndPassword() invoked");
        User user = null;
        UserDto retrievedUserDto = null;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> rootUser = cq.from(User.class);
        Predicate emailPredicate = cb.equal(rootUser.get("email"), loginRequestDto.getEmail());
        Predicate passwordPredicate = cb.like(rootUser.get("password"), loginRequestDto.getPassword());
        cq.where(emailPredicate, passwordPredicate);

        TypedQuery<User> query = entityManager.createQuery(cq);
        try {
            user = query.getSingleResult();
            if (user != null) {
                retrievedUserDto = userModelMapper.userToUserDto(user);
            }
        } catch (NoResultException e) {
            log.info("No user returned for email------------------{}", loginRequestDto.getEmail());
        }
        return retrievedUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("UserDaoImpl.getAllUsers() invoked");
        List<User> userList = null;
        List<UserDto> retrievedUserDtoList = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> rootUser = cq.from(User.class);

        TypedQuery<User> query = entityManager.createQuery(cq);
        userList = query.getResultList();
        if (userList != null) {
            retrievedUserDtoList = userList.stream().map(user -> {
                return userModelMapper.userToUserDto(user);
            }).collect(Collectors.toList());

        }
        return retrievedUserDtoList;
    }
}
