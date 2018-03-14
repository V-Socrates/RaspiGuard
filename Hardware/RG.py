import Adafruit_ADS1x15, RPi.GPIO as IO, time

#ADS1115 Settings
ADS = Adafruit_ADS1x15.ADS1115()
GAIN = 1

#Door Sensor
doorSensor = 4
IO.setmode(IO.BCM)
IO.setup(doorSensor, IO.IN, pull_up_down = IO.PUD_UP)

#Function to print sensor data
def PrintSensorReadings():

    #Variables To Store Data
    rawData = [0] * 2
    formattedData = [0] * 3

    #Reading Data
    for i in range(2):

        # Read the specified ADC channel using the previously set gain value.
        rawData[i] = float(ADS.read_adc(i, gain=GAIN))

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

    #Printing Formatted
    print("\033[H\033[J")
    print("*****************************************************************")
    print("* RaspiGuard *")
    print("*****************************************************************")
    print("Lux:\t%7.2f\tMoisture:\t%04.2f" % (formattedData[0], formattedData[1]))
    print("Door:\t%s\t\tAlarm:" % (formattedData[2]))
    print("*****************************************************************")
#End Of PrintSensorReadings() Function

#Sensor Loop
while True:
    PrintSensorReadings()
    #Half Second Pause
    time.sleep(0.5)
