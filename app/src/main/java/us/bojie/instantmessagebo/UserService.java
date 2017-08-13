package us.bojie.instantmessagebo;

/**
 * Created by bojiejiang on 8/12/17.
 */

public class UserService implements IUserService {

    @Override
    public String search(int hashCode) {
        return "User" + hashCode;
    }
}
