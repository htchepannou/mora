delete from login;

delete from party where party_owner_fk is not null;
delete from party where party_owner_fk is null;