package online.theowlery.services;

import online.theowlery.entities.UserData;
import online.theowlery.types.annotations.Service;

@Service
public class UserService {

    public UserData getUserData(String userId) {
        return UserData.builder()
                .id(userId)
                .build();
    }
}
