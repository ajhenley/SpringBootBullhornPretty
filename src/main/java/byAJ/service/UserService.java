package byAJ.service;

import byAJ.models.BullhornUser;
import byAJ.repositories.BullhornRoleRepository;
import byAJ.repositories.BullhornUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    BullhornUserRepository userRepository;

    @Autowired
    BullhornRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(BullhornUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BullhornUser findByEmail(String email) {
        return userRepository.findByUseremail(email);
    }
    public Long countByEmail(String email) {
        return userRepository.countByUseremail(email);
    }

    public BullhornUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(BullhornUser user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
        userRepository.save(user);
    }

    public void saveAdmin(BullhornUser user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
        userRepository.save(user);
    }
}
