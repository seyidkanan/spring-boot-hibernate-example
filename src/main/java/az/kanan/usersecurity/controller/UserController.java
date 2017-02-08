package az.kanan.usersecurity.controller;

import az.kanan.usersecurity.Util.Utility;
import az.kanan.usersecurity.pojo.User;
import az.kanan.usersecurity.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kanan on 2/7/2017.
 */
@RestController()
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String findByNameOrFindAll(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "id", required = false) Integer id) throws JsonProcessingException {
        String result = null;
        try {
            User user;
            if (name != null && id != null) {
                user = service.findById(id);
                if (user != null) {
                    result = Utility.prepareUser4Gson(user);
                } else {
                    result = Utility.prepareErrorResponse4Json("User has not with this parameter");
                }
            } else if (name == null && id == null) {
                List<User> userList = service.findAll();
                if (userList.size() > 0) {
                    result = Utility.list2JsonArrayString(userList);
                } else {
                    result = Utility.prepareErrorResponse4Json("User has no data");
                }
            } else if (name != null && !name.replaceAll("\"", "").isEmpty()) {
                user = service.findByName(name.replaceAll("\"", ""));
                if (user != null) {
                    result = Utility.prepareUser4Gson(user);
                } else {
                    result = Utility.prepareErrorResponse4Json("User has not with this parameter");
                }
            } else if (id != null) {
                user = service.findById(id);
                if (user != null) {
                    result = Utility.prepareUser4Gson(user);
                } else {
                    result = Utility.prepareErrorResponse4Json("User cannot find");
                }
            } else if (name.replaceAll("\"", "").isEmpty()) {
                result = Utility.prepareErrorResponse4Json("name field cannot be empty");
            }
        } catch (Exception e) {
            result = Utility.prepareErrorResponse4Json(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/add",
            produces = {"application/json; charset=UTF-8"})
    public String save(@RequestBody String body) throws JsonProcessingException {
        String result;
        try {
            User user = Utility.changeJson2User(body);
            if (Utility.isUserOkey4UserWith2AllowedFields(user)) {
                service.save(user);
                result = Utility.prepareSuccessResponse4Json("Data added");
            } else {
                result = Utility.prepareErrorResponse4Json("Sending data is inconrrect");
            }
        } catch (Exception e) {
            result = Utility.prepareErrorResponse4Json(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/users/update",
            produces = {"application/json; charset=UTF-8"})
    public String update(@RequestParam(value = "id") Long userId, @RequestBody String body) throws JsonProcessingException {
        String result = null;
        try {
            User user = Utility.changeJson2User(body);
            if (Utility.isJsonBodyOkey4UserWith1or2AllowedFields(user) && Utility.isIdOkey(userId)) {
                boolean isDataUpdate = service.update(userId, user.getName(), user.getPass());
                if (isDataUpdate) {
                    result = Utility.prepareSuccessResponse4Json("Data updated");
                } else {
                    result = Utility.prepareErrorResponse4Json("User with this id cannot find");
                }
            } else if (!Utility.isIdOkey(userId)) {
                result = Utility.prepareErrorResponse4Json("id field is incorrect");
            } else if (!Utility.isJsonBodyOkey4UserWith1or2AllowedFields(user)) {
                result = Utility.prepareErrorResponse4Json("in body has problem");
            }
        } catch (Exception e) {
            result = Utility.prepareErrorResponse4Json(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/delete/{id}")
    public String delete(@PathVariable Long id) throws JsonProcessingException {
        String result;
        try {
            if (Utility.isIdOkey(id)) {
                try {
                    service.delete(id);
                    result = Utility.prepareSuccessResponse4Json("Data deleted");
                } catch (EmptyResultDataAccessException e) {
                    result = Utility.prepareErrorResponse4Json("This id cannot find");
                }
            } else {
                result = Utility.prepareErrorResponse4Json("id field incorrect");
            }
        } catch (Exception e) {
            result = Utility.prepareErrorResponse4Json(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



}
