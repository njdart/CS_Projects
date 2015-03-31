#include <iostream>
#include <string>
#include <fstream>
#include <stdlib.h>
#include <unistd.h>
#include "game.h"
#include "aphid.h"
#include "ladybird.h"
#include "board.h"

int Game::seed;
int Game::generationNumber = 1;

bool Game::loadSimFile(std::string filename){
  std::ifstream inputStream(filename.c_str());

  if(inputStream == NULL) {
    return false;  
  }

  //save the sim file name
  this->simFile = filename;

  //read the grid size
  std::string gridSize;
  int width, height;
  inputStream >> width >> height;

  //then set boards and populate them
  this->currentGeneration = new Board(width, height);
  this->nextGeneration = new Board(width, height);

  int x, y;

  int aphidCount;
  inputStream >> aphidCount;

  for(int i = 0; i < aphidCount; i++){
    inputStream >> x >> y;
    this->currentGeneration->getCell(x, y).addCreature(new Aphid(x, y));
  }
  
  int ladybirdCount;
  inputStream >> ladybirdCount;

  for(int i = 0; i < ladybirdCount; i++){
    inputStream >> x >> y;
    this->currentGeneration->getCell(x, y).addCreature(new Ladybird(x, y));
  }
  inputStream.close();
  return true;
}

bool Game::loadAphidsFile(std::string filename){
  std::ifstream inputStream(filename.c_str());

  if(inputStream == NULL){
    return false;
  }

  inputStream >> Aphid::moveProbability;
  inputStream >> Aphid::killProbabllity;
  inputStream >> Aphid::killModifier;
  inputStream >> Aphid::matingProbability;
  inputStream.close();

  return true;
}

bool Game::loadLadybirdsFile(std::string filename){
  //std::cout << *filename << std::endl;
  std::ifstream inputStream(filename.c_str());

  if(inputStream == NULL){
    return false;
  }

  inputStream >> Ladybird::moveProbability;
  inputStream >> Ladybird::directionProbability;
  inputStream >> Ladybird::killProbabllity;
  inputStream >> Ladybird::matingProbability;
  inputStream.close();

  return true;
}

void Game::run(int seed){
  //Seed the random generator
  srand(seed);
  Game::seed = seed;

  std::cout << *currentGeneration;

  while(getchar() != 'q' && 
         this->currentGeneration->getAphidCount() != 0 &&
         this->currentGeneration->getLadybirdCount() != 0){ 
    currentGeneration = currentGeneration->simulateMove()->simulateInteractions();
    std::cout << *currentGeneration;
    ++generationNumber;
  }
}

int Game::getSeed(){
  return Game::seed;
}

int Game::getGenerationNumber(){
  return Game::generationNumber;
}


