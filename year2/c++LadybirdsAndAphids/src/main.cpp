#include <iostream>
#include <stdio.h>
#include <time.h>
#include "game.h"

int main(int argv, char **argc){
  Game game;
  
  std::string inputString;
  int seed = time(0);
  int status = true;
  std::string defaultConfFile = "./conf/aphidsAndLadybugs.conf";
  std::string defaultAphidFile = "./conf/aphids.conf";
  std::string defaultLadybirdFile = "./conf/ladybugs.conf";

  do{
    std::cout << "Please enter the sim file name (default '" << defaultConfFile << "')"
              << std::endl << "> ";
    std::getline(std::cin, inputString);
    if(inputString.empty()){
      inputString = defaultConfFile;
    }
  }while(!game.loadSimFile(inputString));

  do{
    std::cout << "Please enter the Aphids file name (default '" << defaultAphidFile << "')"
              << std::endl << "> ";
    std::getline(std::cin, inputString);
    if(inputString.empty()){
      inputString = defaultAphidFile;
    }
  }while(!game.loadAphidsFile(inputString));

  do{
    std::cout << "Please enter the Ladybird file name (default '" << defaultLadybirdFile << "')"
              << std::endl << "> ";
    std::getline(std::cin, inputString);
    if(inputString.empty()){
      inputString = defaultLadybirdFile;
    }
  }while(!game.loadLadybirdsFile(inputString));

  do{
    status = true;
    std::cout << "Please enter a seed (Or leave blank for random seed)" << std::endl << "> ";
    std::getline(std::cin, inputString);
    if(!inputString.empty()){
      status = sscanf(inputString.c_str(), "%d", &seed);
    }
  } while (!status);

  std::cout << "Using seed " << seed << std::endl;

  game.run(seed);
  return 0;
}
