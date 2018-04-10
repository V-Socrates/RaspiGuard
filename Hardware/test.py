import Adafruit_ADS1x15
import datetime
import MySQLdb
import RPi.GPIO as IO
import time
import webbrowser
#SQL Database Information
SQL_Server      = "165.227.44.224"
SQL_User        = "user"
SQL_Pass        = "user"
SQL_Database    = "raspiguard"

#SQL Unit Information
SQL_Username    = "vivek"
SQL_Unitname    = "Prototype"

#Get Alarm State
def GetAlarmStatus():

    #Opening Connection
    db = MySQLdb.connect(SQL_Server, SQL_User, SQL_Pass, SQL_Database)
    cursor = db.cursor()
    alarm = "SELECT dooralarmstate FROM units WHERE username = '%s' AND unitname = '%s' LIMIT 1" % (SQL_Username, SQL_Unitname)

    cursor.execute(alarm)
    data = cursor.fetchone()
    print (data[0])
    db.close()
    return

GetAlarmStatus()
