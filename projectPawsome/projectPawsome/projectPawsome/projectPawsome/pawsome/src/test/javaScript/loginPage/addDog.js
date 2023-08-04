function validateForm() {

    const dogName = document.getElementById("dogName").value;
    const dogBreed = document.getElementById("dogBreed").value;
    const dogAge = document.getElementById("dogAge").value;

    const namePattern = /^[A-Za-z\s]+$/;
    const agePattern = /^\d{1,2}$/;

    if (!namePattern.test(dogName)) {
        alert("Name should contain only text (letters and spaces).");
        return false;
    }

    if (!namePattern.test(dogBreed)) {
        alert("Breed should contain only text (letters and spaces).");
        return false;
    }

    if (!agePattern.test(dogAge)) {
        alert("Age should be 1 or 2 digits.");
        return false;
    }

    return true;
}

module.exports = {validateForm};