/* 
 * File:   feature2.h
 * Author: Nic
 *
 * Created on 02 December 2014, 18:44
 */

#ifndef FEATURE2_H
#define	FEATURE2_H

    
    #include <stdio.h>
    #include <stdlib.h>
    #include "cs237utils.h"
    #include "navigation.h"
    #include "cs237LinkedList.h"

    #define MAX_DIST_FROM_SIMILAR_KIND 0.02

    LinkedList* groupSightings(LinkedList*, char);
    void doFeature2(LinkedList**, LinkedList**);
    
    #ifdef	__cplusplus
    extern "C" {
    #endif

    #ifdef	__cplusplus
    }
    #endif

#endif	/* FEATURE2_H */

