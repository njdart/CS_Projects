#include <iostream>
#include "direction.h"
#include "aphid.h"
#include "creature.h"
#include "board.h"

float Aphid::moveProbability;
float Aphid::killProbabllity;
float Aphid::killModifier;
float Aphid::matingProbability;

Aphid::Aphid(int x, int y) : Creature(x, y){ }

bool Aphid::willMove(Board *board){
  return rand()%100 <= moveProbability*100;
}

void Aphid::doMove(Board *board){
  if(willMove(board)){
    std::pair<int, int> direction;
    do{
      direction = Direction::Directions[rand()%8];
    } while(this->x + direction.first < 0 ||
            this->x + direction.first >= board->getWidth() ||
            this->y + direction.second < 0 ||
            this->y + direction.second >= board->getHeight());

    this->x += direction.first;
    this->y += direction.second;
  }

  board->getCell(this->x, this->y).addCreature(this);
  
  //reset for interactions
  this->hasAttacked = false;
  this->hasMated = false;
}

void Aphid::doInteraction(Aphid *aphid, Board *board){
  if(aphid->hasMated || this->hasMated){
    return;
  } else if(rand()%100 <= matingProbability*100) {
    this->hasMated = true;
    aphid->hasMated = true;
    board->getCell(this->x, this->y).addCreature(new Aphid(this->x, this->y), true);
  }
}

void Aphid::doInteraction(Ladybird *ladybird, Board *board){
  if(rand()%100 <= killProbabllity){
    int aphids = board->getCell(this->x, this->y).getAphidCount();
    float killProb = killProbabllity + aphids*killModifier;
    if(rand()%100 <= killProb*100){
      board->getCell(this->x, this->y).kill((Creature*)ladybird);
      this->hasAttacked = true;
    }
  }
}
