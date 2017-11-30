package us.bojie.factory.data.user;

import java.util.List;

import us.bojie.factory.data.DataSource;
import us.bojie.factory.model.db.User;

/**
 * Created by bojiejiang on 11/29/17.
 * 联系人数据源
 */

public interface ContactDataSource {
    /**
     * 对数据进行加载的一个职责
     *
     * @param callback 加载成功后返回的Callback
     */
    void load(DataSource.SucceedCallback<List<User>> callback);

    /**
     * 销毁操作
     */
    void dispose();
}
