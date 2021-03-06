package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.Keyword;
import edu.montgomerycollege.drdoom.Models.Role;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.KeywordRepository;
import edu.montgomerycollege.drdoom.Repositories.RoleRepository;
import edu.montgomerycollege.drdoom.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception
    {
        if(roleRepository.count() == 0) {
            System.out.println("Data loader user run");

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@admin.com", "password", "Admin", "Admin", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }


        if(jobRepository.count() == 0) {
            System.out.println("Data loader user run");
            jobRepository.save(new Job("Java Web Developer", "Java web development", new Date(), false));
            jobRepository.save(new Job("QA", "Quality assurance", new Date(), false));
            jobRepository.save(new Job("DBA", "Database Architect", new Date(), false));
        }

        if(keywordRepository.count() == 0) {
            keywordRepository.save(new Keyword("Oracle"));
            keywordRepository.save(new Keyword("Java Web Development"));
            keywordRepository.save(new Keyword("Java"));
            keywordRepository.save(new Keyword("JavaScript"));
            keywordRepository.save(new Keyword("SpringBoot"));
            keywordRepository.save(new Keyword("Spring"));
        }
    }
}
