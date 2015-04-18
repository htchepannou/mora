package tchepannou.mora.insidesoccer.service.impl;

import org.junit.Test;
import tchepannou.mora.core.domain.Password;

public class IsPasswordServiceTest {
    private IsPasswordService service = new IsPasswordService();

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        service.create(new Password());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEncrypt() throws Exception {
        service.encrypt("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFindByUser() throws Exception {
        service.findByUser(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        service.update(new Password());
    }
}