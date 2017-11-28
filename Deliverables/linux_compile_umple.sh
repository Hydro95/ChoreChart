#bin/bash

java -jar umple_1.22.0.5146.jar class.ump && \
mv net/sudormrf/chorechart/*.java ../Source/app/src/main/java/net/sudormrf/chorechart/ && \
rm -r net