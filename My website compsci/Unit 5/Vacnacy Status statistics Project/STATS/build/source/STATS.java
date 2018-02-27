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
    output.println("<a href='website/"+state+".html'>"+state+"</a>");}
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    int pop1 = json1.getJSONArray(i).getInt(0)/sf;}
  output.println("<svg width=\"2000\" height=\"15000\" xmlns=\"http://www.w3.org/2000/svg\">");
  //load JSON into arrays (state,pop2010)
  for (int i = 1; i < json.size(); i++) {
    String state = json.getJSONArray(i).getString(1);
    int pop = json.getJSONArray(i).getInt(0)/sf;
    output.println("<rect y='0' x='"+ (i*20-10) +"' height='"+ pop+"' width='10' fill='darkred'/>");
    output.println("<text x='"+pop+"' y='"+ -(i*20-8) + "'transform= rotate("+90+","+0+","+0+") fill='green' font-size='10'>"+state+"  </text>");
    println(state + ": " + pop);
  }
  for (int i = 1; i < json1.size(); i++) {
    String state = json1.getJSONArray(i).getString(1);
    int pop1 = json1.getJSONArray(i).getInt(0)/sf;
    output.println("<rect y='0' x='"+ (i*20) +"' height='"+ (pop1)+"' width='10' fill='darkblue'/>");
    output.println("<text x='"+pop1+"' y='"+ -(i*20) + "'transform= rotate("+90+","+0+","+0+") fill='green' font-size='10'>"+state+"  </text>");
    println(state + ": " + pop1);
  }
  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
  String states[] = loadStrings("State_Names.txt");
  for(int i = 0; i<states.length; i++){
  output = createWriter("website/"+states[i]+".html");
  output.println("<html><head>");
  output.println("<title>" + states[i] + "</title>");
  output.println("</head><body>");
  output.println("<h1>" + states[i] + "</h1>");



  output.println("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis tortor ullamcorper, ultricies nunc at, varius libero. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla rutrum accumsan lectus vel bibendum. Ut sed hendrerit eros, et finibus ipsum. Maecenas eros magna, placerat at mi vel, sagittis lobortis sapien. Donec at dignissim elit, in egestas enim. Suspendisse condimentum, magna non porta hendrerit, lorem mauris tempor justo, sed imperdiet libero metus et nisi. Etiam eu orci et sem vulputate vestibulum dapibus in ipsum. Ut mi mauris, mollis quis condimentum eget, commodo nec felis. In varius augue viverra erat consequat convallis.</p>");
  output.println("</body></html>");
  output.flush(); // Writes the remaining data to the file
  output.close(); // Finishes the file
  println("Done");
  }
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
