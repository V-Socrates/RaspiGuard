package Team.RG.RaspiGuard.SupportClasses;


public class Config {
    //JSON URL
    public static final String DATA_DOORSENSOR_URL = "http://165.227.44.224/ds.php";
    public static final String DATA_ACTIVITYLOG_URL = "http://165.227.44.224/al.php";

    //Tags used in the JSON String
    public static final String TAG_USERNAME = "username";
    public static final String TAG_SENSORNAME = "sensorname";
    public static final String TAG_LOCATION = "location";
    public static final String TAG_STATUS = "status";
    public static final String TAG_ALARMSTATE = "alarmstate";
    public static final String TAG_DATETIME = "datetime";
    public static final String TAG_ACTIVITY = "activity";

    //JSON array name
    public static final String JSON_ARRAY = "result";
}
