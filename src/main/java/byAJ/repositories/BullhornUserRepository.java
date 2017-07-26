package byAJ.repositories;

import byAJ.models.BullhornUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BullhornUserRepository extends CrudRepository<BullhornUser, Long> {

    BullhornUser findByUseremail(String useremail);

    BullhornUser findByUsername(String username);

    Long countByUseremail(String useremail);

    Long countByUsername(String username);

    @Query(value="Select * from bullhornuser u where u.id in (select user_id from bullhornfollow f where f.followee_id = ?1)", nativeQuery = true)
    Iterable<BullhornUser> findUsersFollowing(Long id);

    @Query(value="Select * from bullhornuser u where u.id in (select followee_id from bullhornfollow f where f.user_id = ?1)", nativeQuery = true)
    Iterable<BullhornUser> findUsersFollowed(Long id);

}
