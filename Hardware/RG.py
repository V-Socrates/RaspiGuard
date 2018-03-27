import Adafruit_ADS1x15
import MySQLdb
import RPi.GPIO as IO
import time

##############################
#     VARIABLES/SETTINGS     #
##############################
#ADS1115 Settings
ADS = Adafruit_ADS1x15.ADS1115()
GAIN = 1

#Sensor Data
formattedData = [0] * 4

#Door Sensor
doorSensor = 14
IO.setmode(IO.BCM)
IO.setup(doorSensor, IO.IN, pull_up_down = IO.PUD_UP)

#SQL Database Information
SQL_Server      = "165.227.44.224"
SQL_User        = "user"
SQL_Pass        = "user"
SQL_Database    = "raspiguard"

#SQL Unit Information
SQL_Username    = "vivek"
SQL_Unitname    = "Prototype"

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

#Function To Print Sensor Data
def PrintSensorReadings():

    #Variables To Store Data
    rawData = [0] * 2
    #formattedData = [0] * 3

    #Reading Data
    for i in range(2):

        #Read the specified ADC channel using the previously set gain value.
        rawData[i] = float(ADS.read_adc(i, gain = GAIN))

        #Formatting Light Sensor Data
        if i == 0:
            formattedData[i] = rawData[i]

        #Formatting Moisture Sensor Data
        if i == 1:
            formattedData[i] = round(float((rawData[i] / 26500) * 100))

        if IO.input(doorSensor) == True:
            formattedData[2] = "Open"

        if IO.input(doorSensor) == False:
            formattedData[2] = "Closed"

        GetAlarmStatus()

    #Printing Formatted
    if formattedData[0] < 10000:
        print("\033[H\033[J")
        print(" ")
        print("\033[1;31;40m************************************************")
        print("\033[1;31;40m*\t\t\033[1;32;40m    RaspiGuard    \t\t\033[1;31;40m*")
        print("\033[1;31;40m*----------------------------------------------*")
        print("\033[1;31;40m* Lux:  \033[1;32;40m%7.2f\t\t\033[1;31;40mMoisture: \033[1;32;40m%04.2f\t\t\033[1;31;40m*" % (formattedData[0], formattedData[1]))
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m* Door: \033[1;32;40m%s\t\t\033[1;31;40mAlarm:    \033[1;32;40m%s\t\033[1;31;40m*" % (formattedData[2], formattedData[3]))
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m************************************************")

    if formattedData[0] > 9999:
        print("\033[H\033[J")
        print(" ")
        print("\033[1;31;40m************************************************")
        print("\033[1;31;40m*\t\t\033[1;32;40m    RaspiGuard    \t\t\033[1;31;40m*")
        print("\033[1;31;40m*----------------------------------------------*")
        print("\033[1;31;40m* Lux:  \033[1;32;40m%7.2f\t\033[1;31;40mMoisture: \033[1;32;40m%04.2f\t\t\033[1;31;40m*" % (formattedData[0], formattedData[1]))
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m* Door: \033[1;32;40m%s\t\t\033[1;31;40mAlarm:    \033[1;32;40m%s\t\033[1;31;40m*" % (formattedData[2], formattedData[3]))
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m*                                              *")
        print("\033[1;31;40m************************************************")
    return

#Get Alarm State
def GetAlarmStatus():

    #Opening Connection
    db = MySQLdb.connect(SQL_Server, SQL_User, SQL_Pass, SQL_Database)
    cursor = db.cursor()
    alarm = "SELECT dooralarmstate FROM units WHERE username = '%s' AND unitname = '%s' LIMIT 1" % (SQL_Username, SQL_Unitname)

    cursor.execute(alarm)
    data = cursor.fetchone()
    formattedData[3] = data
    db.close()
    return

################
#     MAIN     #
################
def main():

    counter = 0
    #Sensor Loop
    while True:
        PrintSensorReadings()

        if (counter % 6) == 0:
            UpdateSQLDatabase(formattedData)

        time.sleep(0.5)
        counter += 1
    return

main()
