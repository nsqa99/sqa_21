window.addEventListener("load", event => {

    // Expand Left Side
    var icon = document.querySelector('.left__icon'),
        left = document.querySelector('.left');

    icon.addEventListener('click', expand);

    function expand() {
        if (left.classList.contains('show')) {
            left.classList.remove('show')
        } else {
            left.classList.add('show')
        }
    }

    var menuItem = document.querySelectorAll('.left__menuItem');

    menuItem.forEach(function (el) {
        el.addEventListener("click", openMenu);
    });

    function openMenu(event) {
        var currentmenuItem = event.currentTarget;

        if (currentmenuItem.classList.contains('open')) {
            currentmenuItem.classList.remove('open');
        } else {
            currentmenuItem.classList.add('open');
        }
    };
})


    function showform(){
        var x = document.getElementById("abc");
        if (x.style.display == "none"){
         x.style.display = "flex";
        } else {
         x.style.display = "none";
        }
    }

let btnClose = document.querySelector('#close');

let abc;
btnClose.addEventListener('click',()=>{
    if (abc) abc.close();
});