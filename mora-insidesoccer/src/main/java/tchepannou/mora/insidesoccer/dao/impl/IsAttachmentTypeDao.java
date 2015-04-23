package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.AttachmentTypeDao;
import tchepannou.mora.core.domain.AttachmentType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IsAttachmentTypeDao implements AttachmentTypeDao{
    private static final List<AttachmentType> ALL = Arrays.asList(
            new AttachmentType(AttachmentType.POST, "post")
    );

    @Override
    public List<AttachmentType> findAll() {
        return ALL;
    }

    @Override
    public AttachmentType findById(long id) {
        List<AttachmentType> result = findAll().stream()
                .filter(f -> f.getId() == id)
                .collect(Collectors.toList());
        return result.isEmpty() ? null : result.get(0);
    }
}
