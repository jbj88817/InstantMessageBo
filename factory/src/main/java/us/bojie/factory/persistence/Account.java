package us.bojie.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import us.bojie.factory.Factory;

/**
 * Created by bojiejiang on 10/29/17.
 */

public class Account {

    public static final String KEY_PUSH_ID = "KEY_PUSH_ID";

    // 设备的推送Id
    private static String pushId;

    /**
     * 存储数据到XML文件，持久化
     */
    private static void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        // 存储数据
        sp.edit()
                .putString(KEY_PUSH_ID, pushId)
                .apply();
    }

    /**
     * 进行数据加载
     */
    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
    }

    /**
     * 获取推送Id
     *
     * @return 推送Id
     */
    public static String getPushId() {
        return pushId;
    }

    /**
     * 设置并存储设备的Id
     *
     * @param pushId 设备的推送ID
     */
    public static void setPushId(String pushId) {
        Account.pushId = pushId;
        Account.save(Factory.app());
    }

    /**
     * 返回当前账户是否登录
     *
     * @return True已登录
     */
    public static boolean isLogin() {
        //TODO
        return true;
    }

    /**
     * 是否已经绑定到了服务器
     *
     * @return True已绑定
     */
    public static boolean isBind() {
        //TODO
        return false;
    }
}
