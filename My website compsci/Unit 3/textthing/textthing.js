
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
  createDiv("<h2>"+'# OF Words ='+(WordCount(file.data))+"</h2>");
  createDiv("<h2>"+'# OF Lines ='+((LineCount(file.data))-1)+"</h2>");
}

function WordCount(str) {
  return str.split(" ").length;
}
function LineCount(str) {
  return str.split("\n").length;
}
