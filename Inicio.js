document.getElementById("logo").addEventListener("click", function () {
    var menu = document.getElementById("menu2");
    if (menu.style.display === "block") {
        menu.style.display = "none";
    } else {
        menu.style.display = "block";
    }
});

document.addEventListener("click", function (event) {
    var menu = document.getElementById("menu2");
    var logo = document.getElementById("logo");
    if (menu.style.display === "block" && event.target !== menu && event.target !== logo) {
        menu.style.display = "none";
    }
});
