//2010 Census Demo using JSON (https://www.json.org/)
//2010 variables: https://api.census.gov/data/2010/sf1/variables.html
//H0110004: renter occupied
//* = wildcard (matches anything)
//query: https://api.census.gov/data/2010/sf1?key=e8a63d6bac96233cd5c3cf2de348ed9882285b0a&get=H0110004,NAME&for=state:*
//results saved to: sf1.json (2D array)

// What are the pros/cons of this graph???
PrintWriter output;
float sf = 100000; //scale factor

void setup() {
  JSONArray json;
  json = loadJSONArray("sf1.json");
  output = createWriter("barGraph.html"); 
  output.println("<html><head>");
  output.println("<title>SVG Bar Graph</title>");
  output.println("</head><body>");
  output.println("<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\">");
  //load JSON into arrays (state,pop2010)
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    int pop = json.getJSONArray(i).getInt(0);
    //logarithmic scale (base 10)
    //int exp = 0;
    //while (pop>9) {
      //pop = pop/10;
      //exp++;
    //}
    output.println("<rect x='0' y='"+ (i*20) +"' width='"+ (pop/sf)+"' height='10'/>");
    output.println("<text x='0' y='"+ (i*20+8) + "' fill='gray' font-size='8'>"+
      state+"</text>");
    println(state + ": " + pop);
  }
  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
}