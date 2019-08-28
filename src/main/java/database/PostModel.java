package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostModel {
    private static Logger LOGGER = LoggerFactory.getLogger(PostModel.class);

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Map<String, String> fetchPostByID(int id) {
        List<Map<String, String>> posts = new ArrayList();

        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();

            // PostModel Data .
            resultSet = statement.executeQuery("select id, title, content, date, author from post where id = " + id + " and visiable = 1");
            while (resultSet.next()) {
                Map<String, String> fieldDataSet = new HashMap<>();
                fieldDataSet.put("id", String.valueOf(resultSet.getInt("id")));
                fieldDataSet.put("title", resultSet.getString("title"));
                fieldDataSet.put("content", resultSet.getString("content"));
                fieldDataSet.put("date", resultSet.getString("date"));
                fieldDataSet.put("author", resultSet.getString("author"));
                posts.add(fieldDataSet);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    DBUtils.releaseConnection(connection);
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        if (posts.size() == 0) {
            return null;
        }
        return posts.get(0);
    }
}
