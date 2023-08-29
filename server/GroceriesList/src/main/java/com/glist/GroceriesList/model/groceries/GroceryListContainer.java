package com.glist.GroceriesList.model.groceries;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;


@Data
@Builder
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document("groceryContainer")
public class GroceryListContainer {
    @Id
    private final String id;

    private final String username;
    private final List<CollapsedList> collapsedLists;
    @Enumerated(EnumType.STRING)
    private final GroceryContainerType containerType;

    public void deleteCollapsedListByName(String listName) {
        CollapsedList list = getCollapsedListByName(listName);
        if (list != null) {
            collapsedLists.remove(list);
        }
    }

    public void deleteCollapsedListById(String id) {
        CollapsedList list = getCollapsedListById(id);
        if (list != null) {
            collapsedLists.remove(list);
        }
    }

    public CollapsedList getCollapsedListByName(String listName) {
        for (CollapsedList groceryList : collapsedLists) {
            if (groceryList.getListName().equals(listName)) {
                return groceryList;
            }
        }
        return null; // Grocery list not found
    }

    public CollapsedList getCollapsedListById(String listId) {
        for (CollapsedList groceryList : collapsedLists) {
            if (groceryList.getId().equals(listId)) {
                return groceryList;
            }
        }
        return null; // Grocery list not found
    }

    public void addGroceryCollapsedList(CollapsedList list) {
        this.collapsedLists.add(list);
    }
}