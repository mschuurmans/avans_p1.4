 char statusLed = 0;
 int val = 0;
 int delayTime = 100;
 int Recv = 0;
 int strobeTime = 1000;


#define REDPIN 6
#define GREENPIN 5
#define BLUEPIN 3
 
void setup()
{
  Serial.begin(9600);
  pinMode(REDPIN, OUTPUT);
  pinMode(GREENPIN, OUTPUT);
  pinMode(BLUEPIN, OUTPUT);
  Serial.setTimeout(200);
}

void loop()
{
  if (Serial.available() > 0) 
  {
    statusLed = Serial.read();
  }
  setLed();
//  Serial.print(statusLed);
}
void setLed()
{
  if (statusLed <=100)
  {
    analogWrite(REDPIN, 255 - statusLed*2);
    analogWrite(GREENPIN, statusLed*2);
    analogWrite(BLUEPIN, 0);
    strobeTime = statusLed*10+10;
    strobeDelay();
  }
  else if (statusLed == 101)
  {
    analogWrite(REDPIN, 0);
    analogWrite(GREENPIN, 255);
    analogWrite(BLUEPIN, 0);
  }
  else if (statusLed == 102)
  {
    analogWrite(REDPIN, 0);
    analogWrite(GREENPIN, 0);
    analogWrite(BLUEPIN, 255);
  }
  
  else
  {
    analogWrite(REDPIN, 0);
    analogWrite(GREENPIN, 0);
    analogWrite(BLUEPIN, 0);
  }
}

void strobeDelay()
{
  delay(strobeTime);
  analogWrite(REDPIN, 0);
  analogWrite(GREENPIN, 0);
  analogWrite(BLUEPIN, 0);
  delay(strobeTime);
}

