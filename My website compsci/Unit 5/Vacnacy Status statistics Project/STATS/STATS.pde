//2010 Census Demo using JSON (https://www.json.org/)
//2010 variables: https://api.census.gov/data/2010/sf1/variables.html
//H0110004: renter occupied
//* = wildcard (matches anything)
//query: https://api.census.gov/data/2010/sf1?key=e8a63d6bac96233cd5c3cf2de348ed9882285b0a&get=H0110004,NAME&for=state:*
//results saved to: sf1.json (2D array)

// What are the pros/cons of this graph???

PShape usa;
PShape alabama;
PShape alaska;
PShape arizona;
PShape arkansas;
PShape california;
PShape colorado;
PShape connecticcut;
PShape delaware;
PShape districtofcolumbia;
PShape florida;
PShape georgia;
PShape hawaii;
PShape idaho;
PShape illinois;
PShape indiana;
PShape iowa;
PShape kansas;
PShape kentucky;
PShape louisiana;
PShape maine;
PShape maryland;
PShape massachusetts;
PShape michigan;
PShape minnesota;
PShape mississippi;
PShape missouri;
PShape montana;
PShape nebraska;
PShape nevada;
PShape newhampshire;
PShape newjersey;
PShape newmexico;
PShape newyork;
PShape northcarolina;
PShape northdakota;
PShape ohio;
PShape oklahoma;
PShape oregon;
PShape pennsylvania;
PShape rhodeisland;
PShape southcarolina;
PShape southdakota;
PShape tennessee;
PShape texas;
PShape utah;
PShape vermont;
PShape virginia;
PShape washington;
PShape westvirginia;
PShape wisconsin;
PShape wyoming;
PShape puertorico;
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
   alabama = usa.getChild("AL");
   alaska = usa.getChild("AK");
   arizona = usa.getChild("AZ");
   arkansas = usa.getChild("AR");
   california = usa.getChild("CA");
   colorado = usa.getChild("CO");
   connecticcut = usa.getChild("CT");
   delaware = usa.getChild("DE");
   districtofcolumbia = usa.getChild("DC");
   florida = usa.getChild("FL");
   georgia = usa.getChild("GA");
   hawaii = usa.getChild("HI");
   idaho = usa.getChild("ID");
   illinois = usa.getChild("IL");
   indiana = usa.getChild("IN");
   iowa = usa.getChild("IA");
   kansas = usa.getChild("KS");
   kentucky = usa.getChild("KY");
   louisiana = usa.getChild("LA");
   maine = usa.getChild("ME");
   maryland = usa.getChild("MD");
   massachusetts = usa.getChild("MA");
  michigan = usa.getChild("MI");
   minnesota = usa.getChild("MN");
   mississippi = usa.getChild("MS");
   missouri = usa.getChild("MO");
   montana = usa.getChild("MT");
   nebraska = usa.getChild("NE");
   nevada = usa.getChild("NV");
   newhampshire = usa.getChild("NH");
   newjersey = usa.getChild("NJ");
   newmexico = usa.getChild("NM");
   newyork = usa.getChild("NY");
   northcarolina = usa.getChild("NC");
   northdakota = usa.getChild("ND");
  ohio = usa.getChild("OH");
   oklahoma = usa.getChild("MI");
   oregon = usa.getChild("MI");
   pennsylvania = usa.getChild("MI");
   rhodeisland = usa.getChild("MI");
   southcarolina = usa.getChild("MI");
   southdakota = usa.getChild("MI");
   tennessee = usa.getChild("MI");
   texas = usa.getChild("MI");
   utah = usa.getChild("MI");
   vermont = usa.getChild("MI");
   virginia = usa.getChild("MI");
   washington = usa.getChild("MI");
   westvirginia = usa.getChild("MI");
   wisconsin = usa.getChild("MI");
   wyoming = usa.getChild("MI");
   puertorico = usa.getChild("MI");

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
