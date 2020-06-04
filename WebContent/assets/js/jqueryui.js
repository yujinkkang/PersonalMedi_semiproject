$( function() {
   
   
    $( '#navi_0' ).on( 'mouseover', function() {
      $('#navi_1').css('visibility','visible');
      
      
      });
    

    $( '#navi_0' ).on( 'mouseout', function() {
        $('#navi_1').css('visibility','hidden');
        
      
      });
  } );