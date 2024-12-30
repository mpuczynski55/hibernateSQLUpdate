package com.sqlupdate;

public abstract class AbstractObjectJPA extends ObjectJPA {

    public static final String COMPONENT_SQL_UPDATE_QUERY = "UPDATE COMPONENT SET ID = ID WHERE ID = ? AND KEY_1 = ? AND KEY_2 = ?";

}
