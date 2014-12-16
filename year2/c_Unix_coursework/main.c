#include <stdio.h>
#include <stdlib.h>
#include "feature1.h"
#include "feature2.h"
#include "navigation.h"
#include "cs237utils.h"
#include "cs237LinkedList.h"

int main(int argc, char** argv) {
    Entity *entities;
    int entityCount;
    LinkedList* dolphins = newLinkedList();
    LinkedList* porpoise = newLinkedList();
    
    printf("===== Entering Phase 1 =====\n");
    doFeature1(&dolphins, &porpoise);
    printf("===== Entering Phase 2 =====\n");
    doFeature2(&dolphins, &porpoise);
    printf("===== Entering Phase 3 =====\n");
    doFeature3(&dolphins, &porpoise);
    printf("===== Phase 3 Finished =====\n");
    
    freeList(dolphins);
    freeList(porpoise);
    return (EXIT_SUCCESS);
}