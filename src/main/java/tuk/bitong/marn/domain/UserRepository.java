package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tuk.bitong.marn.domain.User;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select u from User u where u.enabled=1 And u.userName =?1 ")
    public User findByUserName(String username);

}