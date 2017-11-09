package com.codeup.blog.springbootblog.Models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by RyanHarper on 11/7/17.
 */

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") // one user can have many posts.
    private List<Post> posts;

    public User(){}

    // security files will need the next constructor. It makes clones/copies. Why?
    // Spring dependencies require a copy of all the properties in the User object
    // because it's the only one that requires authentication:
    public User(User copy) {
        this.id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        this.email = copy.email;
        this.username = copy.username;
        this.password = copy.password;
        this.posts = copy.posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public boolean equals(Object anotherUser) {
        if (anotherUser != null)
            if (anotherUser instanceof User)
                if (id.equals(((User) anotherUser).id)) return true;
        // otherwise
        return false;
    }
}


