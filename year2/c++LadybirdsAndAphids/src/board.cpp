#include <cstdlib>
#include "game.h"
#include "board.h"
#include "cell.h"
#include "creature.h"
#include "colours.h"

Board::Board(int width, int height){
  this->width = width;
  this->height = height;
  
  this->board.resize(height);
  for(int x = 0; x < height; x++){
    for(int y = 0; y < width; y++){
      this->board[x].push_back(Cell(x, y));
    }
  }
}

Cell& Board::getCell(int x, int y){
  return this->board[x][y];
}

Board* Board::simulateMove(){
  Board *newBoard = new Board(this->width, this->height);
  for(int x = 0; x < this->height; x++){
    for (int y = 0; y < this->width; y++){
      this->board[x][y].simulateMove(newBoard);
    }
  }
  return newBoard;
}

Board* Board::simulateInteractions(){
  this->ladybirdCount = 0;
  this->aphidCount = 0;
  for(int x = 0; x < this->height; x++){
    for(int y = 0; y < this->width; y++){
      this->board[x][y].simulateInteractions(this);
      this->ladybirdCount += this->board[x][y].getLadybirdCount();
      this->aphidCount += this->board[x][y].getAphidCount();
    }
  }
  return this;
}


int Board::getWidth(){
  return this->width;
}

int Board::getHeight(){
  return this->height;
}

int Board::getAphidCount(){
  int count = 0;
  for(int x = 0; x < height; x++){
    for(int y = 0; y < width; y++){
      count += board[x][y].getAphidCount();
    }
  }
  return count;
}

int Board::getLadybirdCount(){
  int count = 0;
  for(int x = 0; x < height; x++){
    for(int y = 0; y < width; y++){
      count += board[x][y].getLadybirdCount();
    }
  }
  return count;
}


std::ostream& operator<<(std::ostream &out, const Board &board){
  out << "\033c" //clear the terminal
      << "Seed: " << Game::getSeed() << "\t"
      << "Generation: " << Game::getGenerationNumber() << std::endl;

  //pritify the top boarder
  out << "\u250C";
  for(int x = 1; x < board.height*3; x++){
    if(x % 3 == 0){
      out << "\u252C";
    } else {
      out << "\u2500";
    }
  }
  out << "\u2510" << std::endl;
  
  //now print the table
  for(int x = 0; x < board.height; x++){
    //print the left table boarder
    out << "\u2502";
    for(int y = 0; y < board.width; y++){

      out << board.board[x][y] << Color_Off << "\u2502";
    }

    if(x != board.height - 1){
      out << std::endl << "\u251C";
      for(int y = 1; y < board.width*3; y++){
        if(y % 3 == 0){
          out << "\u253C";
        } else {
          out << "\u2500";
        }
      }
      out << "\u2524";
    }
    out << std::endl;
  }
 
  out << "\u2514";

  for(int x = 1; x < board.height*3; x++){
    if(x % 3 == 0){
      out << "\u2534";
    } else {
      out << "\u2500";
    }
  }
  out << "\u2518" << std::endl;

  out << "Aphids:\t" << board.aphidCount << std::endl
      << "Ladybirds:\t" << board.ladybirdCount << std::endl;
  return out;
}
