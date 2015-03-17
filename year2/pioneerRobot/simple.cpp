#include <iostream>
#include <libplayerc++/playerc++.h>
#include <math.h>

void TurnToHeadding(float headding, PlayerCc::PlayerClient *robot, PlayerCc::Position2dProxy *playerClient){
  float origonalHeadding = PlayerCc::rtod(playerClient->GetYaw());
  std::cout << " Turning to headding " << headding << " starting from " << origonalHeadding << std::endl;
  float turnRate = 0;
  float newHeadding = 0;
  if(origonalHeadding > headding)
    turnRate = PlayerCc::dtor(-20);
  else turnRate = PlayerCc::dtor(20);
  
  do{
    robot->Read();
    newHeadding = PlayerCc::rtod(playerClient->GetYaw());
    std::cout << "Current Yaw " << newHeadding << " Turning to heading " << headding << std::endl;
    playerClient->SetSpeed(0.01, turnRate);
  } while(abs(newHeadding - headding) > 0.8);
  playerClient->SetSpeed(0, 0); //ALL STOP
}

void TurnBy(float degrees, PlayerCc::PlayerClient *robot, PlayerCc::Position2dProxy* playerClient){
  int adjustBy = PlayerCc::rtod(PlayerCc::dtor(degrees) + playerClient->GetYaw());
  adjustBy %= 360;
  std::cout << "Turning by " << degrees  << ", turning to headding " << adjustBy << std::endl;
  TurnToHeadding(adjustBy, robot, playerClient);
}

double DifferenceBetween(float firstX, float firstY, float secondX, float secondY){
  return sqrt(pow(firstX - secondX, 2) - pow(firstY - secondY, 2));
}

void GoBy(float distance, PlayerCc::PlayerClient *robot, PlayerCc::Position2dProxy *playerClient){
  robot->Read();

  float startingX = playerClient->GetXPos(),
        startingY = playerClient->GetYPos(),
        endingX, endingY;
        double difference;
 
  do{
    robot->Read();
    playerClient->SetSpeed(0.3, 0);
    endingX = playerClient->GetXPos();
    endingY = playerClient->GetYPos();
    difference = abs(DifferenceBetween(startingX, startingY, endingX, endingY) - distance);
    std::cout << "START: (" << startingX << "," << startingY << ")" << std::endl
              << "END  : (" << endingX << "," << endingY << ")" << std::endl
              << "I Think i have traveled " << difference << std::endl;
  } while (difference > 0.5);
  playerClient->SetSpeed(0, 0); //ALL STOP! 
}


int main(int argc, char *argv[])
{
  using namespace PlayerCc;
  std::cout.precision(15);
  PlayerClient    robot("lisa.islnet");
  Position2dProxy pp(&robot,0);

  pp.SetMotorEnable(true);

  robot.Read();
  GoBy(4, &robot, &pp);
  TurnBy(180, &robot, &pp);
  GoBy(4, &robot, &pp);
  pp.SetSpeed(0, 0);
}


