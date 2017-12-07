#bin/bash

repacement_lines=\
"	public List<String> getTaskIds() {
    	return taskIds;
  }";

if (java -jar umple_1.22.0.5146.jar class.ump)
then

sed "4 a\
import com.google.firebase.database.DataSnapshot;\n\
import com.google.firebase.database.DatabaseError;\n\
import com.google.firebase.database.DatabaseReference;\n\
import com.google.firebase.database.FirebaseDatabase;\n\
import com.google.firebase.database.ValueEventListener;" net/sudormrf/chorechart/Facade.java > Facade.tmp;
mv Facade.tmp net/sudormrf/chorechart/Facade.java;

#these methods haven't worked perl has been the closest if someone wants to try
# grep -vF "$removable_lines" net/sudormrf/chorechart/User.java > User.tmp;
# awk '/public String\[\] getTaskIds\(\)/,/\}/' net/sudormrf/chorechart/User.java > User.tmp;
# perl -i -pe 'BEGIN{undef $/;} s/public String\[\] getTaskIds\(\).*}/$repacement_lines/smg' net/sudormrf/chorechart/User.java;
# mv User.tmp net/sudormrf/chorechart/User.java;

mv net/sudormrf/chorechart/*.java ../Source/app/src/main/java/net/sudormrf/chorechart/ && \
rm -r net;

fi