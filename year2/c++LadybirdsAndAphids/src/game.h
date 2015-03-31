
#ifndef GAME_H_
#define GAME_H_

#include <string>
#include "board.h"

class Game{
  private:
    std::string simFile;
    std::string adhidsFile;
    std::string LadybirdFile;
    Board *currentGeneration;
    Board *nextGeneration;
    static int seed;
    static int generationNumber;

  public:
    bool loadSimFile(std::string filename);
    bool loadAphidsFile(std::string filename);
    bool loadLadybirdsFile(std::string filename);
    void run(int seed);
    static int getSeed();
    static int getGenerationNumber();
};

#endif
