package byAJ.repositories;

import byAJ.models.BullhornRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface  BullhornRoleRepository extends CrudRepository<BullhornRole, Long> {
    BullhornRole findByRole(String role);

    @Query(value="Select count(*) from bullhorn_role", nativeQuery = true)
    long countRoles();
}
