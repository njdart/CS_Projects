#include <iostream>
#include <libplayerc++/playerc++.h>
#include <math.h>

int main(int argc, char *argv[])
{
  using namespace PlayerCc;
  std::cout.precision(15);
  PlayerClient    robot("lisa.islnet");
  SonarProxy      sp(&robot,0);
  Position2dProxy pp(&robot,0);

  pp.SetMotorEnable(true);

  for(;;)
  {
    robot.Read();
    int minimumObservedDist = 1000;
    int closestSensor = 3;
    std::cout << "Scanning" << std::endl;
    for(int i = 0; i < 16; i++){
      if(sp[i] < minimumObservedDist){
        minimumObservedDist = sp[i];
        closestSensor = i;
      }
    }
    
    robot.Read();
    if(sp[3] < 0.5 || sp[4] < 0.5){
      pp.SetSpeed(0, 0);
      std::cout << "Too Close, Stopping" << std::endl;  
    } else if (closestSensor == 3 || closestSensor == 4) {
      robot.Read();
      pp.SetSpeed(0.5, 0);
      std::cout << "Going forward" << std::endl;
    } else if(closestSensor >= 0 && closestSensor <= 2 || closestSensor >= 12 && closestSensor <= 15) {
      //turn left untill we are pointing at it
      std::cout << "Turning left" << std::endl;
      pp.SetSpeed(0.1, 0.6);
    } else if(closestSensor >= 5 && closestSensor <= 11) {
      //turn right untill we are pointing at it
      std::cout << "Turning right" << std::endl;
      pp.SetSpeed(0.1, -0.6);
    } else {
      std::cout << "wat";
      pp.SetSpeed(0, 0);
      exit(1);
    }
  }
}


