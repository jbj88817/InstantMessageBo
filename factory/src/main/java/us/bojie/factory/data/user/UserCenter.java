package us.bojie.factory.data.user;

import us.bojie.factory.model.card.UserCard;

/**
 * Created by bojiejiang on 11/25/17.
 * 用户中心的基本定义
 */

public interface UserCenter {
    // 分发处理一堆用户卡片的信息，并更新到数据库
    void dispatch(UserCard... userCards);
}
