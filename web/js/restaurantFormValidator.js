/* 
 * @author Dawn Bykowski
 * form validation for the restaurant menu
 */
function validateMenuForm() {
    if (!document.getElementById('entree').checked &&
            !document.getElementById('salad').checked &&
            !document.getElementById('side').checked &&
            !document.getElementById('drink').checked) {
        alert("Error! Please make your menu selections");
        return false;
    } else {
        return true;
    }
}