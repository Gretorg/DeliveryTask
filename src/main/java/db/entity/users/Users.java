package db.entity.users;

import java.util.Objects;

/**
 * Users entity
 *
 * @author  Vlad Salimovskyi
 */
public class Users {

    private int id;
    private final UsersCommonInfo usersCommonInfo;
    private final UsersSecretInfo usersSecretInfo;

    public Users(UsersSecretInfo usersSecretInfo, UsersCommonInfo usersCommonInfo){
        this.usersSecretInfo = usersSecretInfo;
        this.usersCommonInfo = usersCommonInfo;
    }

    public UsersCommonInfo getUsersCommonInfo() {
        return usersCommonInfo;
    }

    public UsersSecretInfo getUsersSecretInfo() {
        return usersSecretInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", usersCommonInfo=" + usersCommonInfo +
                ", usersSecretInfo=" + usersSecretInfo +
                '}';
    }
}