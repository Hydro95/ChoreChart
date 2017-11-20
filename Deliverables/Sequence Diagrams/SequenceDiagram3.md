title Use Case 3: Create a Communal Shopping list

":Adult"->":GUI":newList()
":GUI"->":GUI": displayUserSelect
":Adult"->":GUI": setList(String name, Home home, String[] items)
":GUI"->*":ShoppingList": createList(String name, Home home, String[] items)
":ShoppingList"-->":Adult": confirmSave()