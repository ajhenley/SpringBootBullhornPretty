package byAJ.validator;

import byAJ.models.BullhornUser;
import byAJ.repositories.BullhornUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    BullhornUserRepository userRepository;

    public boolean supports(Class<?> clazz){
        return BullhornUser.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors){
        BullhornUser user = (BullhornUser) target;

        String email = user.getUseremail();
        String username = user.getUsername();
        String password = user.getUserpassword();

        if(username.length() < 5){
            errors.rejectValue("username","user.username.tooShort");
        }
        if(password.length() < 5){
            errors.rejectValue("userpassword","user.password.tooShort");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "user.email.empty");

        if(userRepository.countByUseremail(email)>0){
            errors.rejectValue("useremail", "user.email.duplicate");
        }
        if(userRepository.countByUsername(username)>0){
            errors.rejectValue("username", "user.username.duplicate");
        }
    }
}