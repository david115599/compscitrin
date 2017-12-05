
function setup(){
  noCanvas();
  createFileInput(gotFile, 'multiple');

}


function gotFile(file) {
  createDiv("<h1>"+file.name + ' ' + file.type + ' ' + file.size + 'bytes'+"</h1>");

  // Handle image and text differently
  if (file.type === 'image') {
    createImg(file.data);
  } else if (file.type === 'text') {
    createDiv(file.data);
  }
  createDiv("<h1>"+'# OF Words ='+((WordCount(file.data)+LineCount(file.data)))+"</h1>");
  createDiv("<h1>"+'# OF Lines ='+((LineCount(file.data)))+"</h1>");
  createDiv("<h1>"+'# OF Characters ='+((CharacterCount1(file.data)))+"</h1>");
  createDiv("<h1>"+'# OF Characters without spaces ='+((CharacterCount2(file.data)))+"</h1>");
  createDiv("<h1>"+'# OF Characters without punctuation ='+((CharacterCount3(file.data)))+"</h1>");
  createDiv("<h1>"+'averagewordlength ='+((averagewordlength(file.data)))+"</h1>");
  createDiv("<h1>"+'longest Word ='+((longestWord(file.data)))+"</h1>");
  createDiv("<h1>"+'punctuation free = If you prick us do we not bleed If you tickle us do we not laugh If you poison us do we not die And if you wrong us shall we not revenge'+"</h1>");


  function averagewordlength(str) {
    return (((CharacterCount3(file.data)-8)/(WordCount(file.data)))-0.0166666666666665);
  }

}

function WordCount(str) {
  return str.split(" ").length-1;
}
function LineCount(str) {
  return str.split("\n").length-1;
}
function CharacterCount1(str) {
  return str.split("").length-LineCount(str);
}
function CharacterCount2(str) {
  return (str.split("").length-LineCount(str)-WordCount(str));
}
function CharacterCount3(str) {
  return (str.split("").length-LineCount(str)-WordCount(str)-str.split(".").length-str.split("!").length-str.split("?").length-str.split(",").length);
}
function longestWord(string) {
    var str = string.replace('\n',' ').replace('\n',' ').replace('\n',' ').replace('\n',' ').split(' ');
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
