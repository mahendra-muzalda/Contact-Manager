console.log("Script loaded...");


// change theme work
let currentTheme = getTheme();
// initial -->



document.addEventListener('DOMContentLoaded', function() {
  console.log('The page has loaded!');
  changeTheme(currentTheme);
});

//TODO
function changeTheme(currentTheme) {
    //set to web page
    changePageTheme(currentTheme,currentTheme);
    //set the listener to change theme button
    const changeThemeButton = document.querySelector('#theme_change_button');
    changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";
    // const oldTheme = currentTheme;

    changeThemeButton.addEventListener('click', (event) => {
        console.log("change theme button clicked");
        let oldTheme = currentTheme;
        if (currentTheme == "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }

        changePageTheme(currentTheme,oldTheme);
    });

}

//set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}


//get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");

    return theme ? theme : "light";
}


//change page theme
function changePageTheme(theme, oldTheme) {

    //local storage mein update karenge
    //setTheme(currentTheme);
    //remove the current theme
    document.querySelector('html').classList.remove(oldTheme);
    //set the current theme
    document.querySelector('html').classList.add(theme);
    //change the text of button
    document.querySelector("#theme_change_button").querySelector("span").textContent = theme == "light" ? "Dark" : "Light";

}

//  end of the change theme work
