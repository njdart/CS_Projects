/* 
 * File:   utils.h
 * Author: nid21
 *
 * Created on 27 November 2014, 13:45
 */

#ifndef UTILS_H
#define	UTILS_H

    #include "navigation.h"
    #include <math.h>
    #include "cs237LinkedList.h"

    #define MAX_NAME_LEN 32
    #define MAX_ENTITYS_IN_POD 50
    #define true 1
    #define false 0
    #define Location location
    #define MIN_LATITUDE 52.000
    #define MAX_LATITUDE 52.833
    #define MIN_LONGITUDE -5.500
    #define MAX_LONGITUDE -4.000
    #define Location location

    #define TABLEHEADDER "UID\tOLAT\tOLNG\tTYPE\tBEARING\tRANGE\tCMLAT\tCMLNG\n"
    #define TABLEROW     "%s\t%.3lf\t%.3lf\t%c\t%.1lf\t%.3lf\t%.3lf\t%.3lf\n"

    typedef struct {
        location pos;
        char name[MAX_NAME_LEN];
    } Observer;

    typedef struct {
        char type;
        double bearing;
        double distance;
        char name[MAX_NAME_LEN];
    } Sighting;

    typedef struct {
        Location position;
        Observer observer;
        Sighting sighting;
    } Entity;
    
    typedef struct {
        Location location;
        char type;
        LinkedList* entities;
    } DefinitiveEntity;
    
    typedef struct {
        LinkedList* definitiveEntitiesList;
    } Pod;

    #ifdef	__cplusplus
    extern "C" {
    #endif

        //FOO
        
    #ifdef	__cplusplus
    }
    #endif

#endif	/* UTILS_H */

