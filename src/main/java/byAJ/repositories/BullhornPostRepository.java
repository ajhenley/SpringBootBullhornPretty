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
}
