package byAJ.repositories;

import byAJ.models.BullhornFollow;
import org.springframework.data.repository.CrudRepository;

public interface BullhornFollowRepository extends CrudRepository<BullhornFollow, Long> {
    BullhornFollow findByUseridAndFolloweeid(long userid, long followeeid);
}
