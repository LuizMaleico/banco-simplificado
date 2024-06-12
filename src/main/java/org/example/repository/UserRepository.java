package org.example.repository;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    public UserEntity existsCpf(String cpf);
//    public UserEntity existsEmail(String email);
}
