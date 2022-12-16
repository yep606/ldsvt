package ru.ledsvet.repo;

import org.springframework.data.repository.CrudRepository;
import ru.ledsvet.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByPhoneNumber(String phoneNumber);
}
