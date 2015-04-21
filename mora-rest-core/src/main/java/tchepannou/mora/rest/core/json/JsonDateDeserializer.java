package tchepannou.mora.rest.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Strings;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        if (Strings.isNullOrEmpty(text)){
            return null;
        }

        try {
            return new SimpleDateFormat(JsonDateSerializer.PATTERN).parse(text);
        } catch (ParseException e){
            throw new InvalidFormatException("Invalid date", text, Date.class);
        }
    }
}
