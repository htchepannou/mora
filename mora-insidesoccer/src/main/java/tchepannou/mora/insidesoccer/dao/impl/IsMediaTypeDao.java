package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IsMediaTypeDao implements MediaTypeDao{
    public static final long IMAGE  = MediaType.IMAGE;
    public static final long VIDEO  = MediaType.VIDEO;
    public static final long ASB = 100;
    public static final long UNKNOWN  = MediaType.UNKNOWN;


    private static final List<MediaType> ALL = Arrays.asList(
            new MediaType(IMAGE, "image"),
            new MediaType(VIDEO, "video"),
            new MediaType(ASB, "asb"),
            new MediaType(UNKNOWN, "unknown")
    );

    @Override
    public List<MediaType> findAll() {
        return ALL;
    }

    @Override
    public MediaType findById(long id) {
        List<MediaType> result = findAll().stream()
                .filter(f -> f.getId() == id)
                .collect(Collectors.toList());
        return result.isEmpty() ? null : result.get(0);
    }
}
