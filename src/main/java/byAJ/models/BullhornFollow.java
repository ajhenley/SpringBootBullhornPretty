package byAJ.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BullhornFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long user_id;

    private long followee_id;

    public BullhornFollow(long user_id, long followee_id) {
        this.user_id = user_id;
        this.followee_id = followee_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getFollowee_id() {
        return followee_id;
    }

    public void setFollowee_id(long followee_id) {
        this.followee_id = followee_id;
    }
}
