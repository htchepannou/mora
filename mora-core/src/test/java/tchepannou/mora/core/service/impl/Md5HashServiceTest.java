package tchepannou.mora.core.service.impl;

import org.junit.Test;
import tchepannou.mora.core.service.HashService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class Md5HashServiceTest {
    HashService service =  new Md5HashService();

    @Test
    public void testGenerate_Null_shouldReturnNull() throws Exception {
        assertThat(service.generate(null), nullValue());
    }

    @Test
    public void testGenerate() throws Exception {
        assertThat(service.generate("This is a test"), equalTo("ce114e4501d2f4e2dcea3e17b546f339"));
    }


}