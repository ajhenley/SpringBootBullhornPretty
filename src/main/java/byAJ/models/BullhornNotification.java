package byAJ.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BullhornNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userid;

    private String notice;

    private Date occurdate;

    private Boolean beenSeen = Boolean.FALSE;

    public BullhornNotification() {
    }

    public BullhornNotification(long userid, String notice, Date occurdate, Boolean beenSeen) {
        this.userid = userid;
        this.notice = notice;
        this.occurdate = occurdate;
        this.beenSeen = beenSeen;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Boolean getBeenSeen() {
        return beenSeen;
    }

    public void setBeenSeen(Boolean beenSeen) {
        this.beenSeen = beenSeen;
    }
}
