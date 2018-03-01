//2010 Census Demo using JSON (https://www.json.org/)
//2010 variables: https://api.census.gov/data/2010/sf1/variables.html
//H0110004: renter occupied
//* = wildcard (matches anything)
//query: https://api.census.gov/data/2010/sf1?key=e8a63d6bac96233cd5c3cf2de348ed9882285b0a&get=H0110004,NAME&for=state:*
//results saved to: sf1.json (2D array)

// What are the pros/cons of this graph???

PShape usa;
PShape michigan;
PShape ohio;

String states[];
int vacancy2000[]=new int[52];
int vacancy2010[]=new int[52];
int national2000;
int national2010;
PrintWriter output;
int sf =5;
void setup() {
  size(1000, 1000);
  usa = loadShape("usa-wikipedia.svg");
  michigan = usa.getChild("MI");
  ohio = usa.getChild("OH");
  JSONArray json;
  JSONArray json1;
  states = loadStrings("State_Names.txt");
  json = loadJSONArray("2010sf1.json");
  json1 = loadJSONArray("2000sf1.json");
  output = createWriter("HOME.html");
  output.println("<html><head>");
  output.println("<title>Vacancy Status Statistics</title>");
  output.println("</head><body>");
  output.println("<h1>Migrant Vacancy Status Statistics in US Between 2000 and 2010</h1>");
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1); //states[i-1]
  output.println("<a href='website/"+state+".html'>"+state+"</a>");
}
  output.println("<svg width=\"2000\" height=\"15000\" xmlns=\"http://www.w3.org/2000/svg\">");
  //load JSON into arrays (state,pop2010)
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    vacancy2010[i-1]=json.getJSONArray(i).getInt(0);
    output.println("<rect y='0' x='"+ (i*20-10) +"' height='"+ vacancy2010[i-1]/sf+"' width='10' fill='darkred'/>");
    output.println("<text x='"+vacancy2010[i-1]/sf+"' y='"+ -(i*20-8) + "'transform= rotate("+90+","+0+","+0+") fill='green' font-size='10'>"+state+"  </text>");
    println(state + ": " + vacancy2010[i-1]);
  }
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    vacancy2000[i-1]=json1.getJSONArray(i).getInt(0);
    output.println("<rect y='0' x='"+ (i*20) +"' height='"+ (vacancy2000[i-1]/sf)+"' width='10' fill='darkblue'/>");
    output.println("<text x='"+vacancy2000[i-1]/sf+"' y='"+ -(i*20) + "'transform= rotate("+90+","+0+","+0+") fill='green' font-size='10'>"+state+"  </text>");
    println(state + ": " + vacancy2000[i-1]);
  }

  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
  for (int i=0;i<vacancy2000.length ;i++ ) {
  national2000+=vacancy2000[i];
  }
  for (int i=0;i<vacancy2010.length ;i++ ) {
  national2010+=vacancy2010[i];
  }
  for(int i = 0; i<states.length; i++){
  output = createWriter("website/"+states[i]+".html");
  output.println("<html><head>");
  output.println("<title>" + states[i] + "</title>");
  output.println("</head><body>");
  output.println("<h1>" + states[i] + "</h1>");
  output.println("<h1> Number of vacant housing units for migrants in "+ states[i] +" in 2000: " + vacancy2000[i] + " units</h1>");
  output.println("<h1> Total Number of vacant housing units for migrants in 2000: " + national2000 + " units</h1>");
  output.println("<h1> Number of vacant housing units for migrants in 2010: " + vacancy2010[i] + " units</h1>");
  output.println("<h1> Total Number of vacant housing units for migrants in "+ states[i] +" in 2000: " + national2010 + " units</h1>");
  output.println("<svg width=\"2000\" height=\"20000\" xmlns=\"http://www.w3.org/2000/svg\">");
  output.println("<rect y='0' x='40' height='"+ vacancy2000[i]/(sf*10)+"' width='100' fill='darkred'/>");
  output.println("<rect y='0' x='140' height='"+ national2000/(sf*10)+"' width='100' fill='yellow'/>");
  output.println("<rect y='0' x='240' height='"+ (vacancy2010[i]/(sf*10))+"' width='100' fill='darkred'/>");
  output.println("<rect y='0' x='340' height='"+ (national2010/(sf*10))+"' width='100' fill='yellow'/>");
  output.println("<text y='"+(vacancy2000[i]/(sf*10)+40)+"' x='40' fill='green' font-size='40'>"+vacancy2000[i]+"  </text>");
  output.println("<text y='"+(vacancy2010[i]/(sf*10)+40)+"' x='240' fill='green' font-size='40'>"+vacancy2010[i]+"  </text>");
  output.println("<text y='"+(national2000/(sf*10)+40)+"' x='140' fill='green' font-size='40'>"+national2000+"  </text>");
  output.println("<text y='"+(national2010/(sf*10)+40)+"' x='340' fill='green' font-size='40'>"+national2010+"  </text>");
  output.println("</body></html>");
  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
  }
}
void draw() {
  background(255);

  // Draw the full map
  shape(usa, -250, 0);

  // Disable the colors found in the SVG file
  michigan.disableStyle();
  // Set our own coloring
  fill(0, 51, 102);
  noStroke();
  // Draw a single state
  shape(michigan, -250, 0); // Wolverines!

  // Disable the colors found in the SVG file
  ohio.disableStyle();
  // Set our own coloring
  fill(153, 0, 0);
  noStroke();
  // Draw a single state
  shape(ohio, -250, 0);  // Buckeyes!
}
