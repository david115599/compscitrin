//this program parses text and returns statistics about it.
//by David Bershadsky

function setup(){
  noCanvas();
  createFileInput(gotFile, 'multiple');
}

//imports text
function gotFile(file) {
  createDiv("<h1>"+file.name + ' ' + file.type + ' ' + file.size + 'bytes'+"</h1>");

  // Handle image and text differently
  /*if (file.type === 'image') {
  createImg(file.data);
} else if (file.type === 'text') {
createDiv(file.data);
}*/
//returns statistics about text.
createDiv("<h1>"+'# OF Words ='+((WordCount(file.data)+LineCount(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Lines ='+((LineCount(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Characters ='+((CharacterCount1(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Characters without spaces ='+((CharacterCount2(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Characters without punctuation ='+((CharacterCount3(file.data)))+"</h1>");
createDiv("<h1>"+'averagewordlength ='+((averagewordlength(file.data)))+"</h1>");
//  createDiv("<h1>"+'punctuation free = '+((punctuationfree(file.data)))+"</h1>");
createDiv("<h1>"+'longest Word = '+((longestWord(file.data)))+"</h1>");
(getFrequency(file.data));

}

//returns the number of words.
function WordCount(str) {
  return str.split(" ").length-1;
}
//returns the number of lines.
function LineCount(str) {
  return str.split("\n").length-1;
}
//returns the number of number of Characters.
function CharacterCount1(str) {
  return str.split("").length-LineCount(str);
}
//returns the number of number of Characters without spaces.
function CharacterCount2(str) {
  return (str.split("").length-LineCount(str)-WordCount(str));
}
//returns the number of number of Characters without spaces and without punctuation.
function CharacterCount3(str) {
  return (str.split("").length-LineCount(str)-WordCount(str)-str.split(".").length-str.split("!").length-str.split("?").length-str.split(",").length);
}
//returns the average word length
function averagewordlength(str) {
  return (((CharacterCount3(str))/(WordCount(str)+LineCount(str))));
}
//removes punctuation for future functions.
function punctuationfree(string) {
  var chars = {'.':' ','!':'','?':'',',':'','\n':' ','–':' ','_':' ',';':'',':':'','[':'',']':'','"':''};
  string = string.replace(/[!?'—'''_',;[\]:.\n"]/g, m => chars[m]);

  return string;
}
//returns the longest word.
function longestWord(string) {
  var str = punctuationfree(string).split(' ');
  var longest = 0;
  var word = null;
  for (var i = 0; i < str.length - 1; i++) {
    if (longest < str[i].length) {
      longest = str[i].length;
      word = str[i];
    }
  }
  return word;
}
//returns the table with word Frequency.
function getFrequency(string) {
  var div = createDiv("Frequency");
  var table = "<table><tr><th>Words</th><th>Occurences</th><th>Precentage</th></tr>";
  var concordance = {}; //empty object
  var tokens = punctuationfree(string).split(' ');
  for (var i = 0; i < tokens.length; i++) {
    var word = tokens[i].toLowerCase();
    if (concordance[word] === undefined) {
      concordance[word] = 1;
    } else {
      concordance[word]++;
    }
  }

  for (var k in concordance) {

    table += ("<tr><td>"+k+"</td><td> "+concordance[k]+"</td><td> "+nf(concordance[k]/tokens.length*100,2,2)+ "%" + "</td></tr>")
  }
  table += "</table>";
  div.html(table)
}
