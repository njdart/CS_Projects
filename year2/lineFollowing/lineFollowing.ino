
#include <math.h>
#include <Servo.h>

int leftLDR = A2;
int middleLDR = A1;
int rightLDR = A0;
int leftMotor = 6;
int rightMotor = 5;
int irLED = 3;
int irReceiver = 2;
int redLED = 13;
int irTone = 38000;  // 38kHz

float leftLDRWeight = 1.0;
float rightLDRWeight = -1.0;

float leftMotorStop = 90;
float rightMotorStop = 87;  //This motor needs to be inverted
float baseSpeed = 20;

Servo leftServo;
Servo rightServo;

long irStartTime;
long irDuration = 200; // ms
long irTimeout = 100;   // ms
int canCheckIR = true;
int irResponse = 1;

void setup() {
  Serial.begin(9600);
  pinMode(leftLDR, INPUT);
  pinMode(middleLDR, INPUT);
  pinMode(rightLDR, INPUT);
  pinMode(redLED, OUTPUT);
  pinMode(irReceiver, INPUT);
  pinMode(irLED, OUTPUT);
  
  leftServo.attach(leftMotor);
  rightServo.attach(rightMotor);
  
  leftServo.write(leftMotorStop);
  rightServo.write(rightMotorStop);
  irStartTime = 1;
}

/** NO LONGER USED **/
float getNormalisedLinePosition(){
  int left = 1023 - analogRead(leftLDR);
  int middle = 1023 - analogRead(middleLDR);
  int right = 1023 - analogRead(rightLDR);
  
  Serial.print("Raw: ");
  Serial.print(left);
  Serial.print(", ");
  Serial.print(middle);
  Serial.print(", ");
  Serial.println(right);
  
  float average = (left + middle + right) / 3.00f;
  
  Serial.print("Average: ");
  Serial.println(average);
  
  Serial.print("Over Line Bool: ");
  Serial.print(left > average);
  Serial.print(", ");
  Serial.print(middle > average);
  Serial.print(", ");
  Serial.print(right > average);
  Serial.println(", ");
  
  if((left > average && right > average) || middle > average || average > 700){
    return 0.0f;
  }
  
  return 10.0f;
  
  float leftSet = (left * leftLDRWeight) + (middle * rightLDRWeight);
  float leftSetAverage = abs(left + middle) / 2.0f;
  float rightSet = abs((middle * leftLDRWeight) + (right * rightLDRWeight));
  float rightSetAverage = abs(middle + right) / 2.0f;
  float weightedAverage = (rightSetAverage + leftSetAverage) / 2.0f;
  
  Serial.print("Left Set => ");
  Serial.print(leftSet);
  Serial.print(", avg => ");
  Serial.print(leftSetAverage);
  Serial.print("; Right Set => ");
  Serial.print(rightSet);
  Serial.print(", avg => ");
  Serial.println(rightSetAverage);
  
  float normalisedPosition = ((leftSet * leftLDRWeight) + (rightSet * rightLDRWeight)) / weightedAverage;
  return normalisedPosition;
}

void loop() {
  long loopTime = millis();
  
  if((loopTime - irStartTime) > (irTimeout + irDuration)){
    tone(irLED, irTone);
    irStartTime = loopTime;
    canCheckIR = true;
  }
  
  if((loopTime - irStartTime) > irDuration){
    noTone(irLED);
    canCheckIR = false;
  }
  
  if(canCheckIR){
    irResponse = digitalRead(irReceiver);  //0 for something there, 1 for not
    Serial.println("Checking for response");
  }
  
  Serial.println(irResponse);
  
  if(irResponse){
    digitalWrite(redLED, LOW);
    
    int left = 1023 - analogRead(leftLDR);
    int middle = 1023 - analogRead(middleLDR);
    int right = 1023 - analogRead(rightLDR);
    
    Serial.print("Raw: ");
    Serial.print(left);
    Serial.print(", ");
    Serial.print(middle);
    Serial.print(", ");
    Serial.println(right);
    
    float average = (left + middle + right) / 3.00f;
    
    Serial.print("Average: ");
    Serial.println(average);
    
    Serial.print("Over Line Bool: ");
    Serial.print(left > average);
    Serial.print(", ");
    Serial.print(middle > average);
    Serial.print(", ");
    Serial.print(right > average);
    Serial.println(", ");
  
    if(left > average && right > average){
      //continue strait 
      leftServo.write(leftMotorStop + baseSpeed);
      rightServo.write(rightMotorStop - baseSpeed);
    } else if(middle > average){
      //we're between two lines? continue
      Serial.write("Between two lines?");
      leftServo.write(leftMotorStop + baseSpeed);
      rightServo.write(rightMotorStop - baseSpeed);
    } else if(left > average){
      //the line is on the left
      leftServo.write(leftMotorStop - baseSpeed);
      rightServo.write(rightMotorStop - baseSpeed);
    } else if(right > average){
      //the line is on the right
      leftServo.write(leftMotorStop + baseSpeed);
      rightServo.write(rightMotorStop + baseSpeed);
    } else {
      leftServo.write(leftMotorStop);
      rightServo.write(rightMotorStop);
      Serial.println("ERROR?!?"); 
    }
  
    //float linePosition = getNormalisedLinePosition();
    //Serial.println(linePosition);
    
    //float leftMotorValue = leftMotorStop - 5 - (90 * linePosition);
    //float rightMotorValue = rightMotorStop + 5 + (90 * linePosition);
   
    //Serial.print(leftMotorValue);
    //Serial.print(", ");
    //Serial.println(rightMotorValue);
    //
    //leftServo.write(100);
    //rightServo.write(80); 
      
  } else {
    digitalWrite(redLED, HIGH);
    leftServo.write(leftMotorStop);
    rightServo.write(rightMotorStop);
  }
}

