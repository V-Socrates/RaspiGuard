# RaspiGuard
Vivek Socrates
Karel Tutsu
Heakeme Williams

# RASPIGUARD Board For Home Monitoring Build Instrutions

### Background Knowledge
RaspiGuard is a home security/surveillance system that is used to monitor a room remotely using our android application. The current operational functionality is door and moisture sensors. We are currently working on the functionality of light sensors as well as various other sensors. Installing a home security system can be costly, but needing one and not having one can cost you more. Fortunately, you will not have that dilemma with RaspiGuard. RaspiGuard is a cost-effective and easy to install security system that you can easily setup and deploy on your own. 

### Materials\Components Needed 
Click [Here](https://www.adafruit.com/) To Buy Parts

Components	   |                                Cost
------------ |  ---------------------------------------
 Raspberry Pi 3 Model B	 |                     $48.98
 Raspberry Pi Power Adapter	|                  $16.95
 ADATA Premier 8GB microSDHC UHS-I Class 10	|  $8.99
ADATA microReader Ver.3 microSDHC	          |  $4.99
adafruit PiTFT Plus 480x320 3.5"	          |  $44.95
adafruit ADS1115	                          |  $14.95
SparkFun Soil Moisture Sensor	              |  $5.95
 Photo Cell (CdS Photoresistor)	            |  $0.95
 Magnetic Contact Switchs	                  |  $3.95
 Piezo Buzzer - PS1240	                      |  $1.50
 GPIO 2x13pin Ribbon Cable	                  |  $2.95
 Male To Male 2x13pin Header	                |  $1.75
 Jumper Wire Cables	                        |  $1.95
Raspbian Stretch with Desktop (OS)	        |  $0.00
TAX	                                        | $20.65
TOTAL	                                      | $179.46

### Boards Needed 

Designing the boards can be quite an annoying process, however, We have designed these boards and they are available for download if needed. 
* To download the RaspiGuard board file in Eagle, please click [HERE](https://github.com/V-Socrates/RaspiGuard/blob/master/Hardware/RaspiGuardProtoBoard.zip)
![Image of the RaspiGuard Board](https://github.com/V-Socrates/RaspiGuard/blob/master/Images/RaspiguardBoard.JPG)
* To download the RaspiGuard Schematic in Fritzing, please click [HERE](https://github.com/V-Socrates/RaspiGuard/blob/master/Hardware/RaspiGuard%20Schematic.fzz)
![Image of the RaspiGuard Board](https://github.com/V-Socrates/RaspiGuard/blob/master/Images/FritzingSchematic.JPG)

### Components Needed For Testing

* Raspberry Pi 
* Keyboard
* Monitor

### Implementing Everything Together / Testing.

![Image of the RaspiGuard Board](https://github.com/V-Socrates/RaspiGuard/blob/master/Images/PCB.JPG)

1.	Build the above circuit using the GPIO pinout found on the PiTFT. The picture above shows a Raspberry Pi Model B which the pinout mirrors. It is recommended to use a ribbon cable to connect the above circuit for testing and easy modifications.
2.	Place the PiTFT on to the your Raspberry Pi 3 B.
3.	Download the Jesse Pitft image from adafruit (https://s3.amazonaws.com/adafruit-raspberry-pi/2016-11-08-pitft-35r.zip) and flash the image onto you microSD card. Minimum 8GB.
4.	Insert your SD card into your Raspberry Pi 3 B and power on your Pi.
5.	After powering on connect to the internet via Ethernet cable or Wifi.
6.	Execute the commands below into a terminal window. Default sudo password is raspberry.
* cd ~
* sudo apt-get update
* sudo apt-get install build-essential python-dev python-smbus git
* cd ~
* git clone https://github.com/adafruit/Adafruit_Python_ADS1x15.git
* cd Adafruit_Python_ADS1x15
* sudo python setup.py install
* sudo apt-get install build-essential python-dev python-smbus python-pip
* sudo pip install adafruit-ads1x15
* cd ~/Adafruit_Python_ADS1x15/examples
*wget https://raw.githubusercontent.com/V-Socrates/RaspiGuard/master/Hardware/RG.py

7.	Execute the RG.py python script using “sudo python RG.py” to start your RaspiGuard unit.



