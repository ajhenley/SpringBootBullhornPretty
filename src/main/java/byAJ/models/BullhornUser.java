package byAJ.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class BullhornUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date joindate;

    private String location;

    private String motto;

    private String useremail; //

    private String username; //

    private String userpassword;

    private String headshot = "http://res.cloudinary.com/learningbycoding/image/upload/c_scale,w_64/v1501636042/blank-profile-picture-973460_640_gjacyc.png";

    private String profilepic = "http://res.cloudinary.com/learningbycoding/image/upload/c_scale,w_127/v1501636042/blank-profile-picture-973460_640_gjacyc.png";

    private String website;

    private String background;

    @OneToMany(mappedBy = "bullhornuser")
    private List<BullhornPost> bullhornposts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "bullhornuser_id"),inverseJoinColumns = @JoinColumn(name = "bullhornrole_id"))
    private Collection<BullhornRole> roles;

    public BullhornUser() {
    }

    public BullhornUser(String useremail, String username, String userpassword, String motto ) {
        this.joindate = new Date();
        this.motto = motto;
        this.useremail = useremail;
        this.username = username;
        this.userpassword = userpassword;
    }

    public BullhornUser(Date joindate, String motto, String useremail, String username, String userpassword, String headshot, String background) {
        this.joindate = joindate;
        this.motto = motto;
        this.useremail = useremail;
        this.username = username;
        this.userpassword = userpassword;
        this.headshot = headshot;
        this.background = background;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public List<BullhornPost> getBullhornposts() {
        return bullhornposts;
    }

    public void setBullhornposts(List<BullhornPost> bullhornposts) {
        this.bullhornposts = bullhornposts;
    }

    public Collection<BullhornRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<BullhornRole> roles) {
        this.roles = roles;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
