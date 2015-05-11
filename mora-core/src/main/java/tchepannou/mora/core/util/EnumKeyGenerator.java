package tchepannou.mora.core.util;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class EnumKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return new EnumKey(o.getClass(), objects);
    }
}
