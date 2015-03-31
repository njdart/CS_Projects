#ifndef LADYBIRD_H_
#define LADYBIRD_H_

#include "creature.h"

class Aphid;

class Ladybird : public Creature {
  private:
    int facing;

  protected:
    virtual bool willMove(Board *board);

  public:
    Ladybird(int x, int y);
    static float moveProbability;
    static float directionProbability;
    static float killProbabllity;
    static float matingProbability;
    virtual void doMove(Board *board);
    virtual void doInteraction(Aphid *aphid, Board *board);
    virtual void doInteraction(Ladybird *ladybird, Board *board);
};

#endif