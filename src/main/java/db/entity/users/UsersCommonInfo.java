package db.entity.users;

public class UsersCommonInfo {


    private String salt;
    private String firstName;
    private String lastName;
    private String birthDate;
    private int roleId;

    public UsersCommonInfo( String salt, String firstName, String lastName, String birthDate, int roleId){

        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roleId = roleId;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "UsersCommonInfo{" +
                "salt='" + salt + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
