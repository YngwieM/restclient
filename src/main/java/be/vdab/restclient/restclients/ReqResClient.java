package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;

import java.util.Optional;

public interface ReqResClient {
    Optional<User> findById(long id);
}
