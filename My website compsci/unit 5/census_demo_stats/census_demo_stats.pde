//2010 Census Demo using JSON (https://www.json.org/)
//2010 variables: https://api.census.gov/data/2010/sf1/variables.html
//H0110004: renter occupied
//* = wildcard (matches anything)
//query: https://api.census.gov/data/2010/sf1?key=e8a63d6bac96233cd5c3cf2de348ed9882285b0a&get=H0110004,NAME&for=state:*
//results saved to: sf1.json (2D array)
float avg2010 = 0 ;

String [] states = new String[52];
int [] pop2010 = new int[52];

void setup() {
  JSONArray json;
  json = loadJSONArray("sf1.json");
  println(json.size());

  for (int i = 1; i < json.size(); i++) {
    states[i-1] = json.getJSONArray(i).getString(1);
    pop2010[i-1] = json.getJSONArray(i).getInt(0);
    println(states[i-1] +" had " + pop2010[i-1] +" renters in 2010");
    avg2010 += pop2010[i-1];
  }
  avg2010/= json.size()-1;
  println("national average:" + avg2010);
  for (int i =0; i< json.size()-1; i++) {
    println(states[i]+" had "+pop2010[i]+" renters in 2010");
    if (pop2010[i] > avg2010)
      println(states[i]+" has more renters than the national average.");
    else
      println(states[i]+" has less renters than the national average.");
  }
  //How can we calculate the national average?
  //How can we compare each state to the national average?
}
