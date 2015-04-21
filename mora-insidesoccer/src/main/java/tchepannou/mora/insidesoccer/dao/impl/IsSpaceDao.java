package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class IsSpaceDao implements SpaceDao {
    //-- Attributes
    public static final List<Long> PARTY_TYPE_IDS = Arrays.asList(3L, 4L);

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private PartyAttributeDao partyAttributeDao;

    @Value ("${insidesoccer.asset.url}")
    private String assetUrl;

    @Value("${insidesoccer.logo.default}")
    private String defaultLogoUrl;


    //-- SpaceDao overrides
    @Override
    public Space findById(long id) {
        Party party = partyDao.findById(id);
        if (party == null || !PARTY_TYPE_IDS.contains(party.getTypeId())){
            return null;
        }

        List<PartyAttribute> attributes = partyAttributeDao.findByPartyByNames(id, PartyAttribute.SPACE_ATTRIBUTE_NAMES.toArray(new String[]{}));

        return toSpace(party, attributes);
    }

    @Override
    public List<Space> findByIds(Collection<Long> ids) {
        List<Party> partys = partyDao.findByIds(ids);
        Multimap<Long, PartyAttribute> attributes = partyAttributeDao.findByPartiesByNames(ids, PartyAttribute.SPACE_ATTRIBUTE_NAMES.toArray(new String[]{}));

        List<Space> result = new LinkedList<>();
        for (Party party : partys){
            Collection<PartyAttribute> attrs = attributes.get(party.getId());
            Space space = toSpace(party, attrs);
            result.add(space);
        }
        return result;
    }

    @Override
    public long create(Space space) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Space space) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Space space) {
        throw new UnsupportedOperationException();
    }

    //-- Private
    private Space toSpace (Party party, Collection<PartyAttribute> attributes){
        Space result = new Space ();
        party.toSpace(result);
        PartyAttribute.toSpace(attributes, result);

        if (Strings.isNullOrEmpty(result.getLogoUrl())){
            result.setLogoUrl(defaultLogoUrl);
        } else if (!Strings.isNullOrEmpty(assetUrl)){
            String logoUrl = result.getLogoUrl();
            if (!Strings.isNullOrEmpty(logoUrl) && !logoUrl.startsWith("http://") && !logoUrl.startsWith("https://")) {
                result.setLogoUrl(assetUrl + logoUrl);
            }
        }

        return result;
    }
}
