package tuk.bitong.marn.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

    @Query("select a.role from UserRole a, User b where b.userName=?1 and a.userid=b.userId")
    public List<String> findRoleByUserName(String username);

}