# Compile the Java program
javac generateInput.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
  echo "Compilation successful"

  # Run the program with input files
  java generateInput

else
  echo "Compilation failed"
fi