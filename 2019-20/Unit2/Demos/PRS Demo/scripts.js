var rules_button=document.getElementById("show_rules_button");
rules_button.addEventListener("click", function(){
  var rules_div = document.getElementById("rules");

  if(rules_div.classList.contains("hidden")){
    rules_div.classList.remove("hidden");
    rules_div.classList.add("visible");
  }else{
    rules_div.classList.remove("visible");
    rules_div.classList.add("hidden");
    }
  console.log(rules_div)
});
