package response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import json.JsonResponseSerializer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonSerialize(using = JsonResponseSerializer.class)
public class JsonResponse {
    private boolean success = true;
    private Map<String, Object> errors = new HashMap<>();
    private Map<String, Object> data = new HashMap<>();
    private BindingResult bindingResult;
    private String[] fields;

    public JsonResponse(){}

    public JsonResponse(BindingResult bindingResult, String... fields){
        this.bindingResult = bindingResult;
        this.fields = fields;

        checkErrors();
    }

    public void addError(String field, String message){
        success = false;
        errors.put(field, message);
    }

    private void checkErrors(){
        if (bindingResult.hasErrors()) {
            if (fields.length == 0) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
            }
            else {
                for (String field : fields) {
                    if (bindingResult.hasFieldErrors(field)) {
                        errors.put(field, bindingResult.getFieldError(field).getDefaultMessage());
                    }
                }
            }
            if (errors.size() > 0) {
                success = false;
            }
        }
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getData(String key){
        return data.get(key);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setData(String key, Object data) {
        this.data.put(key, data);
    }
}

