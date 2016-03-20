package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tuk.bitong.marn.domain.User;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String username);

}