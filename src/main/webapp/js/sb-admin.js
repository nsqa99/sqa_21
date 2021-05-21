(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle").on('click',function(e) {
    e.preventDefault();
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll',function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(event) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    event.preventDefault();
  });
  
  var currentMonth = new Date().getMonth() + 1;
  if (+currentMonth<10) currentMonth = '0' + currentMonth;
  var currentYear = new Date().getFullYear();
  
  $('#month-add').attr({
     "min": currentYear + '-' + currentMonth
  });
  
  $('#updateModal').on('show.bs.modal', function(e) {
    var idUpdate = $(e.relatedTarget).data('fuck');
    var userId = $(e.relatedTarget).data('id');
    var name = $(e.relatedTarget).data('name');
    var address = $(e.relatedTarget).data('address');
    var month = $(e.relatedTarget).data('month');
    var amount = $(e.relatedTarget).data('amount');
    $(e.currentTarget).find('#userId-indx').val(userId);
    $(e.currentTarget).find('#id-indx').val(idUpdate);
    $(e.currentTarget).find('#userFullName-indx').val(name);
    $(e.currentTarget).find('#userAddr-indx').val(address);
    $(e.currentTarget).find('#consumed-indx').val(amount);
    $(e.currentTarget).find('#month-indx').val(month);
});

$('#payModal').on('show.bs.modal', function(e) {
    var idPay = $(e.relatedTarget).data('fuck');
    var userId = $(e.relatedTarget).data('id');
    var name = $(e.relatedTarget).data('name');
    var address = $(e.relatedTarget).data('address');
    var month = $(e.relatedTarget).data('month');
    var amount = $(e.relatedTarget).data('amount');
    var total = $(e.relatedTarget).data('total');
    $(e.currentTarget).find('#userId').val(userId);
    $(e.currentTarget).find('#idPay').val(idPay);
    $(e.currentTarget).find('#userFullName').val(name);
    $(e.currentTarget).find('#userAddr').val(address);
    $(e.currentTarget).find('#month').val(month);
    $(e.currentTarget).find('#consumed').val(amount);
    $(e.currentTarget).find('#toPay').val(total);
});

$('#configModal').on('show.bs.modal', function(e) {
    var id = $(e.relatedTarget).data('id');
    var area = $(e.relatedTarget).data('area');
    var levels = $(e.relatedTarget).data('levels');
    
    $(e.currentTarget).find('#idConfig').val(id);
    $(e.currentTarget).find('#configAddr').val(area);
    levels.map((level, index) => {
        $(e.currentTarget).find('#level' + (index + 1)).val(level);
    })
});

})(jQuery); // End of use strict
