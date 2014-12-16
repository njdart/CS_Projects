/* 
 * File:   cs237LinkedList.h
 * Author: Nic
 *
 * Created on 05 December 2014, 02:10
 */

#ifndef CS237LINKEDLIST_H
#define	CS237LINKEDLIST_H

    #include <stdio.h>
    #include <stdlib.h>
    
    typedef struct struct_data_item {
        void* item;
        struct struct_data_item* next;
        struct struct_data_item* previous;
    } DataItem;
    
    typedef struct {
        DataItem* headPointer;
        DataItem* tailPointer;
        int size;
    } LinkedList;

    void* newLinkedList();

    void* traverseToEnd(DataItem*);

    void* traverseToBeginning(DataItem*);

    DataItem* insert(void*, LinkedList*);
    
    void removeItem(DataItem*, LinkedList*);

    void freeList(LinkedList*);
    
    #ifdef	__cplusplus
    extern "C" {
    #endif

    #ifdef	__cplusplus
    }
    #endif

#endif	/* CS237LINKEDLIST_H */

