title Allocate Task

"aParent:Parent"->+":GUI": modifyTask()
":GUI"->+":TaskModifier" : displayTaskModifier()
":TaskModifier"-->":GUI":
"aParent:Parent"->":GUI" : allocateUser()
":GUI"->+":UserSelect" : displayUserSelect()
":UserSelect"-->":GUI":
"aParent:Parent"-> ":GUI" : selectUser()
":GUI"->":UserSelect":  aUser := selectUser()
":UserSelect"-->":TaskModifier":
":TaskModifier"-->":GUI":
destroy ":UserSelect"
"aParent:Parent"->":GUI": exitTaskModifier()
":GUI"->":GUI" : confirm := promptConfirmAction("Save Changes?")

opt confirm

    ":GUI"->":Task" : allocateUser(aUser)
    ":Task"-->":TaskModifier":

end

":TaskModifier"-->":GUI" : DisplayTasks()
destroy ":TaskModifier"