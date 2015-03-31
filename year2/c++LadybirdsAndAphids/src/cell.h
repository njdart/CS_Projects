#ifndef CELL_H_
#define CELL_H_

#include <iostream>
#include <list>
#include <vector>
#include "board.h"

class Aphid;
class Board;
class Creature;
class Ladybird;

class Cell{
  private:
    int x, y;
    std::vector<Aphid*> aphids;
    std::vector<Ladybird*> ladybirds;
    std::vector<Creature*> killedCreatures;
    std::vector<Aphid*> newAphids;
    std::vector<Ladybird*> newLadybirds;

  public:
    Cell(int x, int y);
    void addCreature(Aphid *creature, bool deferred = false);
    void addCreature(Ladybird *creature, bool deferred = false);
    int getAphidCount();
    int getLadybirdCount();
    void simulateMove(Board *nextGenerationBoard);
    void simulateInteractions(Board *board);
    void kill(Creature *creature);

    friend std::ostream& operator<<(std::ostream &out, const Cell &cell);
};

#endif