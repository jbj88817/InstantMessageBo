package us.bojie.factory.data.message;

import us.bojie.factory.model.card.MessageCard;

/**
 * Created by bojiejiang on 11/25/17.
 * 消息中心，进行消息卡片的消费
 */

public interface MessageCenter {
    void dispatch(MessageCard... messageCards);
}
