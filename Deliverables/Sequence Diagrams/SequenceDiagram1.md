title Allocate Task

"Parent Actor"->+":GUI" : selectTask()
":GUI"->+":Task" : task := selectTask()

"Parent Actor"->":GUI": modifyTask()
":GUI"->+":TaskModifier" : displayTaskModifier()
":TaskModifier"-->":GUI" :


"Parent Actor"->":GUI" : allocateUser()
":GUI"->+":UserSelect" : displayUserSelect()
":UserSelect"-->":GUI":
"Parent Actor"-> ":GUI" : selectUser()
":GUI"->":UserSelect":  aUser := selectUser()
":UserSelect"-->":TaskModifier":
":TaskModifier"-->":GUI":
destroy ":UserSelect"
"Parent Actor"->":GUI": exitTaskModifier()
":GUI"->":GUI" : confirm := promptConfirmAction("Save Changes?")

opt confirm

    ":GUI"->":Task" : task.allocateUser(aUser)
    ":Task"-->":TaskModifier":

end

":TaskModifier"-->":GUI" : DisplayTasks()
destroy ":TaskModifier"

