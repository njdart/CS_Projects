#ifndef BOARD_H_
#define BOARD_H_
#include <vector>
#include "cell.h"
#include "creature.h"

class Cell;

class Board{
  private:
    std::vector<std::vector<Cell> > board;
    int width;
    int height;
    int ladybirdCount;
    int aphidCount;
  public:
    Board(int width, int height);
    Cell& getCell(int x, int y);
    Board* simulateMove();
    Board* simulateInteractions();
    int getWidth();
    int getHeight();
    int getAphidCount();
    int getLadybirdCount();

    friend std::ostream& operator<<(std::ostream &out, const Board &board);
};

#endif
