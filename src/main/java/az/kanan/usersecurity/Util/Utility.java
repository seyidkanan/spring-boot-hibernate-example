package az.kanan.usersecurity.Util;

import az.kanan.usersecurity.pojo.ResponseClass;
import az.kanan.usersecurity.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kanan on 2/7/2017.
 */
public class Utility {

    public static User changeJson2User(String body) {
        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(body, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String list2JsonArrayString(List<User> userList) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userList);
    }

    public static String prepareUser4Gson(User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(user);
    }

    public static String prepareErrorResponse4Json(String message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ResponseClass(message, false));
    }

    public static String prepareSuccessResponse4Json(String message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ResponseClass(message, true));
    }

    public static boolean isUserOkey4UserWith2AllowedFields(User user) {
        return isUserObjectOkey(user) && isNameOkey(user.getName()) && isPassOkey(user.getPass());
    }

    public static boolean isNameOkey(String name) {
        return !(name == null || name.trim().replaceAll("\"", "").isEmpty());
    }

    public static boolean isPassOkey(String pass) {
        return !(pass == null || pass.trim().replaceAll("\"", "").isEmpty());
    }

    public static boolean isUserObjectOkey(User user) {
        return user != null;
    }

    public static boolean isJsonBodyOkey4UserWith1or2AllowedFields(User user) {
        return isUserObjectOkey(user) && (isNameOkey(user.getName()) || isPassOkey(user.getPass()));
    }


    public static boolean isIdOkey(Long id) {
        return id != null;
    }

}
