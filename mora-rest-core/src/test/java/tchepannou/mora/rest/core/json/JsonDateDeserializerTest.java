package tchepannou.mora.rest.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonDateDeserializerTest {
    @Mock
    private JsonParser jp;

    @Mock
    private DeserializationContext context;

    private JsonDateDeserializer deserializer = new JsonDateDeserializer();

    @Test
    public void testDeserialize() throws Exception {
        when(jp.getText()).thenReturn("2014-12-01 14:30:55 -0500");

        Date result = deserializer.deserialize(jp, context);

        assertThat(result, equalTo(new SimpleDateFormat(JsonDateSerializer.PATTERN).parse("2014-12-01 14:30:55 -0500")));
    }



    @Test
    public void testDeserialize_null_shouldReturnNull() throws Exception {
        when(jp.getText()).thenReturn(null);

        Date result = deserializer.deserialize(jp, context);

        assertThat(result, nullValue());
    }

    @Test
    public void testDeserialize_empty_shouldReturnNull() throws Exception {
        when(jp.getText()).thenReturn("");

        Date result = deserializer.deserialize(jp, context);

        assertThat(result, nullValue());
    }

    @Test(expected = InvalidFormatException.class)
    public void testDeserialize_badFromat_shouldThrowInvalidFormatException() throws Exception {
        when(jp.getText()).thenReturn("???");

        Date result = deserializer.deserialize(jp, context);
    }

}