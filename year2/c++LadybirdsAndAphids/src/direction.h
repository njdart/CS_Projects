#ifndef DIRECTION_H_
#define DIRECTION_H_

#include <iostream>

class Direction {
  public:
    static const std::pair<int, int> NORTH;
    static const std::pair<int, int> NORTHEAST;
    static const std::pair<int, int> EAST;
    static const std::pair<int, int> SOUTHEAST;
    static const std::pair<int, int> SOUTH;
    static const std::pair<int, int> SOUTHWEST;
    static const std::pair<int, int> WEST;
    static const std::pair<int, int> NORTHWEST;

    /** Indexed for returning easily when using random **/
    static const std::pair<int, int> Directions[8];
};

#endif