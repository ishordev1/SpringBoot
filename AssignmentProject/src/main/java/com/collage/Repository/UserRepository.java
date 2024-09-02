package com.collage.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.Dto.UserDto;
import com.collage.Entity.Client;
import com.collage.Entity.User;
import com.collage.Entity.Writer;

public interface UserRepository extends JpaRepository<User, String>{
User findUserByEmail(String email);
Optional<User> findUserByEmailToken(String emailToken);
Optional<User> findUserByClient(Client client);
Optional<User> findUserByWriter(Writer client);



}
