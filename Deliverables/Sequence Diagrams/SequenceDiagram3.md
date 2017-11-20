title Use Case 3: Create a Communal Shopping list

"Actor Adult"->":GUI":newList()
":GUI"->":GUI": displayUserSelect
"Actor Adult"->":GUI": setList(String name, Home home, String[] items)
":GUI"->*":ShoppingList": createList(String name, Home home, String[] items)
":ShoppingList"-->"Actor Adult": confirmSave()