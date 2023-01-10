package com.interview.tuncode.security;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailService(StudentRepository studentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);

        if (student == null) {
            throw new UsernameNotFoundException("This user cannot be found !");
        }
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(student.getSecretText()))
                .roles(student.getStudentRole().toString())
                .build();
    }
}
