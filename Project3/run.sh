javac Knapsack.java
if [ $? -eq 0 ]; then
  echo "Compilation successful"
else
  echo "Compilation failed"
fi

java Knapsack.java

