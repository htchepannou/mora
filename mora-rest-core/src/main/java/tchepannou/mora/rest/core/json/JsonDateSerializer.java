package tchepannou.mora.rest.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Date> {
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss Z";

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)  throws IOException
    {
        String formattedDate = new SimpleDateFormat(PATTERN).format(date);
        gen.writeString(formattedDate);
    }
}
