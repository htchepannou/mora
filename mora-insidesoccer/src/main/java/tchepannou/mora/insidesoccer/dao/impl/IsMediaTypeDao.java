package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IsMediaTypeDao implements MediaTypeDao{
    private List<MediaType> ALL = Arrays.asList(
            new MediaType(1, "image"),
            new MediaType(2, "video"),
            new MediaType(3, "oembed")
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
