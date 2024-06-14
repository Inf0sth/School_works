volatile boolean flag;

// Interrupt Service Routine (ISR)
void isr () 
{
  flag = true;
} // end of isr

void setup ()
{
  attachInterrupt (digitalPinToInterrupt (2), isr, CHANGE); // attach interrupt handler
} // end of setup

void loop()
{
  if (flag)
    {
      //interrupt has occurred
    }
} // end of loop