import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class STATS extends PApplet {

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
String states[];
int vacancy2000[]=new int[52];
int vacancy2010[]=new int[52];
int national2000;
int national2010;
PrintWriter output;
int sf =5;
public void setup() {
  
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
  for (int i=0; i<vacancy2000.length; i++ ) {
    national2000+=vacancy2000[i];
  }
  for (int i=0; i<vacancy2010.length; i++ ) {
    national2010+=vacancy2010[i];
  }
  for (int i = 0; i<states.length; i++) {
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
public void draw() {
  background(255);
  shape(usa, -250, 0);

  alabama.disableStyle();
  fill(50, 0, 0+((vacancy2000[1]-vacancy2010[1])/20));
  noStroke();
  shape(alabama, -250, 0);
  alaska.disableStyle();
  fill(50, 0, 0+((vacancy2000[2]-vacancy2010[2])/20));
  noStroke();
  shape(alaska, -250, 0);
  arizona.disableStyle();
  fill(50, 0, 0+((vacancy2000[3]-vacancy2010[3])/20));
  noStroke();
  shape(arizona, -250, 0);
  arkansas.disableStyle();
  fill(50, 0, 0+((vacancy2000[4]-vacancy2010[4])/20));
  noStroke();
  shape(arkansas, -250, 0);
  california.disableStyle();
  fill(50, 0, 0+((vacancy2000[5]-vacancy2010[5])/20));
  noStroke();
  shape(california, -250, 0);
  colorado.disableStyle();
  fill(50, 0, 0+((vacancy2000[6]-vacancy2010[6])/20));
  noStroke();
  shape(colorado, -250, 0);
  connecticcut.disableStyle();
  fill(50, 0, 0+((vacancy2000[7]-vacancy2010[7])/20));
  noStroke();
  shape(connecticcut, -250, 0);
  delaware.disableStyle();
  fill(50, 0, 0+((vacancy2000[8]-vacancy2010[8])/20));
  noStroke();
  shape(delaware, -250, 0);
  districtofcolumbia.disableStyle();
  fill(50, 0, 0+((vacancy2000[9]-vacancy2010[9])/20));
  noStroke();
  shape(districtofcolumbia, -250, 0);
  florida.disableStyle();
  fill(50, 0, 0+((vacancy2000[10]-vacancy2010[10])/20));
  noStroke();
  shape(florida, -250, 0);
  georgia.disableStyle();
  fill(50, 0, 0+((vacancy2000[11]-vacancy2010[11])/20));
  noStroke();
  shape(georgia, -250, 0);
  hawaii.disableStyle();
  fill(50, 0, 0+((vacancy2000[12]-vacancy2010[12])/20));
  noStroke();
  shape(hawaii, -250, 0);
  idaho.disableStyle();
  fill(50, 0, 0+((vacancy2000[13]-vacancy2010[13])/20));
  noStroke();
  shape(idaho, -250, 0);
  illinois.disableStyle();
  fill(50, 0, 0+((vacancy2000[14]-vacancy2010[14])/20));
  noStroke();
  shape(illinois, -250, 0);
  indiana.disableStyle();
  fill(50, 0, 0+((vacancy2000[15]-vacancy2010[15])/20));
  noStroke();
  shape(indiana, -250, 0);
  iowa.disableStyle();
  fill(50, 0, 0+((vacancy2000[16]-vacancy2010[16])/20));
  noStroke();
  shape(iowa, -250, 0);
  kansas.disableStyle();
  fill(50, 0, 0+((vacancy2000[17]-vacancy2010[17])/20));
  noStroke();
  shape(kansas, -250, 0);
  kentucky.disableStyle();
  fill(50, 0, 0+((vacancy2000[18]-vacancy2010[18])/20));
  noStroke();
  shape(kentucky, -250, 0);
  louisiana.disableStyle();
  fill(50, 0, 0+((vacancy2000[19]-vacancy2010[19])/20));
  noStroke();
  shape(louisiana, -250, 0);
  maine.disableStyle();
  fill(50, 0, 0+((vacancy2000[20]-vacancy2010[20])/20));
  noStroke();
  shape(maine, -250, 0);
  maryland.disableStyle();
  fill(50, 0, 0+((vacancy2000[21]-vacancy2010[21])/20));
  noStroke();
  shape(maryland, -250, 0);
  massachusetts.disableStyle();
  fill(50, 0, 0+((vacancy2000[22]-vacancy2010[22])/20));
  noStroke();
  shape(massachusetts, -250, 0);
  michigan.disableStyle();
  fill(50, 0, 0+((vacancy2000[23]-vacancy2010[23])/20));
  noStroke();
  shape(michigan, -250, 0);
  minnesota.disableStyle();
  fill(50, 0, 0+((vacancy2000[24]-vacancy2010[24])/20));
  noStroke();
  shape(minnesota, -250, 0);
  mississippi.disableStyle();
  fill(50, 0, 0+((vacancy2000[25]-vacancy2010[25])/20));
  noStroke();
  shape(mississippi, -250, 0);
  missouri.disableStyle();
  fill(50, 0, 0+((vacancy2000[26]-vacancy2010[26])/20));
  noStroke();
  shape(missouri, -250, 0);
  montana.disableStyle();
  fill(50, 0, 0+((vacancy2000[27]-vacancy2010[27])/20));
  noStroke();
  shape(montana, -250, 0);
  nebraska.disableStyle();
  fill(50, 0, 0+((vacancy2000[28]-vacancy2010[28])/20));
  noStroke();
  shape(nebraska, -250, 0);
  nevada.disableStyle();
  fill(50, 0, 0+((vacancy2000[29]-vacancy2010[29])/20));
  noStroke();
  shape(nevada, -250, 0);
  newhampshire.disableStyle();
  fill(50, 0, 0+((vacancy2000[30]-vacancy2010[30])/20));
  noStroke();
  shape(newhampshire, -250, 0);
  newjersey.disableStyle();
  fill(50, 0, 0+((vacancy2000[31]-vacancy2010[31])/20));
  noStroke();
  shape(newjersey, -250, 0);
  newmexico.disableStyle();
  fill(50, 0, 0+((vacancy2000[32]-vacancy2010[32])/20));
  noStroke();
  shape(newmexico, -250, 0);
  newyork.disableStyle();
  fill(50, 0, 0+((vacancy2000[33]-vacancy2010[33])/20));
  noStroke();
  shape(newyork, -250, 0);
  northcarolina.disableStyle();
  fill(50, 0, 0+((vacancy2000[34]-vacancy2010[34])/20));
  noStroke();
  shape(northcarolina, -250, 0);
  northdakota.disableStyle();
  fill(50, 0, 0+((vacancy2000[35]-vacancy2010[35])/20));
  noStroke();
  shape(northdakota, -250, 0);
  ohio.disableStyle();
  fill(50, 0, 0+((vacancy2000[36]-vacancy2010[36])/20));
  noStroke();
  shape(ohio, -250, 0);
  oklahoma.disableStyle();
  fill(50, 0, 0+((vacancy2000[37]-vacancy2010[37])/20));
  noStroke();
  shape(oklahoma, -250, 0);
  oregon.disableStyle();
  fill(50, 0, 0+((vacancy2000[38]-vacancy2010[38])/20));
  noStroke();
  shape(oregon, -250, 0);
  pennsylvania.disableStyle();
  fill(50, 0, 0+((vacancy2000[39]-vacancy2010[39])/20));
  noStroke();
  shape(pennsylvania, -250, 0);
  rhodeisland.disableStyle();
  fill(50, 0, 0+((vacancy2000[40]-vacancy2010[40])/20));
  noStroke();
  shape(rhodeisland, -250, 0);
  southcarolina.disableStyle();
  fill(50, 0, 0+((vacancy2000[41]-vacancy2010[41])/20));
  noStroke();
  shape(southcarolina, -250, 0);
  southdakota.disableStyle();
  fill(50, 0, 0+((vacancy2000[42]-vacancy2010[42])/20));
  noStroke();
  shape(southdakota, -250, 0);
  tennessee.disableStyle();
  fill(50, 0, 0+((vacancy2000[43]-vacancy2010[43])/20));
  noStroke();
  shape(tennessee, -250, 0);
  texas.disableStyle();
  fill(50, 0, 0+((vacancy2000[44]-vacancy2010[44])/20));
  noStroke();
  shape(texas, -250, 0);
  utah.disableStyle();
  fill(50, 0, 0+((vacancy2000[45]-vacancy2010[45])/20));
  noStroke();
  shape(utah, -250, 0);
  vermont.disableStyle();
  fill(50, 0, 0+((vacancy2000[46]-vacancy2010[46])/20));
  noStroke();
  shape(vermont, -250, 0);
  virginia.disableStyle();
  fill(50, 0, 0+((vacancy2000[47]-vacancy2010[47])/20));
  noStroke();
  shape(virginia, -250, 0);
  washington.disableStyle();
  fill(50, 0, 0+((vacancy2000[48]-vacancy2010[48])/20));
  noStroke();
  shape(washington, -250, 0);
  westvirginia.disableStyle();
  fill(50, 0, 0+((vacancy2000[49]-vacancy2010[49])/20));
  noStroke();
  shape(westvirginia, -250, 0);
  wisconsin.disableStyle();
  fill(50, 0, 0+((vacancy2000[50]-vacancy2010[50])/20));
  noStroke();
  shape(wisconsin, -250, 0);
  wyoming.disableStyle();
  fill(50, 0, 0+((vacancy2000[51]-vacancy2010[51])/20));
  noStroke();
  shape(wyoming, -250, 0);

}
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "STATS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
