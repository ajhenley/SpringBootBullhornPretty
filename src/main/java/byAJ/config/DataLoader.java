package byAJ.config;

import byAJ.models.BullhornRole;
import byAJ.models.BullhornUser;
import byAJ.repositories.BullhornRoleRepository;
import byAJ.repositories.BullhornUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    BullhornUserRepository userRepository;

    @Autowired
    BullhornRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");

        if (roleRepository.countRoles() <= 0) {
            roleRepository.save(new BullhornRole("USER"));
            roleRepository.save(new BullhornRole("ADMIN"));

            BullhornRole adminRole = roleRepository.findByRole("ADMIN");
            BullhornRole userRole = roleRepository.findByRole("USER");

            BullhornUser user = new BullhornUser("bob@bob.com", "Bobberson", "bobby5", "Bobbing down life's river...");
            user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new BullhornUser("jim@jim.com", "Jimmerson", "jimmy2times", "Snap into a slim jim...");
            user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new BullhornUser("admin@secure.com", "Admin", "password", "In total control...");
            user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

            user = new BullhornUser("sam@every.com", "Sam Everyman", "password", "everyman");
            user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
            user.setRoles(Arrays.asList(userRole, adminRole));
            userRepository.save(user);
        }
    }
}