package az.kanan.usersecurity.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by Kanan on 2/7/2017.
 */
public class ResponseClass {

    private String message;
    private Boolean success;


    public ResponseClass(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResponseClass() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
