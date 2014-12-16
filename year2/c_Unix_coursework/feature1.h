/* 
 * File:   feature1.h
 * Author: nid21
 *
 * Created on 27 November 2014, 11:32
 */


#ifndef FEATURE1_H
#define	FEATURE1_H

    #include "cs237utils.h"
    #include <stdio.h>
    #include <stdlib.h>
    #include <unistd.h>
    #include <string.h>
    #include <strings.h>
    #include "cs237LinkedList.h"

    #define INPUT_FILE_LEN 50
    #define FILE_IN_BUFF_SIZE 50
    #define MAX_OBSERVERS 50
    #define MAX_SIGHTINGS 50
    #define MAX_ENTITIES 50

    FILE* getValidFileFromUser(char*);
    void doFeature1(LinkedList**, LinkedList**);
    float degreesToRadians(float);
    void convertSightingToAbsolutePosition(Sighting, Observer, Entity*);

    #ifdef	__cplusplus
    extern "C" {
    #endif

            //FOO

    #ifdef	__cplusplus
    }
    #endif

#endif	/* FEATURE1_H */

