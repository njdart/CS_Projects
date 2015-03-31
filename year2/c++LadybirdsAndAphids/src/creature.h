#ifndef CREATURE_H_
#define CREATURE_H_

#include <iostream>
#include "board.h"

class Board;
class Aphid;
class Ladybird;

class Creature {
  protected:
    int x, y;
    Creature(int x, int y);
    virtual bool willMove(Board *board) = 0;
    bool hasAttacked = false;
    bool hasMated = false;

  public:
    int getX();
    int getY();
    virtual void doMove(Board *board) = 0;
    virtual void doInteraction(Aphid *aphid, Board *board) = 0;
    virtual void doInteraction(Ladybird *ladybird, Board *board) = 0;
};

#endif