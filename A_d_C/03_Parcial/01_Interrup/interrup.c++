volatile boolean flag;
int i=0;

void isr () 
{
  digitalWrite(13, HIGH);
  delay(2000);
  digitalWrite(13, LOW);
}

void setup ()
{
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);

  Serial.begin(9600);

  attachInterrupt (digitalPinToInterrupt (2), isr, RISING);
}

void loop() {
  Serial.println(i);
  i++;
  delay(2000);
}