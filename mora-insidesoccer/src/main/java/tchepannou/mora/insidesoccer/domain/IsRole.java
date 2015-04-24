package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Role;

public class IsRole extends Role {
    private boolean accessAllTeams;

    //-- Constructor
    public IsRole(){

    }
    public IsRole(long id) {
        super(id);
    }

    public IsRole(long id, String name, boolean accessAllTeams) {
        super(id, name);
        this.accessAllTeams = accessAllTeams;
    }

    public IsRole(IsRole role) {
        super(role);
        this.accessAllTeams = role.accessAllTeams;
    }

    //-- Getter/Setter
    public boolean isAccessAllTeams() {
        return accessAllTeams;
    }

    public void setAccessAllTeams(boolean accessAllTeams) {
        this.accessAllTeams = accessAllTeams;
    }
}
