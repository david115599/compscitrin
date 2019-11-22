console.log("A list of all the command line arguments:");
console.log(process.argv);
console.log("You were using the executable:"+process.argv[0]);
console.log("You were passing the name of the file:"+process.argv[1]);

for(var i=0; i<parseInt(process.argv[3]); i++)
  console.log("Hello " +process.argv[2]+"!");
