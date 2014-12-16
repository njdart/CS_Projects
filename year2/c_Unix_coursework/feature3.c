#include "feature3.h"

void doFeature3(LinkedList** dolphins, LinkedList** porpoise){
    int d, p;
    
    printf(TABLEHEADDER);
    
    d = findPods(*dolphins);
    
    printf("\n");
    
    p = findPods(*porpoise);
    
    printf("\nFound %d Pods, %d of Dolphins, %d of Porpoises\n",
            d + p, d, p);
}

int findPods(LinkedList* list){
    DataItem* definitiveListIterator = list->headPointer;
    DataItem* innerDefinitiveListIterator;
    DataItem* entityIterator;
    DefinitiveEntity* definitiveEntity;
    DefinitiveEntity* innerDefinitiveEntity;
    Entity* entity;
    Pod* pod;
    LinkedList* podList = newLinkedList();
    int foundNone = false;
    double greatCircle;
    
    while(definitiveListIterator != NULL){
        definitiveEntity = (DefinitiveEntity*)definitiveListIterator->item;
        
        //make a new pod
        pod = calloc(1, sizeof(Pod));
        pod->definitiveEntitiesList = newLinkedList();
        
        //and insert this item into pod list and remove it from the searching list
        insert(definitiveEntity, pod->definitiveEntitiesList);
        removeItem(definitiveListIterator, list);
                
        innerDefinitiveListIterator = definitiveEntity->entities->headPointer;
        
        //keep iterating through the list of entities until we arent finding
        // finding any more
        while(foundNone){
            foundNone = true;
            
            innerDefinitiveListIterator = list->headPointer;
            while(innerDefinitiveListIterator != NULL){
                innerDefinitiveEntity = (DefinitiveEntity*)innerDefinitiveListIterator->item;
                innerDefinitiveListIterator = innerDefinitiveListIterator->next;
                
                greatCircle = great_circle(definitiveEntity->location,
                                           innerDefinitiveEntity->location);
                
                if(greatCircle <= MAX_POD_RANGE){
                    foundNone = false;
                    
                    insert(innerDefinitiveEntity, pod->definitiveEntitiesList);
                    removeItem(innerDefinitiveListIterator, list);
                }
            }
        }
        
        if(innerDefinitiveEntity->entities->size > 1){
            insert(pod, podList);
            printf("POD %d\n", podList->size);
            innerDefinitiveListIterator = pod->definitiveEntitiesList->headPointer;
            while(innerDefinitiveListIterator != NULL){
                innerDefinitiveEntity = (DefinitiveEntity*)innerDefinitiveListIterator->item;

                entityIterator = innerDefinitiveEntity->entities->headPointer;
                while(entityIterator != NULL){
                    entity = (Entity*)entityIterator->item;
                    printf(TABLEROW,
                            entity->observer.name,
                            entity->observer.pos.lat,
                            entity->observer.pos.lng,
                            entity->sighting.type,
                            entity->sighting.bearing,
                            entity->sighting.distance,
                            entity->position.lat,
                            entity->position.lng);                
                    entityIterator = entityIterator->next;
                }
                innerDefinitiveListIterator = innerDefinitiveListIterator->next;
            }
        }
        definitiveListIterator = list->headPointer;
    }
    return podList->size;
}