package tchepannou.mora.rest.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JsonDateSerializerTest {

    @Test
    public void testSerialize() throws Exception {
        // Given
        JsonGenerator gen = mock(JsonGenerator.class);
        SerializerProvider provider = mock(SerializerProvider.class);
        Date date = new SimpleDateFormat("yyyy/MM/dd h/mm/ss a Z").parse("2015/02/01 2/30/20 PM UTC");

        // When
        new JsonDateSerializer().serialize(date, gen, provider);

        // Then
        verify(gen).writeString("2015-02-01 09:30:20 -0500");
    }
}