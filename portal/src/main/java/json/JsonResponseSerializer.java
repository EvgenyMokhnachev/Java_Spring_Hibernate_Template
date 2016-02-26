package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import response.JsonResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yevhen on 4/20/15.
 */
public class JsonResponseSerializer extends JsonSerializer<JsonResponse> {
    @Override
    public void serialize(JsonResponse value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();

        jgen.writeBooleanField("success", value.isSuccess());

        Map<String, Object> errors = value.getErrors();
        if (errors != null && errors.size() > 0) {
            jgen.writeObjectField("errors", errors);
        }

        Object data = value.getData();
        if (data != null) {
            jgen.writeObjectField("data", data);
        }

        jgen.writeEndObject();

    }
}
