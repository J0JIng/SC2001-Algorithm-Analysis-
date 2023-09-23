javac DijkstraB.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
  echo "Compilation successful"

  # Run the program with input files
  java DijkstraB.java < testB.txt

else
  echo "Compilation failed"
fi