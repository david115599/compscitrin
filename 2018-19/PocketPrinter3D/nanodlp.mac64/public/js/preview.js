function preview_init(){
	if ($('#preview img').length==0) return;
	$("body").delegate("#preview_layer","click",function(e){
		e.preventDefault();
		var t=$("#preview_range");
		if (t.data("clicked")){
			t.data("clicked",false);
			$(this).text("Preview");
			$.get("/projector/blank");
		} else {
			if (confirm_action($(this))) {
				$.get("/projector/display/"+"plates***"+t.data("plate")+"***"+t.val()+".png");
				t.data("clicked",true);
				$(this).text("Close");
			}
		}
	}).delegate("#preview_previous","click",function(){
		$("#preview_range").val($("#preview_range").val()-1);
		$("#preview_range").trigger("change");
	}).delegate("#preview_next","click",function(){
		$("#preview_range").val(parseInt($("#preview_range").val())+1);
		$("#preview_range").trigger("change");
	}).delegate(".overhang","click",function(e){
		e.preventDefault();
		$("#preview_range").val($(this).text());
		$("#preview_range").trigger("change");
	}).delegate("#toggle_details","click",function(){
		if ($(this).data("display")){
			$(this).data("display",false);
			$("#details,#preview").removeClass("split");
			return;
		}
		$(this).data("display",true);
		$("#details,#preview").addClass("split");
		preview_update();
	}).delegate("#preview_range","change keypress",function(){
		preview_update();
	});
	preview_update();	
}

var compareTimer;
function preview_update(){
	var d = new Date();
	var addon = '?' + d.getTime();
	var current_layer = $('#preview_range').val();
	clearTimeout(compareTimer);
	compareTimer = setTimeout(function(){ 
		compare($('#preview img'),$('#preview img').data('path'),current_layer);
	}, 100);
	
	$('#current_layer').html(current_layer);
	$('#preview img').attr('src', $('#preview img').data('path') + current_layer + '.png' + addon);
	if (!$("#toggle_details").data("display")) return;

	$.getJSON($('#preview img').data('path')+"info.json").done(function(data) {
		d=data[current_layer-1];
		$.each(d,function(k,v){
			$("#"+k).html(v);
		});
	});
	$.getJSON("/layer/preview/"+$('#preview_range').data("plate")+"/"+current_layer).done(function(data) {
		$.each(data,function(k,v){
			$("#"+k).html(v);
		});
	});
}
	// function to retrieve an image
	function loadImage(url) {
		return new Promise((fulfill, reject) => {
		  let imageObj = new Image();
		  imageObj.onload = () => fulfill(imageObj);
		  imageObj.src = url;
		});
	  }

function compare(selector,plate,current_layer){
	if (current_layer<2) return;
	const canvas = document.createElement('canvas');
	const context = canvas.getContext('2d');
	context.canvas.width = selector.innerWidth();
	context.canvas.height = selector.innerHeight();
	var d = new Date();
	var addon = '?' + d.getTime();
	Promise.all([		
		loadImage(plate+current_layer+".png"+addon),
		loadImage(plate+(current_layer-1)+".png"+addon),
	  ])
	  .then((images) => {
		context.drawImage(images[0], 0, 0, context.canvas.width, context.canvas.height);
		var c = context.getImageData(1, 1, context.canvas.width, context.canvas.height);
		context.drawImage(images[1], 0, 0, context.canvas.width, context.canvas.height);
		var p = context.getImageData(1, 1, context.canvas.width, context.canvas.height);
	
		for (i = 0; i < c.data.length; i+=4){
			if (c.data[i]>0&&p.data[i]==0){
				c.data[i+1] = 0;
				c.data[i+2] = 0;
			}
		}
		var img = imagedata_to_image(selector,c);
	  })
	  .catch( (e) => alert(e) );
}
function imagedata_to_image(selector,imagedata) {
	var canvas = document.createElement('canvas');
	var ctx = canvas.getContext('2d');
	canvas.width = imagedata.width;
	canvas.height = imagedata.height;
	ctx.putImageData(imagedata, 0, 0);

	var image = new Image();
	selector.attr("src",canvas.toDataURL());
	return image;
}
