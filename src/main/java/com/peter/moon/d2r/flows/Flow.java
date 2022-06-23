package com.peter.moon.d2r.flows;

import java.io.IOException;
import java.sql.SQLException;

public interface Flow {
    Object process() throws SQLException, IOException;
}
