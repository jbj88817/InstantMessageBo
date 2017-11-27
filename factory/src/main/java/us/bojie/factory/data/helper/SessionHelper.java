package us.bojie.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import us.bojie.factory.model.db.Session;
import us.bojie.factory.model.db.Session_Table;

/**
 * Created by bojiejiang on 11/26/17.
 * 会话辅助工具类
 */

public class SessionHelper {
    public static Session findFromLocal(String id) {
        return SQLite.select()
                .from(Session.class)
                .where(Session_Table.id.eq(id))
                .querySingle();
    }
}
