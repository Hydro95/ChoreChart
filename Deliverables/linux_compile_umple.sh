#bin/bash

java -jar umple_1.22.0.5146.jar class.ump && \

sed "4 a\
import com.google.firebase.database.DataSnapshot;\n\
import com.google.firebase.database.DatabaseError;\n\
import com.google.firebase.database.DatabaseReference;\n\
import com.google.firebase.database.FirebaseDatabase;\n\
import com.google.firebase.database.ValueEventListener;" net/sudormrf/chorechart/Facade.java > tmp.txt

mv tmp.txt net/sudormrf/chorechart/Facade.java

mv net/sudormrf/chorechart/*.java ../Source/app/src/main/java/net/sudormrf/chorechart/ && \
rm -r net