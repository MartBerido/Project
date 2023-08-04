function validateForm() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const city = document.getElementById('city').value;
    const postalCode = document.getElementById('postalCode').value;

    if (password !== confirmPassword) {
        alert('Passwords do not match. Please re-enter your password.');
        return false;
    }

    const phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(phoneNumber)) {
        alert('Please enter a valid 10-digit phone number.');
        return false;
    }

    const nameRegex = /^[A-Za-z]+$/;
    if (!nameRegex.test(firstName)) {
        alert('First name should contain only text (letters).');
        return false;
    }

    if (!nameRegex.test(lastName)) {
        alert('Last name should contain only text (letters).');
        return false;
    }

    const cityRegex = /^[A-Za-z ]+$/;
    if (!cityRegex.test(city)) {
        alert('City should contain only text (letters).');
        return false;
    }

    const zipRegex = /^[A-Za-z0-9]{6}$/;
    if (!zipRegex.test(postalCode)) {
        alert('Postal code should contain 6 characters (letters and digits).');
        return false;
    }

    return true;
}

module.exports = {validateForm};