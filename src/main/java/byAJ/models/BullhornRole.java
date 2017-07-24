package byAJ.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class BullhornRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Collection<BullhornUser> users;

    public BullhornRole(String role) {
        this.role = role;
    }

    public BullhornRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<BullhornUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<BullhornUser> users) {
        this.users = users;
    }
    //  Getters and Setters

}
