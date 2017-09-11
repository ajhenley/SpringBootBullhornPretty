package byAJ.service;

import byAJ.models.BullhornFollow;
import byAJ.models.BullhornNotification;
import byAJ.repositories.BullhornNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    BullhornNotificationRepository bullhornNotificationRepository;

    public void addRepostNotification(String username, long user, long post){
        Date rightnow = new Date();
        StringBuilder message = new StringBuilder();
        message.append(rightnow.toString());
        message.append(" - ");
        message.append(username);
        message.append(" reposted your post <a href='/post/");
        message.append(post);
        message.append("'>#");
        message.append(post);
        message.append("</a>");
        BullhornNotification bn = new BullhornNotification(user, message.toString(), new Date(), Boolean.FALSE);
        bullhornNotificationRepository.save(
                new BullhornNotification(user, message.toString(), rightnow, Boolean.FALSE)
        );
    }

    public void addFollowNotification(String username, long follower, long user, long post){
        Date rightnow = new Date();
        StringBuilder message = new StringBuilder();
        message.append(rightnow.toString());
        message.append(" - ");
        message.append(" <a href='/user/");
        message.append(follower);
        message.append("'>");
        message.append(username);
        message.append("</a> followed you.");
        BullhornNotification bn = new BullhornNotification(user, message.toString(), new Date(), Boolean.FALSE);
        bullhornNotificationRepository.save(
                new BullhornNotification(user, message.toString(), rightnow, Boolean.FALSE)
        );
    }

    public void addUnfollowNotification(String username, long follower, long user, long post){
        Date rightnow = new Date();
        StringBuilder message = new StringBuilder();
        message.append(rightnow.toString());
        message.append(" - ");
        message.append(" <a href='/user/");
        message.append(follower);
        message.append("'>");
        message.append(username);
        message.append("</a> unfollowed you.");
        BullhornNotification bn = new BullhornNotification(user, message.toString(), new Date(), Boolean.FALSE);
        bullhornNotificationRepository.save(
                new BullhornNotification(user, message.toString(), rightnow, Boolean.FALSE)
        );
    }

    public void viewedNotification(long notifyid){
        BullhornNotification notify = bullhornNotificationRepository.findOne(notifyid);
        notify.setBeenSeen(Boolean.TRUE);
        bullhornNotificationRepository.save(notify);
    }
}
