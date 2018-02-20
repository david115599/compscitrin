//Vacnacy Status statistics For Migrant Workers--These include vacant units intended for occupancy by migratory workers employed in farm work during the crop season. (Work in a cannery, a freezer plant, or a food-processing plant is not farm work.)
float avg2010 = 0 ;

String [] states = new String[52];
int [] vacancy2010 = new int[52];

void setup() {
  JSONArray json;
  json = loadJSONArray("2010sf1.json");
  //println(json.size());

  for (int i = 1; i < json.size(); i++) {
    states[i-1] = json.getJSONArray(i).getString(1);
    vacancy2010[i-1] = json.getJSONArray(i).getInt(0);
    println(states[i-1] +" had " + vacancy2010[i-1] +" vacant housing units for migrants in 2010");
    avg2010 += vacancy2010[i-1];
  }
  avg2010/= json.size()-1;
  println("national average:" + avg2010);
  for (int i =0; i< json.size()-1; i++) {
    println(states[i]+" had "+vacancy2010[i]+" vacant housing units for migrants in 2010");
    if (vacancy2010[i] > avg2010){
      println(states[i]+" has more vacant housing units for migrants than the national average.");}
    else{
      println(states[i]+" has less vacant housing units for migrants than the national average.");}
  }
}
