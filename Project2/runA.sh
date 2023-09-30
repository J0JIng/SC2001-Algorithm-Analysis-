javac DijkstraA.java

if [ $? -eq 0 ]; then
  # echo "Compilation successful"
  java DijkstraA.java < test1.txt
else
  echo "Compilation failed"
fi