#include <iostream>
#include "ladybird.h"
#include "creature.h"
#include "board.h"
#include "direction.h"
#include "cell.h"

float Ladybird::moveProbability;
float Ladybird::directionProbability;
float Ladybird::killProbabllity;
float Ladybird::matingProbability;

Ladybird::Ladybird(int x, int y) : Creature(x, y){
  this->facing = rand() % 8;
}

bool Ladybird::willMove(Board *board){
  //if the ladybird can only move into a wall, turn it around
  if((this->y == 0 && this->facing == 0) ||                                                          //Top and facing NORTH
      (this->x >= board->getWidth() - 1 && this->y == 0 && this->facing == 1) ||                     //Top Right and facing NORTH EAST
      (this->x == board->getWidth() - 1 && this->facing == 2) ||                                     //Right and facing EAST
      (this->x >= board->getWidth() - 1 && this->y >= board->getHeight() - 1 && this->facing == 3) ||//Bottom Right and facing SOUTH EAST
      (this->y == board->getHeight() - 1 && this->facing == 4) ||                                    //Bottom and facing SOUTH
      (this->x == 0 && this->y >= board->getHeight() - 1 && this->facing == 5) ||                    //Bottom Left and facing SOUTH WEST
      (this->x == 0 && this->facing == 6) ||                                                         //Left and facing WEST
      (this->x == 0 && this->y == 0 && this->facing == 7)) {                                         //Top Left and facing NORTH WEST
    this->facing = (this->facing + 4) % 8;
  }

  return rand()%100 <= moveProbability*100;
}

void Ladybird::doMove(Board *board){
  if(willMove(board)){
    // can turn one "increment" clockwise or counderclockwise, ie add -1<=x<=1
    std::pair<int, int> direction;
    do{
      this->facing = (this->facing + ((rand() % 3) - 1)) % 8;
      if(this->facing < 0){
        this->facing = 8 + this->facing;
      }
      direction = Direction::Directions[this->facing];
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

void Ladybird::doInteraction(Aphid *aphid, Board *board){
  if(this->hasAttacked){
    return;
  }
  if(rand()%100 <= Ladybird::killProbabllity*100){
    board->getCell(this->x, this->y).kill((Creature*)aphid);
    this->hasAttacked = true;
  }
}

void Ladybird::doInteraction(Ladybird *ladybird, Board *board) {
  if(this->hasMated || ladybird->hasMated){
    return;
  } else if(rand()%100 <= matingProbability*100){
      this->hasMated = true;
      ladybird->hasMated = true;
      board->getCell(this->x, this->y).addCreature(new Ladybird(this->x, this->y), true);
  }
}