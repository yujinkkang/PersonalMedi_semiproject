(function ($) {

// TOP Menu Sticky
$(window).on('scroll', function () {
    var scroll = $(window).scrollTop();
    var width_size = window.outerWidth;
	if (scroll < 540 || width_size<1094) {
    $("#stickyn").removeClass("sticky");
    $("#stickyn").css("display","none");
    $("#stickyn").fadeOut(700);
    
	} else {
    $("#stickyn").addClass("sticky");
   
    
    $("#stickyn").fadeIn(700);
    
    $("#stickyn").css("display","block");
    
    }
    
  
});

$(window).resize(function(){
    var width_size = window.outerWidth;
    
    if(width_size<1094){
        $("#stickyn").css("display","none");
        
    }

});



})(jQuery);	