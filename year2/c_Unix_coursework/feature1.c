#include "feature1.h"

FILE* getValidFileFromUser(char message[]){
    char fileInput[INPUT_FILE_LEN];
    FILE* file = NULL;
    do{
        printf("%s", message);
        scanf("%s", fileInput);
        file = fopen(fileInput, "r");
    } while(file == NULL);
    return file;
}

void doFeature1(LinkedList** dolphins, LinkedList** porpoise){
    LinkedList* observers = newLinkedList();   //array of observers
    DataItem* observerDataItem;
    Entity* entity;
    Observer* observer;
    Sighting* sighting;
    FILE* file;
    int day, month, year, hours, minutes, seconds;
    int scanStatus;
    
    //Get the observers file
    file = getValidFileFromUser(" >> Please enter observers file: >> ");
    fscanf(file, "%d %d %d %d %d %d", &day, &month, &year, &hours, &minutes, &seconds);
    printf("%02d/%02d/%04d %02d:%02d:%02d\n", day, month, year, hours, minutes, seconds);
    do{
        observer = calloc(1, sizeof(Observer)); 
        scanStatus = fscanf(file, "%s %lf %lf", observer->name, &observer->pos.lat, &observer->pos.lng);
        if(scanStatus != 3){
            //if it's zero, it's most a blank line
            if(scanStatus != -1){
                printf("Error reading in observer: got %s: (%5.3lf, %5.3lf)\n", observer->name, observer->pos.lat, observer->pos.lng);
            }
        } else {
            insert(observer, observers);
        }
    } while(scanStatus != EOF);
    fclose(file);
    
    //get the sightings file
    file = getValidFileFromUser(" >> Please enter sightings file: >> ");
    printf(TABLEHEADDER);
    do{
        sighting = calloc(1, sizeof(sighting));
        entity = calloc(1, sizeof(Entity));
        scanStatus = fscanf(file, "%s %c %lf %lf", sighting->name, &sighting->type, &sighting->bearing, &sighting->distance);
        if(scanStatus != 4){
            if(scanStatus != -1){
                printf("Error reading in observer: got %s: type: %c (%5.3lf deg, %5.3lf nMiles)\n", sighting->name, sighting->type, sighting->bearing, sighting->distance);
            }
        } else {
            
            //Now find the observer
            observerDataItem = observers->headPointer;
            while(observers != NULL){
                observer = (Observer*)observerDataItem->item;
                if(strcmp(observer->name, sighting->name) == 0){
                    //Join the sighting to the observer we've found
                    convertSightingToAbsolutePosition(*sighting, *observer, entity);
                    
                    //check if they're within the search area
                    if(entity->position.lat > MAX_LATITUDE || entity->position.lat < MIN_LATITUDE ||
                       entity->position.lng > MAX_LONGITUDE || entity->position.lng < MIN_LONGITUDE){
                        printf("Found a sighting (%.3lf lat, %.3lf long) out of bounds, Ignoring\n",
                                entity->position.lat, entity->position.lng);
                        //if the position is outside, report it and continue to the next sighting
                        break;
                    } else {
                        //add the entity to the array
                        if(entity->sighting.type == 'D'){
                            insert(entity, *dolphins);
                        } else if(entity->sighting.type == 'P'){
                            insert(entity, *porpoise);
                        } else {
                            printf("ERROR, Unknown type '%c'\n", entity->sighting.type);
                        }
                        printf(TABLEROW,
                              entity->observer.name,
                              entity->observer.pos.lat,
                              entity->observer.pos.lng,
                              entity->sighting.type,
                              entity->sighting.bearing,
                              entity->sighting.distance,
                              entity->position.lat,
                              entity->position.lng);
                        //We've found our observer, continue to the next sighting
                        break;
                    }
                }
                observerDataItem = observerDataItem->next;
            }
        }
    } while(scanStatus != EOF);
    fclose(file);
}

float degreesToRadians(float degrees){
    return degrees * (M_PI / 180.00);
}

void convertSightingToAbsolutePosition(Sighting sighting,
                                       Observer observer,
                                       Entity* entity){    
    entity->observer = observer;
    entity->sighting = sighting;
    float bearingRad = degreesToRadians(sighting.bearing);
    entity->position.lat = observer.pos.lat + (sighting.distance * cos(bearingRad)) / 60.00;
    entity->position.lng = observer.pos.lng + (sighting.distance * sin(bearingRad) / cos(degreesToRadians(observer.pos.lat))) / 60.00;
}