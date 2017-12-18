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
//statistics in for of table
var div = createDiv("DATA");
var datatype =["# OF Words","# OF Lines","# OF Characters","# OF Sentances","# OF Characters without spaces",
  "# OF Characters without punctuation","averagewordlength","characters/sentance","words/sentence","longest Word",
  "# of unique words","Text Richness"];
var data =[(WordCount(file.data)+LineCount(file.data)),(LineCount(file.data)),
  (CharacterCount1(file.data)),(sentanceCount(file.data)),(CharacterCount2(file.data)),
  (CharacterCount3(file.data)),(averagewordlength(file.data)),(characterssentence(file.data)),
  (wordssentence(file.data)),(longestWord(file.data)),(unique(file.data)),(vocabrich(file.data))];

if(datatype.length != data.length){
createDiv("<h1>"+'Your Datatype and Data Are have different lengths'+"</h1>");
}

var table = "<table><tr><th>Type Of Data</th><th>Data</th></tr>";
for (var i = 0; i < datatype.length; i++) {
  table += ("<tr><td>"+datatype[i]+"</td><td> "+data[i]+"</td></tr>");
}
table += "</table>";
div.html(table);
//returns statistics about text.
/*
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
createDiv("<h1>"+'# of unique words = '+((unique(file.data)))+"</h1>");
createDiv("<h1>"+'Text Richness = '+((vocabrich(file.data)))+"</h1>");*/
getFrequency(file.data);
getFrequency2(file.data);
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
function vocabrich(str){
return unique(str)/WordCount(str)
}
//removes punctuation for future functions.
function punctuationfree(string) {
//  var chars = {'.':' ','!':' ','?':' ',',':' ','\n':' ','-':' ','_':' ',';':' ',':':' ','[':' ',']':' ','"':' ','—':' ','—':' ','\m':' '};
  string = string.replace(/[!?'—''-''_',—;:.\n\M]/g, ' '); //m => chars[m]);

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
    if(!(tokens[i] == ' ') && !(tokens[i] == '\s')){
     console.log(tokens[i]);
      if (concordance[word] == undefined) {
        concordance[word] = 1;
        keys.push(word);
      } else {
        concordance[word]++;
      }
    }
  }
  keys.sort(function(a, b) {
    return (concordance[b] - concordance[a]);
  });
  for (var i = 0; i < min(keys.length,20); i++) {
    table += ("<tr><td>"+keys[i]+"</td><td> "+concordance[keys[i]]+"</td><td> "+nf(concordance[keys[i]]/tokens.length*100,2,2)+ "%" + "</td></tr>")
  }
  table += "</table>";
  div.html(table);
}

function unique(string) {
  var concordance = {}; //empty object
  var tokens = punctuationfree(string).split(' ');
  var keys = [];
  for (var i = 0; i < tokens.length; i++) {
    var word = tokens[i].toLowerCase();
    if (concordance[word] == undefined) {
      concordance[word] = 1;
    keys.push(word);
    } else {
      concordance[word]++;
    }
  }
  keys.sort(function(a, b) {
    return (concordance[b] - concordance[a]);
  });
return keys.length;
}
function removeStopwords(str) {

    var stopwords = ["a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any","are","aren't","as","at","be","because","been","before","being","below"
    ,"between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further",
    "had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm",
    "i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only",
    "or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than",
    "that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under",
    "until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's"
    ,"with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"];
    str = str.replace(new RegExp('('+stopwords.join('|')+')', 'g'), 'xxxxxxxx');
//replace(/[!?'—''-''_',—;:.\n\m\s]/g, m => ' ');
return str;
}
function getFrequency2(string) {
  var string2 = punctuationfree(string);
  var tokens = removeStopwords(string2).split(' ');
  var div = createDiv("Frequency");
  var table = "<table><tr><th>Words Without Stop Words</th><th>Occurences</th><th>Precentage</th></tr>";
  var concordance = {}; //empty object

  var keys = [];
  for (var i = 0; i < tokens.length; i++) {
    var word = tokens[i].toLowerCase();
  if(!(tokens[i] == ' ') && !(tokens[i] == '\s')){
    console.log(tokens[i]);
    if (concordance[word] == undefined) {
      concordance[word] = 1;
    keys.push(word);
    } else {
      concordance[word]++;
    }
  }
}
  keys.sort(function(a, b) {
    return (concordance[b] - concordance[a]);
  });
  for (var i = 0; i < min(keys.length,20); i++) {
    table += ("<tr><td>"+keys[i]+"</td><td> "+concordance[keys[i]]+"</td><td> "+nf(concordance[keys[i]]/tokens.length*100,2,2)+ "%" + "</td></tr>")
  }
  table += "</table>";
  div.html(table);
}
