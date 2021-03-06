/*
This is my Sense OF Us project. It takes data from the us census beurua and procedurly generates information page
that contains data for every state and the changes in those Statistics.
It generates a heat map and bar graph for the whole nation.
It generates state specific Statistics page's for every state.
By, David B
When the code runs ocasionaly it spits out an irrelavent error just rerun the code and it works fine without the error.
*/

//here my pshape variables are defined.
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
String states[];
int vacancy2000[]=new int[52];
int vacancy2010[]=new int[52];
int national2000;
int national2010;
PrintWriter output;
int sf =5;
int nationalaverage2000;
int nationalaverage2010;
void setup() {
  size(1400, 650);
  // This is where the pshape variables are assinged to the sections of the svg.
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
  oklahoma = usa.getChild("OK");
  oregon = usa.getChild("OR");
  pennsylvania = usa.getChild("PA");
  rhodeisland = usa.getChild("RI");
  southcarolina = usa.getChild("SC");
  southdakota = usa.getChild("SD");
  tennessee = usa.getChild("TN");
  texas = usa.getChild("TX");
  utah = usa.getChild("UT");
  vermont = usa.getChild("VT");
  virginia = usa.getChild("VA");
  washington = usa.getChild("WA");
  westvirginia = usa.getChild("WV");
  wisconsin = usa.getChild("WI");
  wyoming = usa.getChild("WY");
  //This is where the json file is loaded.
  JSONArray json;
  JSONArray json1;
  states = loadStrings("State_Names.txt");
  json = loadJSONArray("2010sf1.json");
  json1 = loadJSONArray("2000sf1.json");
  //This is where the the home page is generated.
  output = createWriter("HOME.html");
  output.println("<html><head>");
  output.println("<title>Vacancy Status Statistics</title>");
  output.println("</head><body>");
  //CSS for the home page
  output.println("<style>");
  output.println("body {background-color: rgb(90,230,5); text-align: center; text-align: center;} p {column-count: 4; column-gap: 280px;}");
  output.println("</style>");
  //homepage content
  output.println("<h1>Migrant Vacancy Status Statistics in US Between 2000 and 2010</h1>");
  output.println("<h2>The purpose of this website is to investigate the changes in the number of vacant housing units for imigrants in each state between the years of 2000 and 2010.</h2>");
  output.println("<img src='heatmap.png' alt='House''r'>");
  output.println("<a href='https://www.census.gov/developers/'>More Info On The Data</a>");
  output.println("<h2>Does the the number of vacant housing units for imigrants in the us Increase or Decrease From 2000 to 2010?</h2>");
  //checks weither the number of vacancies increased or decreased
  for (int i = 1; i < json.size(); i++) {
    vacancy2010[i-1]=json.getJSONArray(i).getInt(0);
  }
  for (int i = 1; i < json1.size(); i++) {
    vacancy2000[i-1]=json1.getJSONArray(i).getInt(0);
  }
  for (int i=0; i<vacancy2000.length; i++ ) {
    national2000+=vacancy2000[i];
  }
  for (int i=0; i<vacancy2010.length; i++ ) {
    national2010+=vacancy2010[i];
  }
  println(national2000);
  println(national2010);
  if (national2000>national2010) {
    output.println("<h2>The number of vacancies has decreased</h2>");
  }
  else{
    output.println("<h2>The number of vacancies has increased</h2>");
  }
  // links the home page to the state pages.
  output.println("<p>");
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1); //states[i-1]
    output.println("<a href='website/"+state+".html'>"+state+" </a> ");
  }
  output.println("</p>");
  output.println("<svg width=\"1040\" height=\"15000\" xmlns=\"http://www.w3.org/2000/svg\">");
// generates bar graph that is on the home page.
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    output.println("<rect y='0' x='"+ (i*20-10) +"' height='"+ vacancy2010[i-1]/sf+"' width='10' fill='darkred'/>");
    output.println("<text x='"+vacancy2010[i-1]/sf+"' y='"+ -(i*20-8) + "'transform= rotate("+90+","+0+","+0+") fill='rgb(230,90,5)' font-size='10'>"+state+" 2010  </text>");
    println(state + ": " + vacancy2010[i-1]);
  }
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    output.println("<rect y='0' x='"+ (i*20) +"' height='"+ (vacancy2000[i-1]/sf)+"' width='10' fill='darkblue'/>");
    output.println("<text x='"+vacancy2000[i-1]/sf+"' y='"+ -(i*20) + "'transform= rotate("+90+","+0+","+0+") fill='rgb(5,90,230)' font-size='10'>"+state+" 2000  </text>");
    println(state + ": " + vacancy2000[i-1]);
  }

  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
  //calculates the national average.
  for ( int i = 0; i < vacancy2000.length; ++i ) {
 nationalaverage2000 += vacancy2000[i];
}
nationalaverage2000 /= (float)(vacancy2000.length);
for ( int i = 0; i < vacancy2010.length; ++i ) {
nationalaverage2010 += vacancy2010[i];
}
nationalaverage2010 /= (float)(vacancy2010.length);
println(nationalaverage2000);
println(nationalaverage2010);
//generates state pages.
  for (int i = 0; i<states.length; i++) {
    output = createWriter("website/"+states[i]+".html");
    output.println("<html><head>");
    output.println("<title>" + states[i] + "</title>");
    output.println("</head><body>");
    //CSS
    output.println("<style>");
    output.println("body {background-color: rgb(90,230,5); text-align: center; text-align: center;} a {font-size: 40px;}");
    output.println("</style>");
    //link to home.
    output.println("<a href='../HOME.html'> Home</a>");
    //Dynamic data.
    output.println("<h1>Migrant Vacancy Status Statistics in US Between 2000 and 2010</h1>");
    if (i==0) {
    output.println("<a href='"+states[50]+".html'>Previous </a>");
    }
    else{
    output.println("<a href='"+states[i-1]+".html'>Previous </a>");
    }
    output.println("<a href='https://en.wikipedia.org/wiki/"+states[i]+"'>"+states[i]+"</a>");
    if (i==50) {
    output.println("<a href='"+states[1]+".html'> Next</a>");
    }
    else{
    output.println("<a href='"+states[i+1]+".html'> Next</a>");
    }
    output.println("<h1> Number of vacant housing units for migrants in "+ states[i] +" in 2000: " + vacancy2000[i] + " units</h1>");
    output.println("<h1> Total Number of vacant housing units for migrants in 2000: " + national2000 + " units</h1>");
    output.println("<h1> Number of vacant housing units for migrants in 2010: " + vacancy2010[i] + " units</h1>");
    output.println("<h1> Total Number of vacant housing units for migrants in "+ states[i] +" in 2000: " + national2010 + " units</h1>");
    if (vacancy2000[i]>vacancy2010[i]) {
      output.println("<h2>The number of vacancies in "+states[i]+" has decreased</h2>");
    }
    else{
      output.println("<h2>The number of vacancies in "+states[i]+" has increased</h2>");
    }
    if (vacancy2000[i]>nationalaverage2000) {
      output.println("<h2>The number of vacancies in "+states[i]+"was above the national average in 2000</h2>");
    }
    else{
      output.println("<h2>The number of vacancies in "+states[i]+" was below the national average in 2000</h2>");
    }
    if (vacancy2010[i]>nationalaverage2010) {
      output.println("<h2>The number of vacancies in "+states[i]+"was above the national average in 2010</h2>");
    }
    else{
      output.println("<h2>The number of vacancies in "+states[i]+" was below the national average in 2010</h2>");
    }
    output.println("<p> <a href='https://www.census.gov/developers/'>More Info On The Data</a> </p>");
    //Generates svg bar graph for state data vs national data.
    output.println("<svg width=\"440\" height=\"20000\" xmlns=\"http://www.w3.org/2000/svg\">");
    output.println("<rect y='0' x='40' height='"+ vacancy2000[i]/(2*sf)+"' width='100' fill='rgb(230,90,5)'/>");
    output.println("<rect y='0' x='140' height='"+ nationalaverage2000/(2*sf)+"' width='100' fill='rgb(5,90,230)'/>");
    output.println("<rect y='0' x='240' height='"+ (vacancy2010[i]/(2*sf))+"' width='100' fill='rgb(230,90,5)'/>");
    output.println("<rect y='0' x='340' height='"+ (nationalaverage2010/(2*sf))+"' width='100' fill='rgb(5,90,230)'/>");
    output.println("<text y='"+(vacancy2000[i]/(2*sf)+40)+"' x='40' fill='green' font-size='40'>"+vacancy2000[i]+"  </text>");
    output.println("<text y='"+(vacancy2010[i]/(2*sf)+40)+"' x='240' fill='green' font-size='40'>"+vacancy2010[i]+"  </text>");
    output.println("<text y='"+(nationalaverage2000/(2*sf)+40)+"' x='140' fill='green' font-size='40'>"+nationalaverage2000+"  </text>");
    output.println("<text y='"+(nationalaverage2010/(2*sf)+40)+"' x='340' fill='green' font-size='40'>"+nationalaverage2010+"  </text>");
    output.println("<text x='0' y='-120'transform= rotate("+90+","+0+","+0+") fill='Black' font-size='20'>vacancy in 2000 </text>");
    output.println("<text x='0' y='-320'transform= rotate("+90+","+0+","+0+") fill='Black' font-size='20'>vacancy in 2010 </text>");
    output.println("<text x='0' y='-220'transform= rotate("+90+","+0+","+0+") fill='Black' font-size='20'>nationalaverage 2000 </text>");
    output.println("<text x='0' y='-420'transform= rotate("+90+","+0+","+0+") fill='Black' font-size='20'>nationalaverage 2010 </text>");
    output.println("</body></html>");
    output.flush(); // Writes the remaining data to the file
    output.close(); // Finishes the file
    println("Done");
//Creates the png colors for the dynamicly heatmap and assings them to thier
//respcetive shapes.
      background(5,65,219);
      stroke(0,0,0);
      shape(usa, -250, -150);
      fill(219,65,5);
      rect(1000,500,60,20);
      fill(0,0,0);
      text("~No Change -10<x<10", 1000, 499);
      fill(219,255,5);
      rect(940,500,60,20);
      fill(0,0,0);
      text("Increase in Vacancy x>10", 885, 530);
      fill(219,0,5);
      rect(1060,500,60,20);
      fill(0,0,0);
      text("Decrease in Vacancy x<-10", 1060, 530);
      alabama.disableStyle();
      fill(219, 65+((vacancy2000[1]-vacancy2010[1])/20),5);
      noStroke();
      shape(alabama, -250, -150);
      alaska.disableStyle();
      fill(219, 65+((vacancy2000[2]-vacancy2010[2])/20),5);
      noStroke();
      shape(alaska, -250, -150);
      arizona.disableStyle();
      fill(219, 65+((vacancy2000[3]-vacancy2010[3])/20),5);
      noStroke();
      shape(arizona, -250, -150);
      arkansas.disableStyle();
      fill(219, 65+((vacancy2000[4]-vacancy2010[4])/20),5);
      noStroke();
      shape(arkansas, -250, -150);
      california.disableStyle();
      fill(219, 65+((vacancy2000[5]-vacancy2010[5])/20),5);
      noStroke();
      shape(california, -250, -150);
      colorado.disableStyle();
      fill(219, 65+((vacancy2000[6]-vacancy2010[6])/20),5);
      noStroke();
      shape(colorado, -250, -150);
      connecticcut.disableStyle();
      fill(219, 65+((vacancy2000[7]-vacancy2010[7])/20),5);
      noStroke();
      shape(connecticcut, -250, -150);
      delaware.disableStyle();
      fill(219, 65+((vacancy2000[8]-vacancy2010[8])/20),5);
      noStroke();
      shape(delaware, -250, -150);
      districtofcolumbia.disableStyle();
      fill(219, 65+((vacancy2000[9]-vacancy2010[9])/20),5);
      noStroke();
      shape(districtofcolumbia, -250, -150);
      florida.disableStyle();
      fill(219, 65+((vacancy2000[10]-vacancy2010[10])/20),5);
      noStroke();
      shape(florida, -250, -150);
      georgia.disableStyle();
      fill(219, 65+((vacancy2000[11]-vacancy2010[11])/20),5);
      noStroke();
      shape(georgia, -250, -150);
      hawaii.disableStyle();
      fill(219, 65+((vacancy2000[12]-vacancy2010[12])/20),5);
      noStroke();
      shape(hawaii, -250, -150);
      idaho.disableStyle();
      fill(219, 65+((vacancy2000[13]-vacancy2010[13])/20),5);
      noStroke();
      shape(idaho, -250, -150);
      illinois.disableStyle();
      fill(219, 65+((vacancy2000[14]-vacancy2010[14])/20),5);
      noStroke();
      shape(illinois, -250, -150);
      indiana.disableStyle();
      fill(219, 65+((vacancy2000[15]-vacancy2010[15])/20),5);
      noStroke();
      shape(indiana, -250, -150);
      iowa.disableStyle();
      fill(219, 65+((vacancy2000[16]-vacancy2010[16])/20),5);
      noStroke();
      shape(iowa, -250, -150);
      kansas.disableStyle();
      fill(219, 65+((vacancy2000[17]-vacancy2010[17])/20),5);
      noStroke();
      shape(kansas, -250, -150);
      kentucky.disableStyle();
      fill(219, 65+((vacancy2000[18]-vacancy2010[18])/20),5);
      noStroke();
      shape(kentucky, -250, -150);
      louisiana.disableStyle();
      fill(219, 65+((vacancy2000[19]-vacancy2010[19])/20),5);
      noStroke();
      shape(louisiana, -250, -150);
      maine.disableStyle();
      fill(219, 65+((vacancy2000[20]-vacancy2010[20])/20),5);
      noStroke();
      shape(maine, -250, -150);
      maryland.disableStyle();
      fill(219, 65+((vacancy2000[21]-vacancy2010[21])/20),5);
      noStroke();
      shape(maryland, -250, -150);
      massachusetts.disableStyle();
      fill(219, 65+((vacancy2000[22]-vacancy2010[22])/20),5);
      noStroke();
      shape(massachusetts, -250, -150);
      michigan.disableStyle();
      fill(219, 65+((vacancy2000[23]-vacancy2010[23])/20),5);
      noStroke();
      shape(michigan, -250, -150);
      minnesota.disableStyle();
      fill(219, 65+((vacancy2000[24]-vacancy2010[24])/20),5);
      noStroke();
      shape(minnesota, -250, -150);
      mississippi.disableStyle();
      fill(219, 65+((vacancy2000[25]-vacancy2010[25])/20),5);
      noStroke();
      shape(mississippi, -250, -150);
      missouri.disableStyle();
      fill(219, 65+((vacancy2000[26]-vacancy2010[26])/20),5);
      noStroke();
      shape(missouri, -250, -150);
      montana.disableStyle();
      fill(219, 65+((vacancy2000[27]-vacancy2010[27])/20),5);
      noStroke();
      shape(montana, -250, -150);
      nebraska.disableStyle();
      fill(219, 65+((vacancy2000[28]-vacancy2010[28])/20),5);
      noStroke();
      shape(nebraska, -250, -150);
      nevada.disableStyle();
      fill(219, 65+((vacancy2000[29]-vacancy2010[29])/20),5);
      noStroke();
      shape(nevada, -250, -150);
      newhampshire.disableStyle();
      fill(219, 65+((vacancy2000[30]-vacancy2010[30])/20),5);
      noStroke();
      shape(newhampshire, -250, -150);
      newjersey.disableStyle();
      fill(219, 65+((vacancy2000[31]-vacancy2010[31])/20),5);
      noStroke();
      shape(newjersey, -250, -150);
      newmexico.disableStyle();
      fill(219, 65+((vacancy2000[32]-vacancy2010[32])/20),5);
      noStroke();
      shape(newmexico, -250, -150);
      newyork.disableStyle();
      fill(219, 65+((vacancy2000[33]-vacancy2010[33])/20),5);
      noStroke();
      shape(newyork, -250, -150);
      northcarolina.disableStyle();
      fill(219, 65+((vacancy2000[34]-vacancy2010[34])/20),5);
      noStroke();
      shape(northcarolina, -250, -150);
      northdakota.disableStyle();
      fill(219, 65+((vacancy2000[35]-vacancy2010[35])/20),5);
      noStroke();
      shape(northdakota, -250, -150);
      ohio.disableStyle();
      fill(219, 65+((vacancy2000[36]-vacancy2010[36])/20),5);
      noStroke();
      shape(ohio, -250, -150);
      oklahoma.disableStyle();
      fill(219, 65+((vacancy2000[37]-vacancy2010[37])/20),5);
      noStroke();
      shape(oklahoma, -250, -150);
      oregon.disableStyle();
      fill(219, 65+((vacancy2000[38]-vacancy2010[38])/20),5);
      noStroke();
      shape(oregon, -250, -150);
      pennsylvania.disableStyle();
      fill(219, 65+((vacancy2000[39]-vacancy2010[39])/20),5);
      noStroke();
      shape(pennsylvania, -250, -150);
      rhodeisland.disableStyle();
      fill(219, 65+((vacancy2000[40]-vacancy2010[40])/20),5);
      noStroke();
      shape(rhodeisland, -250, -150);
      southcarolina.disableStyle();
      fill(219, 65+((vacancy2000[41]-vacancy2010[41])/20),5);
      noStroke();
      shape(southcarolina, -250, -150);
      southdakota.disableStyle();
      fill(219, 65+((vacancy2000[42]-vacancy2010[42])/20),5);
      noStroke();
      shape(southdakota, -250, -150);
      tennessee.disableStyle();
      fill(219, 65+((vacancy2000[43]-vacancy2010[43])/20),5);
      noStroke();
      shape(tennessee, -250, -150);
      texas.disableStyle();
      fill(219, 65+((vacancy2000[44]-vacancy2010[44])/20),5);
      noStroke();
      shape(texas, -250, -150);
      utah.disableStyle();
      fill(219, 65+((vacancy2000[45]-vacancy2010[45])/20),5);
      noStroke();
      shape(utah, -250, -150);
      vermont.disableStyle();
      fill(219, 65+((vacancy2000[46]-vacancy2010[46])/20),5);
      noStroke();
      shape(vermont, -250, -150);
      virginia.disableStyle();
      fill(219, 65+((vacancy2000[47]-vacancy2010[47])/20),5);
      noStroke();
      shape(virginia, -250, -150);
      washington.disableStyle();
      fill(219, 65+((vacancy2000[48]-vacancy2010[48])/20),5);
      noStroke();
      shape(washington, -250, -150);
      westvirginia.disableStyle();
      fill(219, 65+((vacancy2000[49]-vacancy2010[49])/20),5);
      noStroke();
      shape(westvirginia, -250, -150);
      wisconsin.disableStyle();
      fill(219, 65+((vacancy2000[50]-vacancy2010[50])/20),5);
      noStroke();
      shape(wisconsin, -250, -150);
      wyoming.disableStyle();
      fill(219, 65+((vacancy2000[51]-vacancy2010[51])/20),5);
      noStroke();
      shape(wyoming, -250, -150);
    save("heatmap.png");

  }
}
