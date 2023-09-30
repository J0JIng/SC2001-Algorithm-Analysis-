javac DijkstraA.java
if [ $? -eq 0 ]; then
  echo "Compilation A successful"
else
  echo "Compilation A failed"
fi

javac DijkstraB.java
if [ $? -eq 0 ]; then
  echo "Compilation B successful"
else
  echo "Compilation B failed"
fi

java DijkstraA.java < test1.txt
java DijkstraB.java < test1.txt