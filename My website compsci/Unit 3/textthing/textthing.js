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
createDiv("<h1>"+'# OF Sentaces ='+((sentanceCount(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Characters without spaces ='+((CharacterCount2(file.data)))+"</h1>");
createDiv("<h1>"+'# OF Characters without punctuation ='+((CharacterCount3(file.data)))+"</h1>");
createDiv("<h1>"+'averagewordlength ='+((averagewordlength(file.data)))+"</h1>");
createDiv("<h1>"+'characters/sentence ='+((characterssentence(file.data)))+"</h1>");
createDiv("<h1>"+'words/sentence ='+((wordssentence(file.data)))+"</h1>");

//  createDiv("<h1>"+'punctuation free = '+((punctuationfree(file.data)))+"</h1>");
createDiv("<h1>"+'longest Word = '+((longestWord(file.data)))+"</h1>");
createDiv("<h1>"+'# of unique words = '+((getFrequency(file.data)))+"</h1>");

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
function sentanceCount(str) {
  return str.split(".").length+str.split("!").length+str.split("?").length;
}
function characterssentence(str) {
  return CharacterCount3(str)/sentanceCount(str);
}
function wordssentence(str) {
  return WordCount(str)/sentanceCount(str);
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
  var chars = {'.':' ','!':'','?':'',',':'','\n':' ','-':' ','_':' ',';':'',':':'','[':'',']':'','"':'','—':' '};
  string = string.replace(/[!?'—''-''_',;[\]:.\n"]/g, m => chars[m]);

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
  var keys = [];
  for (var i = 0; i < tokens.length; i++) {
    var word = tokens[i].toLowerCase();
    if (concordance[word] === undefined) {
      concordance[word] = 1;
    keys.push(word);
    } else {
      concordance[word]++;
    }
  }
  keys.sort(function(a, b) {
    return (concordance[b] - concordance[a]);
  });
  for (var i = 0; i < keys.length; i++) {
    table += ("<tr><td>"+keys[i]+"</td><td> "+concordance[keys[i]]+"</td><td> "+nf(concordance[keys[i]]/tokens.length*100,2,2)+ "%" + "</td></tr>")
  }
  table += "</table>";
  div.html(table);
return keys.length;
}
