package us.bojie.factory.persistence;

/**
 * Created by bojiejiang on 10/29/17.
 */

public class Account {

    // 设备的推送Id
    private static String pushId = "";


    public static String getPushId() {
        return pushId;
    }

    public static void setPushId(String pushId) {
        Account.pushId = pushId;
    }
}
