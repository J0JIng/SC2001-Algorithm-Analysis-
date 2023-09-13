# Compile the Java program
javac Main.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
  echo "Compilation successful"

  # Run the program with input files
  echo "Enter the test set number (0 to 6): "
  read set_number
  
  #java Main < "test/testset${set_number}/input10.txt"
  #java Main < "test/testset${set_number}/input100.txt"
  #java Main < "test/testset${set_number}/input1000.txt"
  java Main < "test/testset${set_number}/input10000.txt"
  #java Main < "test/testset${set_number}/input100000.txt"
  #java Main < "test/testset${set_number}/input1000000.txt"
  #java Main < "test/testset${set_number}/input2000000.txt"
  #java Main < "test/testset${set_number}/input3000000.txt"
  #java Main < "test/testset${set_number}/input4000000.txt"
  #java Main < "test/testset${set_number}/input5000000.txt"
  #java Main < "test/testset${set_number}/input6000000.txt"
  #java Main < "test/testset${set_number}/input7000000.txt"
  #java Main < "test/testset${set_number}/input8000000.txt"
  #java Main < "test/testset${set_number}/input9000000.txt"
  #java Main < "test/testset${set_number}/input10000000.txt"

else
  echo "Compilation failed"
fi