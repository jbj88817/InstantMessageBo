package us.bojie.factory.model;

/**
 * Created by bojiejiang on 11/16/17.
 * 基础用户接口
 */

public interface Author {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPortrait();

    void setPortrait(String portrait);
}
