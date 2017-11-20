title Allocate Task

":Parent"->+":GUI": modifyTask()
":GUI"->+":TaskModifier" : displayTaskModifier()
":TaskModifier"-->":GUI":
":Parent"->":GUI" : allocateUser()
":GUI"->+":UserSelect" : displayUserSelect()
":UserSelect"-->":GUI":
":Parent"-> ":GUI" : selectUser()
":GUI"->":UserSelect":  aUser := selectUser()
":UserSelect"-->":TaskModifier":
":TaskModifier"-->":GUI":
destroy ":UserSelect"
":Parent"->":GUI": exitTaskModifier()
":GUI"->":GUI" : confirm := promptConfirmAction("Save Changes?")

opt confirm
    ":GUI"->":Task" : allocateUser(aUser)
    ":Task"-->":GUI":
    ":TaskModifier"-->":GUI" : DisplayTasks()
    destroy ":TaskModifier"
end
