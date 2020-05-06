package modelControllerInterfaces;

import java.sql.Connection;

public interface ConnectionSwitched {
    public void switchConnection(Connection connection);
}
