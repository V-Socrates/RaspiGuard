import Adafruit_ADS1x15
import datetime
import MySQLdb
import RPi.GPIO as IO
import time
import webbrowser

##############################
#     VARIABLES/SETTINGS     #
##############################
#ADS1115 Settings
ADS = Adafruit_ADS1x15.ADS1115()
GAIN = 1

#Sensor Data
formattedData = [0] * 4

#Door Sensor
doorSensor = 4
IO.setmode(IO.BCM)
IO.setup(doorSensor, IO.IN, pull_up_down = IO.PUD_UP)

#SQL Database Information
SQL_Server      = "Fill In"
SQL_User        = "Fill In"
SQL_Pass        = "Fill In"
SQL_Database    = "Fill In"

#SQL Unit Information
SQL_Username    = "Fill In"
SQL_Unitname    = "Fill In"

#####################
#     FUNCTIONS     #
#####################
#Update SQL Database
def UpdateSQLDatabase(readings):

    #Opening Connection
    db = MySQLdb.connect(SQL_Server, SQL_User, SQL_Pass, SQL_Database)
    cursor = db.cursor()
    update = "UPDATE units SET lightlevel = '%s', moisturelevel = '%s', doorstatus = '%s' WHERE username = '%s' AND unitname = '%s'" % (readings[0], readings[1], readings[2], SQL_Username, SQL_Unitname)

    try:
        cursor.execute(update)
        db.commit()

    except:
        db.rollback()

    db.close()
    return

#Activity Log Entry
def ActivityLog(readings):

	from datetime import datetime
	curtime = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

	#Opening Connection
	db = MySQLdb.connect(SQL_Server, SQL_User, SQL_Pass, SQL_Database)
	cursor = db.cursor()

	try:
		cursor.execute("""INSERT INTO activitylog (datetime, username, sensorname, activity, moisturelevel, lightlevel) VALUES (%s, %s, %s, "door " %s, %s, %s) """, (curtime, SQL_Username, SQL_Unitname, readings[2].lower(), readings[1], readings[0]))
		db.commit()

	except:
		db.rollback()

	db.close()
	return

#Function To Print Sensor Data
def PrintSensorReadings():

    #Variables To Store Data
    rawData = [0] * 2

    #Reading Data
    for i in range(2):

        #Read the specified ADC channel using the previously set gain value.
        rawData[i] = float(ADS.read_adc(i, gain = GAIN))
        GetAlarmStatus()

        #Formatting Light Sensor Data
        if i == 0:
            formattedData[i] = abs(round(float((rawData[i] / 32767) * 255), 0))

        #Formatting Moisture Sensor Data
        if i == 1:
            formattedData[i] = abs(round(float((rawData[i] / 26500) * 100), 0))

    #Formatting Door Sensor Data
    if IO.input(doorSensor) == True:
        if formattedData[2] != "Opened":
            formattedData[2] = "Opened"
            ActivityLog(formattedData)

    if IO.input(doorSensor) == False:
        if formattedData[2] != "Closed":
            formattedData[2] = "Closed"
            ActivityLog(formattedData)

    #Printing Formatted
    print("\033[H\033[J")
    print(" ")
    print("\033[1;31;40m*****************************************")
    print("\033[1;31;40m*\t\t\033[1;32;40m RaspiGuard \t\t\033[1;31;40m*")
    print("\033[1;31;40m*---------------------------------------*")
    print("\033[1;31;40m* Lux: \033[1;32;40m%7.2f\t\t\033[1;31;40mMoisture:\033[1;32;40m%04.2f%%\t\033[1;31;40m*" % (formattedData[0], formattedData[1]))
    print("\033[1;31;40m*                                       *")
    print("\033[1;31;40m*                                       *")
    #print("\033[1;31;40m*                                       *")
    print("\033[1;31;40m* Door:  \033[1;32;40m%s\t\t\033[1;31;40mAlarm:\t\033[1;32;40m %s\t\033[1;31;40m*" % (formattedData[2], formattedData[3]))
    print("\033[1;31;40m*                                       *")
    print("\033[1;31;40m*                                       *")
    #print("\033[1;31;40m*                                       *")
    print("\033[1;31;40m*****************************************")

#Get Alarm State
def GetAlarmStatus():

    #Opening Connection
    db = MySQLdb.connect(SQL_Server, SQL_User, SQL_Pass, SQL_Database)
    cursor = db.cursor()
    alarm = "SELECT dooralarmstate FROM units WHERE username = '%s' AND unitname = '%s' LIMIT 1" % (SQL_Username, SQL_Unitname)

    cursor.execute(alarm)
    data = cursor.fetchone()

    #Formatting Alarm Status Data
    if data[0] == "on":
        formattedData[3] = "On"

    elif data[0] == "off":
        formattedData[3] = "Off"

    else:
        formattedData[3] = data[3]

    db.close()
    return

################
#     MAIN     #
################
def main():

    #Timer Variables
    Update = 0
    Log = 0

    #Sensor Loop
    while True:

        #Getting And Printing Readings
        PrintSensorReadings()

        #Update SQL Database Every 3 Seconds
        if Update  == 6:
            UpdateSQLDatabase(formattedData)
            Update = 0

        #log Sensor Details Every Minute
        if Log == 30:
            ActivityLog(formattedData)
            Log = 0

        #Updating Timers
        Update += 1
        Log += 1
        time.sleep(0.5)
    return

main()
