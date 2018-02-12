PrintWriter output;

void setup() {
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
