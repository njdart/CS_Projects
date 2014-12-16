#include "feature2.h"
#include "cs237LinkedList.h"

void doFeature2(LinkedList** dolphins, LinkedList** porpoise){

    printf(TABLEHEADDER);

    //now combine the items too close in each list
    *dolphins = groupSightings(*dolphins, 'D');
    
    *porpoise = groupSightings(*porpoise, 'P');
}

LinkedList* groupSightings(LinkedList* list, char type){
    //List iterators
    DataItem* outerListIterator;
    DataItem* innerListIterator;
    Entity* outerListEntity;
    Entity* innerListEntity;
    DefinitiveEntity* definitiveEntity;
    LinkedList* similarEntities;
    
    //Confirmed similar sightings
    LinkedList* definitiveEntityList = newLinkedList();
    int similarSightingsCount;
    
    double greatCircle;
    
    //averages
    double avgLat, avgLng;
    Location averageLocation;
    
    outerListIterator = list->headPointer;
    while(outerListIterator != NULL){
        outerListEntity = (Entity*)outerListIterator->item;
        
        averageLocation = outerListEntity->position;
        similarEntities = newLinkedList();
        insert(outerListEntity, similarEntities);
        
        printf("\n");
        printf(TABLEROW,
                outerListEntity->observer.name,
                outerListEntity->observer.pos.lat,
                outerListEntity->observer.pos.lng,
                outerListEntity->sighting.type,
                outerListEntity->sighting.bearing,
                outerListEntity->sighting.distance,
                outerListEntity->position.lat,
                outerListEntity->position.lng);
        removeItem(outerListIterator, list);
        
        similarSightingsCount = 0;
        
        innerListIterator = list->headPointer;
        while(innerListIterator != NULL){
            innerListEntity = (Entity*)innerListIterator->item;
            
            //innerListIterator = innerListIterator->next;
            
            greatCircle = great_circle(averageLocation, innerListEntity->position);
            
            if(greatCircle <= MAX_DIST_FROM_SIMILAR_KIND){
                printf(TABLEROW,
                    innerListEntity->observer.name,
                    innerListEntity->observer.pos.lat,
                    innerListEntity->observer.pos.lng,
                    innerListEntity->sighting.type,
                    innerListEntity->sighting.bearing,
                    innerListEntity->sighting.distance,
                    innerListEntity->position.lat,
                    innerListEntity->position.lng);
                //remove it from the list
                removeItem(innerListIterator, list);
                free(innerListIterator);
                innerListIterator = innerListIterator->next;
                
                insert(innerListEntity, similarEntities);
                
                averageLocation.lat *= similarSightingsCount;
                averageLocation.lng *= similarSightingsCount;
                
                ++similarSightingsCount;
                
                averageLocation.lat += innerListEntity->position.lat;
                averageLocation.lng += innerListEntity->position.lng;
                
                averageLocation.lat /= similarSightingsCount;
                averageLocation.lng /= similarSightingsCount;
            } else {
                innerListIterator = innerListIterator->next;
            }
        }
        
        //Now add the new entity to the list of unique sightings
        definitiveEntity = malloc(sizeof(DefinitiveEntity));
        definitiveEntity->location = averageLocation;
        definitiveEntity->type = type;
        definitiveEntity->entities = similarEntities;
        
        insert(definitiveEntity, definitiveEntityList);
        
        printf(TABLEROW,
                "AVRG",
                0.00,
                0.00,
                definitiveEntity->type,
                0.00,
                0.00,
                definitiveEntity->location.lat,
                definitiveEntity->location.lng);
        
        free(outerListIterator);
        outerListIterator = list->headPointer;
    }
    return definitiveEntityList;
}