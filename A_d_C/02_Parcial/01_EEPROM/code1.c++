#include <EEPROM.h>
int pos=0;
void setup(){
  Serial.begin(9600);
  for(int i=0;i<1024;i++){
    EEPROM.write(i,i);
  }
}

void loop(){
  int val = EEPROM.read(pos);
  Serial.print(pos);
  Serial.print(" - ");
  Serial.println(val);
  if(++pos>=1024) pos=0;
  delay(500);
}