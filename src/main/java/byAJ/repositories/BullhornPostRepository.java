package byAJ.repositories;

import byAJ.models.BullhornPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BullhornPostRepository extends CrudRepository<BullhornPost, Long> {

  //  List<BullhornPost> findAllByUser
}
