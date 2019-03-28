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
import java.util.List;

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

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("jim@jim.com", "password", "Jim",
                    "Jimmerson", true, "jim",
                    "123-456-7890", "03/22/19 10:30", "submitted");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("sam@sammy.com", "password", "Sam",
                    "Sammy", true, "sam",
                    "703-456-7890", "03/21/19 16:15", "pending interview");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@admin.com", "password", "Admin",
                    "Admin", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }


        if(jobRepository.count() == 0)
        {
            Job job = new Job("Java Web Developer",
                    "Java web development: Develop comprehensive application testing procedures\n" +
                    "                Update existing applications to meet the security and functionality standards as outlined in the company’s website policies\n" +
                    "                Implement testing tools that monitor the ongoing performance of the company website",
                    new Date(),
                    false);
            job.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("JavaScript"), new Keyword("SpringBoot"),
                                           new Keyword("Spring")));

            jobRepository.save(job);

            job = new Job("QA", "Quality Assurance : JOB SKILLS AND REQUIREMENTS. Dexterity: QA analysts need to be able to manipulate materials with their hands and take apart sample items to make sure they work properly. Math: Basic math and computer knowledge are needed when analyzing, calibrating or measuring materials.", new Date(), false);
            job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
            jobRepository.save(job);

            job = new Job("DBA", "Database Administrator : Proven working experience as a Database administrator\n" +
                    "Hands-on experience with database standards and end user applications\n" +
                    "Excellent knowledge of data backup, recovery, security, integrity and SQLProven working experience as a Database administrator ", new Date(), false);
            job.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("CRUD")));
            jobRepository.save(job);


        }
        }
}
