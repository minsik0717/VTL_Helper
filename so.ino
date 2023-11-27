#include <Wire.h>
#include <SPI.h>
#include <Adafruit_BMP280.h>

struct Sensor
{
  float alt;
};

Sensor sensor;

float altitude = 0;
float ALTavg = 0;

Adafruit_BMP280 bmp1; // use I2C interface
Adafruit_Sensor *bmp_temp1 = bmp1.getTemperatureSensor();
Adafruit_Sensor *bmp_pressure1 = bmp1.getPressureSensor();

void setup() {
  Serial.begin(115200);
  Wire.begin();
  delay(2000);
  settingBMP();
  calibrationBMP();
}

void loop() {
  getAltitude();
  Serial.print(sensor.alt - ALTavg);
  Serial.println(" m");
}

void getAltitude()
{
  sensors_event_t temp_event1, pressure_event1, altitude_event1 ;
  bmp_temp1->getEvent(&temp_event1);
  bmp_pressure1->getEvent(&pressure_event1);
  float temperature = temp_event1.temperature;
  float pressure = pressure_event1.pressure;
  altitude = bmp1.readAltitude(1013.25);
  sensor.alt = altitude;
}



void settingBMP()
{
  while ( !Serial ) delay(100);   // wait for native usb
  Serial.println(F("BMP280 Sensor event test"));
  
  unsigned status;
  status = bmp1.begin(0x76);
  if (!status) {
    Serial.println(F("Could not find a valid BMP280 sensor, check wiring or "
                     "try a different address!"));
    Serial.print("SensorID was: 0x"); Serial.println(bmp1.sensorID(), 16);
    Serial.print("        ID of 0xFF probably means a bad address, a BMP 180 or BMP 085\n");
    Serial.print("   ID of 0x56-0x58 represents a BMP 280,\n");
    Serial.print("        ID of 0x60 represents a BME 280.\n");
    Serial.print("        ID of 0x61 represents a BME 680.\n");
    while (1) delay(10);

    bmp1.setSampling(Adafruit_BMP280::MODE_NORMAL,   // Normal 모드로 설정
                     Adafruit_BMP280::SAMPLING_X2,   // 온도 해상도 17-bit (x2)
                     Adafruit_BMP280::SAMPLING_X16,  // 압력 해상도 20-bit (x16)
                     Adafruit_BMP280::FILTER_X16,    // IIR 필터 샘플링 수 16
                     Adafruit_BMP280::STANDBY_MS_500
                    );

    bmp_temp1->printSensorDetails();
  }
}

void calibrationBMP()
{
  for (int i = 0; i < 1000; i++){
    sensors_event_t temp_event1, pressure_event1, altitude_event1 ;
    bmp_temp1->getEvent(&temp_event1);
    bmp_pressure1->getEvent(&pressure_event1);
    float temperature = temp_event1.temperature;
    float pressure = pressure_event1.pressure;
    altitude = bmp1.readAltitude(1013.25);
    ALTavg += altitude;
  }
  ALTavg = ALTavg / 1000;
}
