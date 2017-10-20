var ctx = canvas.getContext("2d");

    var step = 2*Math.PI/20;  // see note 1
    var h = 150;
    var k = 150;
    var r = 50;

    ctx.beginPath();  //tell canvas to start a set of lines

    for(var theta=0;  theta < 2*Math.PI;  theta+=step)
     { var x = h + r*Math.cos(theta);
       var y = k - r*Math.sin(theta);    //note 2.
       ctx.lineTo(x,y);
     }

    ctx.closePath();     //close the end to the start point
	ctx.stroke();        //actually draw the accumulated lines
  var x = h +       r*Math.cos(theta) ;
var y = k - 0.5 * r*Math.sin(theta) ; 
