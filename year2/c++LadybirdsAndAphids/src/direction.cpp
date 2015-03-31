#include <iostream>
#include "direction.h"
// 
//  +-----> +x 
//  |
//  |
//  v +y
//
const std::pair<int, int> Direction::NORTH(-1, 0);
const std::pair<int, int> Direction::NORTHEAST(-1, 1);
const std::pair<int, int> Direction::EAST(0, 1);
const std::pair<int, int> Direction::SOUTHEAST(1, 1);
const std::pair<int, int> Direction::SOUTH(1, 0);
const std::pair<int, int> Direction::SOUTHWEST(1, -1);
const std::pair<int, int> Direction::WEST(0, -1);
const std::pair<int, int> Direction::NORTHWEST(-1, -1);

const std::pair<int, int> Direction::Directions[8] = {
  Direction::NORTH,
  Direction::NORTHEAST,
  Direction::EAST,
  Direction::SOUTHEAST,
  Direction::SOUTH,
  Direction::SOUTHWEST,
  Direction::WEST,
  Direction::NORTHWEST,
};