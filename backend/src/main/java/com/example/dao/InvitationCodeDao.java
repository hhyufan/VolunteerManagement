package com.example.dao;

import java.util.List;

public interface InvitationCodeDao {
    Boolean isInvitationCodeExists(String invitationCode);
    void setInvitationCodeUsed(String invitationCode);
    void deleteInvitationCode(String invitationCode);
    void addInvitationCode(String invitationCode, int username);
    String generateInvitationCode();
    List<String> getInvitationCodesByAdmin(int id);

}
