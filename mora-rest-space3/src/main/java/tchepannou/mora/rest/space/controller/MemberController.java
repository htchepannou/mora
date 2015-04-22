package tchepannou.mora.rest.space.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.exception.MemberException;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.space.dto.CreateMemberDto;
import tchepannou.mora.rest.space.dto.MemberDto;
import tchepannou.mora.rest.space.dto.SaveMemberDto;


@RestController
public class MemberController extends BaseRestController{
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    
    //-- Public
    @RequestMapping(value="/members/{memberId}", method= RequestMethod.GET)
    public MemberDto get (@PathVariable long memberId) throws MemberException{
        Member member = memberService.findById(memberId);
        if (member == null){
            throw new NotFoundException(memberId);
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }

    @RequestMapping(value="/members", method= RequestMethod.PUT)
    public MemberDto create (@RequestBody CreateMemberDto request) throws MemberException{
        Member member = new Member ();
        request.toMember(member);
        try {
            memberService.create(member);

        } catch (MemberDuplicationException e){
            LOG.warn(String.format("User{%d + is already member of Space{%d} as RoleP%d}", request.getUserId(), request.getSpaceId(), request.getRoleId()), e);

            member = memberService.findBySpaceByUserByRole(request.getSpaceId(), request.getUserId(), request.getRoleId());
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }
    
    @RequestMapping(value="/members/{memberId}", method= RequestMethod.POST)
    public MemberDto update (@PathVariable long memberId, @RequestBody SaveMemberDto request) throws MemberException{
        Member member = memberService.findById(memberId);
        if (member == null){
            throw new NotFoundException(memberId);
        }

        try {
            request.toMember(member);
            memberService.update(member);
        } catch (MemberDuplicationException e){
            LOG.warn(member + " already exists", e);

            member = memberService.findBySpaceByUserByRole(member.getSpaceId(), member.getUserId(), request.getRoleId());
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }
    
    @RequestMapping(value="/members/{memberId}", method= RequestMethod.DELETE)
    public void delete(@PathVariable long memberId) {
        Member member = memberService.findById(memberId);
        if (member != null) {
            memberService.delete(member);
        }
    }
}
