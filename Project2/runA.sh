javac DijkstraA.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
  echo "Compilation successful"

  # Run the program
  java DijkstraA.java

else
  echo "Compilation failed"
fi