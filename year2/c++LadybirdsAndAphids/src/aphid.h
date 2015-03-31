#ifndef APHID_H_
#define APHID_H_

#include "creature.h"

class Ladybird;

class Aphid : public Creature {
  private:
    virtual bool willMove(Board *board);

  public:
    Aphid(int x, int y);
    static float moveProbability;
    static float killProbabllity;
    static float killModifier;
    static float matingProbability;
    virtual void doMove(Board *board);
    virtual void doInteraction(Aphid *aphid, Board *board);
    virtual void doInteraction(Ladybird *ladybird, Board *board);
};

#endif