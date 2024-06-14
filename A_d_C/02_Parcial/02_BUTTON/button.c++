const int inputPin = 7;
int input7 = 0;
int lastState = 0;

void setup() {
  pinMode(inputPin, INPUT);
  Serial.begin(9600);
}

void loop() {
input7 = digitalRead(inputPin);
if ( lastState == LOW && input7 == HIGH )
{
  Serial.println("1");  
}
else if ( lastState == HIGH && input7 == LOW )
{
  Serial.println(0);
}
  lastState = input7;
  delay(100);
}