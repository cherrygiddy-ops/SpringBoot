package com.morrisco.net.store.userRegistrationService;

public interface UserRepository {
    void save(User user);

    User findByEmail(String mail);
}
