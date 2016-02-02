package jp.pigumer.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxResponse {

    @JsonProperty("USER_ID")
    private String userId;
    
    public AjaxResponse(String userId) {
        this.userId = userId;
    }
    
    public String getUserId() {
        return userId;
    }
    
}
