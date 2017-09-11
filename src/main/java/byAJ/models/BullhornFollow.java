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

    private long userid;

    private long followeeid;


    public BullhornFollow() {
    }

    public BullhornFollow(long userid, long followeeid) {
        this.userid = userid;
        this.followeeid = followeeid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getFolloweeid() {
        return followeeid;
    }

    public void setFolloweeid(long followeeid) {
        this.followeeid = followeeid;
    }
}
