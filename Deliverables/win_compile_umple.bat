java -jar umple_1.22.0.5146.jar class.ump && ^

echo ^
"import com.google.firebase.database.DataSnapshot;\n^
import com.google.firebase.database.DatabaseError;\n^
import com.google.firebase.database.DatabaseReference;\n^
import com.google.firebase.database.FirebaseDatabase;\n^
import com.google.firebase.database.ValueEventListener;\n" > "tmp.txt"

type "net\sudormrf\chorechart\Facade.java" >> "tmp.txt"
type "tmp.txt" > "net\sudormrf\chorechart\Facade.java"

move "net\sudormrf\chorechart\*.java" "..\Source\app\src\main\java\net\sudormrf\chorechart\" && ^
rd /s /q "./net"