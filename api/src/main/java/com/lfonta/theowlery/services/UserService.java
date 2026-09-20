package com.lfonta.theowlery.services;

import com.lfonta.theowlery.dto.response.UserDataResponse;
import com.lfonta.theowlery.exceptions.UserNotFound;
import com.lfonta.theowlery.types.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {

    private final Map<String, User> loggedUsers = new HashMap<>();

    public User matchUser(SupportedAuthPlatform platform, MappableAuthUserData userData) throws UserNotFound {
        if (Objects.requireNonNull(platform) == SupportedAuthPlatform.DISCORD) {
            DiscordAuthUserData data = (DiscordAuthUserData) userData;
            User user = new User(data.getId(), data.getUsername(), data.getGlobalName(), data.getAvatar(), "USER");
            loggedUsers.put(user.id(), user);
            return user;
        } else {
            throw new UserNotFound();
        }
    }

    public UserAuthData getAuthData(String id) {
        return new UserAuthData(true, Set.of(SupportedAuthPlatform.DISCORD, SupportedAuthPlatform.GOOGLE));
    }

    public ResponseEntity<UserDataResponse> getUser(String id) {
        User user = loggedUsers.get(id);
        if (user == null) throw new UserNotFound();
        return ResponseEntity.status(200).body(new UserDataResponse(user.id(), user.username(), user.globalName(), user.avatar(), user.role()));
    }
}
