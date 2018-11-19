//#include <SoftwareSerial.h> 
 
//SoftwareSerial ModBluetooth(2, 3); // RX | TX 

//Ultrasonico1
long dis;
long tiem;
int distancia=0;

//Ultrasonico2
int distancia2=0;


int Echo=9;   //devuelve valor
int Trig=8;  //envia valor

int Audio=11;

int Perdido=51;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  pinMode(Trig, OUTPUT);
  pinMode(Echo, INPUT);

  pinMode(Audio, OUTPUT);
  digitalWrite(Audio,LOW); 

  pinMode(Perdido, INPUT);
}

void loop() {
        LeerUltrasonico1();   
        AudioAlarma();  
        delay(700);
}
void AudioAlarma(){  
  if((distancia>0 && distancia <=60) || distancia>=2500){    
      digitalWrite(Audio,HIGH);
      Serial.print(dis);    //Enviamos distancia del objeto encontrado.  
      //ModBluetooth.print(dis); 
      //ModBluetooth.print("#");            
      Serial.print("#");
  }else{
    digitalWrite(Audio,LOW);             
    Serial.print("apaga#");
  }

}
void LeerUltrasonico1(){ 
  digitalWrite(Trig,LOW);//recibimiento del pulso.
  
  delayMicroseconds(15);
  digitalWrite(Trig, HIGH);//envió del pulso.
  
  delayMicroseconds(20);
  tiem=pulseIn(Echo, HIGH);//fórmula para medir el pulso entrante.
  dis= long(0.017*tiem);//fórmula para calcular la distancia del sensor ultrasónico.
  
  distancia=dis;  
  delay(100);
}

