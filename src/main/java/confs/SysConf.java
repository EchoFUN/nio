package confs;

import java.util.HashMap;
import java.util.Map;

import static confs.Constant.*;

public class SysConf {

    // public static String build = PRODUCTION_ENV_FLAG;
    public static String build = DEVELOPMENT_ENV_FLAG;

    public static int WEB_PORT = 8001;

    public static String FRONTEND_ROOT = (build.equals(PRODUCTION_ENV_FLAG) ? "/frontend" : "/Users/xukai/Documents/workspace/iamunclexu4java");

    private static Map<String, String> databaseInfo = new HashMap<>();

    public static Map<String, String> fetchDatabaseInfo() {
        databaseInfo.put(URL, "jdbc:mysql://127.0.0.1:3306/yymg?useUnicode=true&characterEncoding=UTF-8");
        databaseInfo.put(USERNAME, "root");
        databaseInfo.put(PASSWORD, (build.equals(PRODUCTION_ENV_FLAG) ? "" : "123456"));

        databaseInfo.put(CONNECTION_POOL_SIZE, "100");
        return databaseInfo;
    }

    public static int PAGE_COUNT = 10;
}
