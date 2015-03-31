#include "creature.h"
#include <iostream>
#include "direction.h"

Creature::Creature(int x, int y){
  this->x = x;
  this->y = y;
}

int Creature::getX(){
  return this->x;
}

int Creature::getY(){
  return this->y;
}