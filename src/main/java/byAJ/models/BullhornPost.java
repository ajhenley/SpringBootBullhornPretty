package byAJ.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BullhornPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date postDate;

    private String postText;

    private String image;

    @ManyToOne
    private BullhornUser bullhornuser;

    public BullhornPost() {
    }

    public BullhornPost(Date postDate, String postText, BullhornUser bullhornuser) {
        this.postDate = postDate;
        this.postText = postText;
        this.bullhornuser = bullhornuser;
    }

    public BullhornPost(Date postDate, String postText, String image, BullhornUser bullhornuser) {
        this.postDate = postDate;
        this.postText = postText;
        this.image = image;
        this.bullhornuser = bullhornuser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public BullhornUser getBullhornuser() {
        return bullhornuser;
    }

    public void setBullhornuser(BullhornUser bullhornuser) {
        this.bullhornuser = bullhornuser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
