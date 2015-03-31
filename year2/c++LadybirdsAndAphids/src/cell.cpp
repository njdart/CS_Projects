#include <iostream>
#include <typeinfo>
#include <vector>
#include "cell.h"
#include "ladybird.h"
#include "aphid.h"
#include "board.h"
#include "colours.h"
#include "creature.h"

Cell::Cell(int x, int y){
  this->x = x;
  this->y = y;
}

void Cell::addCreature(Aphid *creature, bool deferred){
  if(deferred){
    this->newAphids.push_back(creature);
  } else {
    this->aphids.push_back(creature);
  }
}

void Cell::addCreature(Ladybird *creature, bool deferred){
  if(deferred){
    this->newLadybirds.push_back(creature);
  } else {
    this->ladybirds.push_back(creature);
  }
}

int Cell::getAphidCount(){
  return this->aphids.size();
}

int Cell::getLadybirdCount(){
  return this->ladybirds.size();
}

void Cell::simulateMove(Board *newBoard){
  //std::cout << "updating cell " << this->x << ", " << this->y << std::endl;
  if(this->aphids.size() > 0){
    for(std::vector<Aphid*>::iterator it = this->aphids.begin(); it != this->aphids.end(); it++){
      (*it)->doMove(newBoard);
    }
  }

  if(this->ladybirds.size() > 0){
    for(std::vector<Ladybird*>::iterator it = this->ladybirds.begin(); it != this->ladybirds.end(); it++){
      (*it)->doMove(newBoard);
    }
  }
}

void Cell::simulateInteractions(Board *board){
  for(std::vector<Ladybird*>::iterator it = ladybirds.begin(); it != ladybirds.end(); it++){
    for(std::vector<Aphid*>::iterator innerIt = aphids.begin(); innerIt != aphids.end(); innerIt++){
      (*it)->doInteraction(*innerIt, board);
      (*innerIt)->doInteraction(*it, board);
    }
  }

  //Ladybird Mating
  for(std::vector<Ladybird*>::iterator it = ladybirds.begin(); it != ladybirds.end(); it++){
    for(std::vector<Ladybird*>::iterator innerIt = ladybirds.begin(); innerIt != ladybirds.end(); innerIt++){
      if(*it != *innerIt){
        (*it)->doInteraction(*innerIt, board);
      }
    }
  }

  //Aphid Mating
  for(std::vector<Aphid*>::iterator it = aphids.begin(); it != aphids.end(); it++){
    for(std::vector<Aphid*>::iterator innerIt = aphids.begin(); innerIt != aphids.end(); innerIt++){
      if(*it != *innerIt){
        (*it)->doInteraction(*innerIt, board);
      }
    }    
  }

  //kill the required creatures
  bool found = false;
  for(std::vector<Creature*>::iterator it = killedCreatures.begin(); it != killedCreatures.end(); it++){
    found = false;
    for(std::vector<Aphid*>::iterator innerIt = aphids.begin(); innerIt != aphids.end();){
      if(*it == *innerIt){
        innerIt = aphids.erase(innerIt);
        found = true;
        break;
      } else {
        innerIt++;
      }
    }
    if(found) continue;
    for(std::vector<Ladybird*>::iterator innerIt = ladybirds.begin(); innerIt != ladybirds.end();){
      if(*it == *innerIt){
        innerIt = ladybirds.erase(innerIt);
        break;
      } else {
        innerIt++;
      }
    }
  }

  //not add the new creatures to the cell
  for(std::vector<Ladybird*>::iterator it = newLadybirds.begin(); it != newLadybirds.end();it++){
    ladybirds.push_back(*it);
  }

  for(std::vector<Aphid*>::iterator it = newAphids.begin(); it != newAphids.end();it++){
    aphids.push_back(*it);
  }

  newAphids.clear();
  newLadybirds.clear();
  killedCreatures.clear();
}

void Cell::kill(Creature *creature){
  killedCreatures.push_back(creature);
}

std::ostream& operator<<(std::ostream &out, const Cell &cell){
  out << ladybirdBackColour << ladybirdTextColour;
  
  //format the aphids according to the spec
  if(cell.aphids.size() < 1){
    out << " ";
  } else if(cell.aphids.size() < 10){
    out << cell.aphids.size();
  } else {
    out << "~";
  }

  out << aphidBackColour << aphidTextColour;

  if(cell.ladybirds.size() < 1){
    out << " ";
  } else if(cell.ladybirds.size() < 10){
    out << cell.ladybirds.size();
  } else {
    out << "~";
  }
  
  out << Color_Off;
  return out;
}
