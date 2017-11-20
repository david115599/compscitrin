
function setup(){
  noCanvas();
  createFileInput(gotFile, 'multiple');
WordCount();
}


function gotFile(file) {
  createDiv("<h1>"+file.name + ' ' + file.type + ' ' + file.size + 'bytes'+"</h1>");

  // Handle image and text differently
  if (file.type === 'image') {
    createImg(file.data);
  } else if (file.type === 'text') {
    createDiv(file.data);
  }
}


function WordCount(str) {
  return str.split(" ").length;
}

console.log(WordCount("akdf ad fdas adf ad f"));
