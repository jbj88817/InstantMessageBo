package us.bojie.factory.data.group;

import us.bojie.factory.model.card.GroupCard;
import us.bojie.factory.model.card.GroupMemberCard;

/**
 * Created by bojiejiang on 11/25/17.
 * 群中心的接口定义
 */

public interface GroupCenter {
    // 群卡片的处理
    void dispatch(GroupCard... cards);

    // 群成员的处理
    void dispatch(GroupMemberCard... cards);
}
