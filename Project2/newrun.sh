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

javac generateInput.java

if [ $? -eq 0 ]; then
  echo "Compilation successful"

else
  echo "Compilation failed"
fi

# Run DijkstraA 100 times with different input files
#for ((i=3; i<=1000; i++)); do
  #echo "Running iteration $i" 
  #java generateInput s $i
  #java DijkstraA.java < test/sparseTest.txt
#done

# Run DijkstraA 100 times with different input files
for ((i=601; i<=1000; i++)); do
  #echo "Running iteration $i" 
  java generateInput d $i
  java DijkstraA.java < test/denseTest.txt
done


