package byAJ.repositories;

import byAJ.models.BullhornUser;
import org.springframework.data.repository.CrudRepository;

public interface BullhornUserRepository extends CrudRepository<BullhornUser, Long> {

    BullhornUser findByUseremail(String useremail);

    BullhornUser findByUsername(String username);

    Long countByUseremail(String useremail);

    Long countByUsername(String username);

}
