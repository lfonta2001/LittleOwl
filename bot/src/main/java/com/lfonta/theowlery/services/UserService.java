package com.lfonta.theowlery.services;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import online.theowlery.entities.UserData;
import online.theowlery.types.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, UserData> userDatas = new HashMap<>();

    private final JDA client;

    public UserService(JDA client) {
        this.client = client;
    }

    private UserData getUserData(String userId) {
        return UserData.builder()
                .id(userId)
                .build();
    }

    public UserData getOrSetUserData(User user) {
        UserData ud = getUserData(user.getId());
        if (ud == null) {
            ud = UserData.builder()
                    .id(user.getId())
                    .username(user.getName())
                    .build();
        }
        return ud;
    }

    public User getJDAUser(String guildId, String userId) {
        Guild guild = client.getGuildById(guildId);
        if (guild != null) {
            return guild.retrieveMemberById(userId).complete().getUser();
        }
        return null;
    }
}
