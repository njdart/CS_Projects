#include "cs237LinkedList.h"
#include "cs237utils.h"

void* newLinkedList(){
    LinkedList* list = calloc(1, sizeof(LinkedList));
    list->headPointer = NULL;
    list->tailPointer = NULL;
    list->size = 0;
    return list; 
}

void* traverseToEnd(DataItem* i){
    while(i->next != NULL){
        i = i->next;
    }
    return i;
}

DataItem* insert(void* item, LinkedList* list){
    if(list == NULL || item == NULL){
        printf("ERROR ADDING SOMETHING TO LIST, LIST OR ITEM WAS NULL!\n");
        return;
    }
    DataItem* dataItem = calloc(1, sizeof(DataItem));
    DataItem* prevItem;
    dataItem->next = NULL;
    
    list->size++;
    dataItem->item = item;
    
    //Only if the list is empty
    if(list->headPointer == NULL){
        list->headPointer = dataItem;
        list->tailPointer = dataItem;
        return dataItem;
    }
    prevItem = traverseToEnd(list->headPointer);
    prevItem->next = dataItem;
    dataItem->previous = prevItem;
    if(prevItem != NULL){
        prevItem->next = dataItem;
    }
    list->tailPointer = dataItem;
    return dataItem;
}

void removeItem(DataItem* item, LinkedList* list){
    //Sanity checks
    if(list == NULL){
        printf("LIST WAS NULL!\n");
    }
    if(item == NULL){
        printf("ITEM WAS NULL!\n");
    }
    if(--list->size < 0){
        printf("Error removing something from the list, it seems to be empty?\n");
    }
    
    if(list->headPointer == item){
        if(item->next != NULL) {
            list->headPointer = item->next;
        } else {
            list->headPointer = NULL;
            list->tailPointer = NULL;
        }
        return;
    } 
    if(list->tailPointer == item || item->next == NULL){
        if(item->previous != NULL){
            list->tailPointer = item->previous;
        } else {
            list->headPointer = NULL;
            list->tailPointer = NULL;
        }
        return;
    }
    
    if(item->previous != NULL) {
        item->previous->next = item->next;
    } else {
        printf("ERROR CHANGING item->previous, was NULL");
    }
    if(item->next != NULL){
        item->next->previous = item->previous;
    } else {
        printf("ERROR CHANGING item->next, was NULL");
    }
}

void freeList(LinkedList* list){
    DataItem* iterator = list->headPointer;
    DataItem* toFree;
    while(iterator != NULL){
        toFree = iterator;
        iterator = iterator->next;
        free(toFree);
    }
    //free(list);
}