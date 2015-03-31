#ifndef COLOURS_H_
#define COLOURS_H_

/* Because we're pretty computer scientists! */

/* Thanks to the gloious arch wiki! https://wiki.archlinux.org/index.php/Color_Bash_Prompt */

static std::string Color_Off="\e[0m";       // Text Reset

// Regular Colors
static std::string Black="\e[0;30m";        // Black
static std::string Red="\e[0;31m";          // Red
static std::string Green="\e[0;32m";        // Green
static std::string Yellow="\e[0;33m";       // Yellow
static std::string Blue="\e[0;34m";         // Blue
static std::string Purple="\e[0;35m";       // Purple
static std::string Cyan="\e[0;36m";         // Cyan
static std::string White="\e[0;37m";        // White

// Bold
static std::string BBlack="\e[1;30m";       // Black
static std::string BRed="\e[1;31m";         // Red
static std::string BGreen="\e[1;32m";       // Green
static std::string BYellow="\e[1;33m";      // Yellow
static std::string BBlue="\e[1;34m";        // Blue
static std::string BPurple="\e[1;35m";      // Purple
static std::string BCyan="\e[1;36m";        // Cyan
static std::string BWhite="\e[1;37m";       // White

// Underline
static std::string UBlack="\e[4;30m";       // Black
static std::string URed="\e[4;31m";         // Red
static std::string UGreen="\e[4;32m";       // Green
static std::string UYellow="\e[4;33m";      // Yellow
static std::string UBlue="\e[4;34m";        // Blue
static std::string UPurple="\e[4;35m";      // Purple
static std::string UCyan="\e[4;36m";        // Cyan
static std::string UWhite="\e[4;37m";       // White

// Background
static std::string On_Black="\e[40m";       // Black
static std::string On_Red="\e[41m";         // Red
static std::string On_Green="\e[42m";       // Green
static std::string On_Yellow="\e[43m";      // Yellow
static std::string On_Blue="\e[44m";        // Blue
static std::string On_Purple="\e[45m";      // Purple
static std::string On_Cyan="\e[46m";        // Cyan
static std::string On_White="\e[47m";       // White

// High Intensity
static std::string IBlack="\e[0;90m";       // Black
static std::string IRed="\e[0;91m";         // Red
static std::string IGreen="\e[0;92m";       // Green
static std::string IYellow="\e[0;93m";      // Yellow
static std::string IBlue="\e[0;94m";        // Blue
static std::string IPurple="\e[0;95m";      // Purple
static std::string ICyan="\e[0;96m";        // Cyan
static std::string IWhite="\e[0;97m";       // White

// Bold High Intensity
static std::string BIBlack="\e[1;90m";      // Black
static std::string BIRed="\e[1;91m";        // Red
static std::string BIGreen="\e[1;92m";      // Green
static std::string BIYellow="\e[1;93m";     // Yellow
static std::string BIBlue="\e[1;94m";       // Blue
static std::string BIPurple="\e[1;95m";     // Purple
static std::string BICyan="\e[1;96m";       // Cyan
static std::string BIWhite="\e[1;97m";      // White

// High Intensity backgrounds
static std::string On_IBlack="\e[0;100m";   // Black
static std::string On_IRed="\e[0;101m";     // Red
static std::string On_IGreen="\e[0;102m";   // Green
static std::string On_IYellow="\e[0;103m";  // Yellow
static std::string On_IBlue="\e[0;104m";    // Blue
static std::string On_IPurple="\e[0;105m";  // Purple
static std::string On_ICyan="\e[0;106m";    // Cyan
static std::string On_IWhite="\e[0;107m";   // White

// CHANGE COLOURS HERE!
static std::string ladybirdTextColour = BBlue;
static std::string ladybirdBackColour = On_Red;
static std::string aphidTextColour = BPurple;
static std::string aphidBackColour = On_Black;

#endif