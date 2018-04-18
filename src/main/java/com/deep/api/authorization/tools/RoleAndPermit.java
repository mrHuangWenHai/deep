package com.deep.api.authorization.tools;

public class RoleAndPermit {
    Long role;
    byte extended;
    String extendedPermit;

    public String getExtendedPermit() {
        return extendedPermit;
    }

    public void setExtendedPermit(String extendedPermit) {
        this.extendedPermit = extendedPermit;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public byte getExtended() {
        return extended;
    }

    public void setExtended(byte extended) {
        this.extended = extended;
    }

}
