package db.entity.users;

public class UserBuilder implements Builder{


    private UsersSecretInfo usersSecretInfo;
    private UsersCommonInfo usersCommonInfo;

    @Override
    public void setUsersSecretInfo(UsersSecretInfo usersSecretInfo) {
        this.usersSecretInfo = usersSecretInfo;
    }

    @Override
    public void setUsersCommonInfo(UsersCommonInfo usersCommonInfo) {
        this.usersCommonInfo = usersCommonInfo;
    }

    public Users getResult(){
        return new Users(usersSecretInfo, usersCommonInfo);
    }
}
