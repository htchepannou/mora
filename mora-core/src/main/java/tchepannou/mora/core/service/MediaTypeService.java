package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.MediaType;

import java.util.List;

public interface MediaTypeService {
    MediaType findById(long id);

    List<MediaType> findAll();
}
