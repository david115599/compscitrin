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
var datatype =("# OF Words","# OF Lines","# OF Characters","# OF Sentaces","# OF Characters without spaces","# OF Characters without punctuatio","averagewordlength","characters/sentence","words/sentence","longest Word","# of unique words","Text Richness")
var data =((WordCount(file.data)+LineCount(file.data)),(LineCount(file.data)),(CharacterCount1(file.data)),(sentanceCount(file.data)),(CharacterCount2(file.data)),(CharacterCount3(file.data)),(averagewordlength(file.data)),(characterssentence(file.data)),(wordssentence(file.data)),(vocabrich(file.data)),(longestWord(file.data)))
var table = "<table><tr><th>Type Of Data</th><th>Data</th></tr>";
for (var i = 0; i < 20; i++) {
  table += ("<tr><td>"+datatype[i]+"</td><td> "+data[i]+"</td></tr>")
}
table += "</table>";
div.html(table);
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
createDiv("<h1>"+'# of unique words = '+((unique(file.data)))+"</h1>");
createDiv("<h1>"+'Text Richness = '+((vocabrich(file.data)))+"</h1>");
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
  var chars = {'.':' ','!':'','?':'',',':'','\n':' ','-':' ','_':' ',';':'',':':'','[':'',']':'','"':'','—':' ','—':' '};
  string = string.replace(/[!?'—''-''_',—;[\]:.\n"]/g, m => chars[m]);

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
  for (var i = 0; i < 20; i++) {
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
return keys.length;
}
function removeStopwords(str) {

    var stopwords = ["I","but","and","like","did","it","a", "about", "above", "above", "across", "after",
  "afterwards", "again", "against", "all", "almost", "alone", "along",
  "already", "also","although","always","am","among", "amongst",
  "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone",
  "anything","anyway", "anywhere", "are", "around", "as",  "at", "back",
  "be","became", "because","become","becomes", "becoming", "been",
  "before", "beforehand", "behind", "being", "below", "beside",
  "besides", "between", "beyond", "bill", "both", "bottom","but",
  "by", "call", "can", "cannot", "cant", "co", "con", "could",
  "couldnt", "cry", "de", "describe", "detail",  "do", "does", "done",  "down",
  "due", "during", "each", "eg", "eight", "either", "eleven","else",
  "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
  "everyone", "everything", "everywhere", "except", "few", "fifteen",
  "fify", "fill", "find", "fire", "first", "five", "for", "former",
  "formerly", "forty", "found", "four", "from", "front", "full",
  "further", "get", "give", "go", "had", "has", "hasnt", "have",
  "he", "hence", "her", "here", "hereafter", "hereby", "herein",
  "hereupon", "hers", "herself", "him", "himself", "his", "how",
  "however", "hundred", "ie", "if", "in", "inc", "indeed",
  "interest", "into", "is",  "it", "its", "itself", "keep",
  "last", "latter", "latterly", "least", "less", "ltd", "made",
  "many", "may", "me", "meanwhile", "might", "mill", "mine",
  "more", "moreover", "most", "mostly", "move", "much", "must", "my",
  "myself", "name", "namely", "neither", "never", "nevertheless", "next",
  "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now",
  "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto",
  "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out",
  "over", "own","part", "per", "perhaps", "please", "put", "rather", "re",
  "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several",
  "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so",
  "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere",
  "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them",
  "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore",
  "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this",
  "those", "though", "three", "through", "throughout", "thru", "thus", "to",
  "together", "too", "top", "toward", "towards", "twelve", "twenty", "two",
  "un", "under", "until", "up", "upon", "us", "very", "via", "was", "way", "we",
  "well", "were", "what", "whatever", "when", "whence", "whenever", "where",
  "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever",
  "whether", "which", "while", "whither", "who", "whoever", "whole", "whom",
  "whose", "why", "will", "with", "within", "without", "would", "yet", "you",
  "your", "yours", "yourself", "yourselves", "the",
  // contractions?
  "didnt", "doesnt", "dont", "isnt", "wasnt", "youre", "hes", "ive", "theyll",
  "whos", "wheres", "whens", "whys", "hows", "whats", "were", "shes", "im", "thats"
  ];
    str = str.replace(new RegExp('\\b('+stopwords.join('|')+')\\b', 'g'), '');
return str;
}
function getFrequency2(string2) {
  var string = removeStopwords(string2);
  var div = createDiv("Frequency");
  var table = "<table><tr><th>Words Without Stop Words</th><th>Occurences</th><th>Precentage</th></tr>";
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
  for (var i = 0; i < 20; i++) {
    table += ("<tr><td>"+keys[i]+"</td><td> "+concordance[keys[i]]+"</td><td> "+nf(concordance[keys[i]]/tokens.length*100,2,2)+ "%" + "</td></tr>")
  }
  table += "</table>";
  div.html(table);
}
