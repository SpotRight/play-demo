/***********************************************
* Limit number of checked checkboxes script- by JavaScript Kit (www.javascriptkit.com)
* This notice must stay intact for usage
* Visit JavaScript Kit at http://www.javascriptkit.com/ for this script and 100s more
***********************************************/

function checkboxlimit(checkgroup, limit){
    var checkgroup=checkgroup
    var limit=limit
    for (var i=0; i<checkgroup.length; i++){
        checkgroup[i].onclick=function(){
        var checkedcount=0
        for (var i=0; i<checkgroup.length; i++)
            checkedcount+=(checkgroup[i].checked)? 1 : 0
        if (checkedcount>limit){
            alert("You can only select a maximum of "+limit+" checkboxes.")
            this.checked=false
            }
        }
    }
}

window.addEventListener(
  'load',
  function() {
      checkboxlimit(document.getElementById("toppings").getElementsByTagName("input"), 3)
  },
  {once: true, passive: true}
)
