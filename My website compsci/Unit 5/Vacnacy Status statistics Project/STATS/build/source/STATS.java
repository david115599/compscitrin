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
PrintWriter output;
int sf =5;
public void setup() {
  JSONArray json;
  JSONArray json1;
  json = loadJSONArray("2010sf1.json");
  json1 = loadJSONArray("2000sf1.json");
  output = createWriter("HOME.html");
  output.println("<html><head>");
  output.println("<title>Vacancy Status Statistics</title>");
  output.println("</head><body>");
  output.println("<h1>Migrant Vacancy Status Statistics in US Between 2000 and 2010</h1>");
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    int pop = json.getJSONArray(i).getInt(0)/sf;
    output.println("<a href='wasteland/census_html_demo/website/"+state+".html'>"+state+"</a>");}
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    int pop1 = json1.getJSONArray(i).getInt(0)/sf;}
  output.println("<svg width=\"1000\" height=\"15000\" xmlns=\"http://www.w3.org/2000/svg\">");
  //load JSON into arrays (state,pop2010)
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    int pop = json.getJSONArray(i).getInt(0)/sf;
    output.println("<rect y='0' x='"+ (i*20-10) +"' height='"+ pop+"' width='10' fill='darkblue'/>");
    output.println("<text x='"+pop+"' y='"+ -(i*20-8) + "'transform= rotate("+90+","+0+","+0+") fill='blue' font-size='8'>"+state+"  </text>");
    println(state + ": " + pop);
  }
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    int pop1 = json1.getJSONArray(i).getInt(0)/sf;
    output.println("<rect y='0' x='"+ (i*20) +"' height='"+ (pop1)+"' width='10' fill='darkred'/>");
    output.println("<text x='"+pop1+"' y='"+ -(i*20) + "'transform= rotate("+90+","+0+","+0+") fill='darkred' font-size='8'>"+state+"  </text>");
    println(state + ": " + pop1);
  }
  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "STATS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
