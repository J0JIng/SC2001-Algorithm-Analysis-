javac DijkstraB.java

if [ $? -eq 0 ]; then
  # echo "Compilation successful"
  java DijkstraB.java < test1.txt
else
  echo "Compilation failed"
fi