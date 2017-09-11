package byAJ.repositories;

import byAJ.models.BullhornPost;
import byAJ.models.BullhornUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BullhornPostRepository extends CrudRepository<BullhornPost, Long> {

    List<BullhornPost> findByBullhornuser(BullhornUser user);

    @Query(value="Select * from bullhorn_post order by post_date desc", nativeQuery = true)
    List<BullhornPost> findAllOrdered();

    List<BullhornPost> findByOrderByPostDateDesc();

    @Query(value="Select * from bullhorn_post where bullhornuser_id " +
            "in (select followee_id from bullhorn_follow where user_id = ?1) order by post_date desc", nativeQuery = true)
    List<BullhornPost> findAllFollowedOrdered(long user_id);

}
