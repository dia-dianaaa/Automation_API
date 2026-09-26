package Acount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBodyAcount {

    @JsonProperty("userID")
    private String userId;
    private String username;
    private String[] books;

    public String getUserID() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String[] getBooks() {
        return books;
    }
}
