package byAJ.repositories;

import byAJ.models.BullhornNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BullhornNotificationRepository extends CrudRepository<BullhornNotification, Long> {
    List<BullhornNotification> findAllByUseridAndBeenSeenOrderById(long userid, Boolean beenseen);
}
