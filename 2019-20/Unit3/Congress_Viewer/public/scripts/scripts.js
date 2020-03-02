
// Class
document.getElementById("senateclick").addEventListener("click", hidesenate);
function hidesenate(){
  document.getElementById("senate").style.visibility = "visible";
  document.getElementById("house").style.visibility = "hidden";
}
document.getElementById("houseclick").addEventListener("click", hidesenate);
function hidesenate(){
  document.getElementById("house").style.visibility = "visible";
  document.getElementById("senate").style.visibility = "hidden";
}
